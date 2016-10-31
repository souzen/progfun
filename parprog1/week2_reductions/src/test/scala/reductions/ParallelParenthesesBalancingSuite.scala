package reductions

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import reductions.ParallelParenthesesBalancing._

@RunWith(classOf[JUnitRunner])
class ParallelParenthesesBalancingSuite extends FunSuite {

  def check(input: String, expected: Boolean) = {
    assert(balance(input.toArray) == expected, s"balance($input) should be $expected")
    assert(parBalance(input.toArray, 1) == expected, s"balance($input) should be $expected")
  }

  test("balance should work for empty string") {
    check("(abcd)", true)
    check("(abc))de()", false)
    check("))((asdasd", false)
  }

  test("balance should work for string of length 1") {
    check("(", false)
    check(")", false)
    check(".", true)
  }

  test("balance should work for string of length 2") {
    check("()", true)
    check("(())", true)
    check(")(", false)
    check("((", false)
    check("))", false)
    check(".)", false)
    check(".(", false)
    check("(.", false)
    check(").", false)
  }


}