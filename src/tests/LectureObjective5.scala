package tests

import org.scalatest._
import pbd.PaleBlueDot

class LectureObjective5 extends FunSuite {
  val ans1:Double = 200.88528932125743
  val ans2:Double = 13831.493521339997
  val ans3:Double = 0.0
  val EPSILON: Double = 0.001

  def compareDoubles(d1: Double, d2: Double): Boolean = {
    Math.abs(d1 - d2) < EPSILON
  }

  test("test 1") {
    assert(compareDoubles(PaleBlueDot.greaterCircleDistance(List(14.3166667,-88.9833333),List(15.5833333,-87.65)),ans1))
    assert(compareDoubles(PaleBlueDot.greaterCircleDistance(List(13.4816667,-87.105),List(17,3380,46.670132,17.643978)),ans2))
    assert(compareDoubles(PaleBlueDot.greaterCircleDistance(List(13.4816667,-87.105),List(13.4816667,-87.105)),ans3))


  }

}
