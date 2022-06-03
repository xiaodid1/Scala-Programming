package tests

import org.scalatest._
import pbd.PaleBlueDot

class LectureObjective2 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val ans1:String = "cn"
  val ans2:String = "bt"
  val ans3:String ="at"
  val ans4:String = ""
  def compareans(d1:String,d2:String): Boolean ={
    (d1 == d2)
  }

  test("test 1") {
    assert(compareans(PaleBlueDot.getCountryCode(countriesFile,"ChInA"),ans1))
    assert(compareans(PaleBlueDot.getCountryCode(countriesFile,"BhUTaN"),ans2))
    assert(compareans(PaleBlueDot.getCountryCode(countriesFile,"AuStrIA"),ans3))
    assert(compareans(PaleBlueDot.getCountryCode(countriesFile,""),ans4))

  }

}
