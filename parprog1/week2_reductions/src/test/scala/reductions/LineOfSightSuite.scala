package reductions

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LineOfSightSuite extends FunSuite {

  import LineOfSight._

  test("lineOfSight should correctly handle an array of size 4") {
    val output = new Array[Float](4)
    lineOfSight(Array[Float](0f, 1f, 8f, 9f), output)
    assert(output.toList == List(0f, 1f, 4f, 4f))
  }

  test("upsweepSequential should correctly handle the chunk 1 until 4 of an array of 4 elements") {
    val res = upsweepSequential(Array[Float](0f, 1f, 8f, 9f), 1, 4)
    assert(res == 4f)
  }

  test("upsweepSequential should correctly handle the chunk 1 until 5 of an array of 5 elements") {
    val res = upsweepSequential(Array[Float](0f, 1f, 8f, 9f, 1f), 1, 5)
    assert(res == 4f)
  }

  test("downsweepSequential should correctly handle a 4 element array when the starting angle is zero") {
    val output = new Array[Float](4)
    downsweepSequential(Array[Float](0f, 1f, 8f, 9f), output, 0f, 1, 4)
    assert(output.toList == List(0f, 1f, 4f, 4f))
  }

  test("downsweepSeq should correctly compute the output for a non-zero starting angle") {
    val output = new Array[Float](5)
    downsweepSequential(Array[Float](0f, 8f, 14f, 33f, 48f), output, 8f, 1, 5)
    assert(output.toList == List(0.0, 8.0, 8.0, 11.0, 12.0))
  }

  test("downsweep should correctly compute the output for a non-zero starting angle") {
    val output = new Array[Float](5)
    val input = Array[Float](0f, 8f, 8f, 33f, 48f)

    val tree = upsweep(input, 0, 5, 2)
    downsweep(input, output, 0f, tree)

    assert(output.toList == List(0.0, 8.0, 8.0, 11.0, 12.0))
  }

  test("downsweep1 should correctly compute the output for a non-zero starting angle") {
    val output = new Array[Float](5)
    val input = Array[Float](0f, 8f, 14f, 33f, 48f)

    val tree = upsweep(input, 1, 5, 2)
    downsweep(input, output, 2f, tree)

    assert(output.toList == List(0.0, 8.0, 8.0, 11.0, 12.0))
  }

  test("up down parallel count") {
    val output = new Array[Float](17)
    val input = Array[Float](0f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 8f, 8f, 33f, 48f)

    parLineOfSight(input, output, 1)
  }

}

