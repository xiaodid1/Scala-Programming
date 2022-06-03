package tests

import org.scalatest._
import pbd.PaleBlueDot

class LectureObjective1 extends FunSuite {

  val EPSILON: Double = 0.001

  def compareDoubles(d1: Double, d2: Double): Boolean = {
    Math.abs(d1 - d2) < EPSILON
  }


  test("test 1") {
    assert(compareDoubles(PaleBlueDot.degreesToRadians(180.0),Math.PI))
    assert(compareDoubles(PaleBlueDot.degreesToRadians(360.0),2*Math.PI))
    assert(compareDoubles(PaleBlueDot.degreesToRadians(720.0),4*Math.PI))

  }


}
