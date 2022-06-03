package tests

import org.scalatest._
import pbd.PaleBlueDot

class LectureObjective3 extends FunSuite {

  val citiesFilename: String = "data/cities.csv"
  val ans1:Map[String,Int] = Map("asadabad"->48400,"asmar"->15708)
  val ans2:Map[String,Int] = Map("agadez"-> 88569, "arlit" -> 83400, "bilma" -> 2292)
  val ans3:Map[String,Int] = Map()
  def compareans(d1:Map[String,Int],d2:Map[String,Int]): Boolean ={
    (d1 == d2)
  }

  test("test 1") {
    assert(compareans(PaleBlueDot.cityPopulations(citiesFilename,"af","34"),ans1))
    assert(compareans(PaleBlueDot.cityPopulations(citiesFilename,"ne","01"),ans2))
    assert(compareans(PaleBlueDot.cityPopulations(citiesFilename,"",""),ans3))

  }

}

