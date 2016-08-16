package exercise

import scala.annotation.tailrec

/**
  * Created by sosnickl on 13.07.2016.
  */
object SumExercise {

  def sumSimple(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sumSimple(f, a + 1, b)
  }

  def sumIntsSimple(a: Int, b: Int): Int = {
    sumSimple((x: Int) => x, a, b)
  }

  def sumTailrec(f: Int => Int, a: Int, b: Int): Int = {
    @tailrec
    def loop(a: Int, acc: Int): Int = if (a > b) acc
    else loop(a + 1, f(a) + acc)

    loop(a, 0)
  }

  def sumIntsTailrec(a: Int, b: Int): Int = {
    sumTailrec((x: Int) => x, a, b)
  }

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
  }

  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    mapReduce(f, (x, y) => x + y, 0)(a, b)
  }

  def sumInts(a: Int, b: Int): Int = {
    sum((x: Int) => x)(a, b)
  }

  def product(f: Int => Int)(a: Int, b: Int): Int = {
    mapReduce(f, (x, y) => x * y, 1)(a, b)
  }

  def productInts(a: Int, b: Int): Int = {
    product(x => x)(a, b)
  }

  def fact(n: Int): Int = product(x => x)(1, n)

}
