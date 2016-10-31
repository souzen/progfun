package reductions

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import reductions.ParallelCountChange._

@RunWith(classOf[JUnitRunner])
class ParallelCountChangeSuite extends FunSuite {

  def seqCheck(money: Int, coins: List[Int], expected: Int) =
    assert(countChange(money, coins) == expected, s"countChange($money, $coins) should be $expected")

  def parCheck(money: Int, coins: List[Int], expected: Int) = {
    assert(parCountChange(money, coins, moneyThreshold(money)) == expected, s"moneyThreshold countChange($money, $coins) should be $expected")
    assert(parCountChange(money, coins, totalCoinsThreshold(coins.length)) == expected, s"totalCoinsThreshold countChange($money, $coins) should be $expected")
    assert(parCountChange(money, coins, moneyThreshold(money)) == expected, s"combinedThreshold countChange($money, $coins) should be $expected")
  }

  test("countChange should return 0 for money < 0") {
    def check(money: Int, coins: List[Int]) = seqCheck(money, coins, 0)

    check(-1, List())
    check(-1, List(1, 2, 3))
    check(-Int.MinValue, List())
    check(-Int.MinValue, List(1, 2, 3))
  }

  test("countChange should return 1 when money == 0") {
    def check(coins: List[Int]) = seqCheck(0, coins, 1)

    check(List())
    check(List(1, 2, 3))
    check(List.range(1, 100))
  }

  test("countChange should return 0 for money > 0 and coins = List()") {
    def check(money: Int) = seqCheck(money, List(), 0)

    check(1)
    check(Int.MaxValue)
  }

  test("countChange should work when there is only one coin") {
    def check(money: Int, coins: List[Int], expected: Int) = seqCheck(money, coins, expected)

    check(1, List(1), 1)
    check(2, List(1), 1)
    check(1, List(2), 0)
    check(Int.MaxValue, List(Int.MaxValue), 1)
    check(Int.MaxValue - 1, List(Int.MaxValue), 0)
  }

  test("countChange should work for multi-coins") {
    def check(money: Int, coins: List[Int], expected: Int) = seqCheck(money, coins, expected)

    check(50, List(1, 2, 5, 10), 341)
    check(250, List(1, 2, 5, 10, 20, 50), 177863)
  }

  test("par countChange should return 0 for money < 0") {
    def check(money: Int, coins: List[Int]) = parCheck(money, coins, 0)

    check(-1, List())
    check(-1, List(1, 2, 3))
    check(-Int.MinValue, List())
    check(-Int.MinValue, List(1, 2, 3))
  }

  test("par countChange should return 1 when money == 0") {
    def check(coins: List[Int]) = parCheck(0, coins, 1)

    check(List())
    check(List(1, 2, 3))
    check(List.range(1, 100))
  }

  test("par countChange should return 0 for money > 0 and coins = List()") {
    def check(money: Int) = parCheck(money, List(), 0)

    check(1)
    check(Int.MaxValue)
  }

  test("par countChange should work when there is only one coin") {
    def check(money: Int, coins: List[Int], expected: Int) = parCheck(money, coins, expected)

    check(1, List(1), 1)
    check(2, List(1), 1)
    check(1, List(2), 0)
    check(Int.MaxValue, List(Int.MaxValue), 1)
    check(Int.MaxValue - 1, List(Int.MaxValue), 0)
  }

  test("par countChange should work for multi-coins") {
    def check(money: Int, coins: List[Int], expected: Int) = parCheck(money, coins, expected)

    check(50, List(1, 2, 5, 10), 341)
    check(250, List(1, 2, 5, 10, 20, 50), 177863)
  }



}
