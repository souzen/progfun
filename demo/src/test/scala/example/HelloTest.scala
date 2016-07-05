package example

import org.scalatest.FunSuite

/**
  * Created by sosnickl on 04.07.2016.
  */
class HelloTest extends FunSuite {
  test("sayHello method works correctly") {
    val hello = new Hello
    assert(hello.sayHello("Scala") == "Hello Scala!")
  }
}
