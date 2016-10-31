package reductions

import common._
import org.scalameter._

import scala.annotation.tailrec

object ParallelParenthesesBalancingRunner {

  @volatile var seqResult = false

  @volatile var parResult = false

  val standardConfig = config(
    Key.exec.minWarmupRuns -> 40,
    Key.exec.maxWarmupRuns -> 80,
    Key.exec.benchRuns -> 120,
    Key.verbose -> true
  ) withWarmer (new Warmer.Default)

  def main(args: Array[String]): Unit = {
    val length = 100000000
    val chars = new Array[Char](length)
    val threshold = 10000
    val seqtime = standardConfig measure {
      seqResult = ParallelParenthesesBalancing.balance(chars)
    }
    println(s"sequential result = $seqResult")
    println(s"sequential balancing time: $seqtime ms")

    val fjtime = standardConfig measure {
      parResult = ParallelParenthesesBalancing.parBalance(chars, threshold)
    }
    println(s"parallel result = $parResult")
    println(s"parallel balancing time: $fjtime ms")
    println(s"speedup: ${seqtime / fjtime}")
  }
}

object ParallelParenthesesBalancing {

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
    */
  def balance(chars: Array[Char]): Boolean = {

    @tailrec
    def balanceCount(idx: Int, count: Int): Int = {
      if (count < 0) -1
      else if (idx == chars.length) count
      else if (chars(idx) == '(') balanceCount(idx + 1, count + 1)
      else if (chars(idx) == ')') balanceCount(idx + 1, count - 1)
      else balanceCount(idx + 1, count)
    }

    val a = balanceCount(0, 0)
    balanceCount(0, 0) == 0
  }

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
    */
  def parBalance(chars: Array[Char], threshold: Int): Boolean = {

    @tailrec
    def traverse(idx: Int, until: Int, count: Int, sign: Int): (Int, Int) = {
      if (idx >= until) (count, sign)
      else if (chars(idx) == '(') traverse(idx + 1, until, count + 1, if (sign == 0) 1 else sign)
      else if (chars(idx) == ')') traverse(idx + 1, until, count - 1, if (sign == 0) -1 else sign)
      else traverse(idx + 1, until, count, sign)
    }

    def reduce(from: Int, until: Int): (Int, Int) = {
      if (until - from <= threshold)
        traverse(from, until, 0, 0)
      else {
        val mid = from + (until - from) / 2
        val ((lcount, lsign), (rcount, rsign)) = parallel(reduce(from, mid), reduce(mid, until))

        val sign = if (lsign == 0) rsign else lsign

        (lcount + rcount, sign)
      }
    }

    val (count, sign) = reduce(0, chars.length)
    count == 0 && sign >= 0
  }

  // For those who want more:
  // Prove that your reduction operator is associative!

}
