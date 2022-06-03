package tests

import org.scalatest._
import pbd.PaleBlueDot

class ProgrammingObjective1 extends FunSuite {

  val citiesFilename: String = "data/cities.csv"
  val ans1: List[String] = List("us", "buffalo", "NY")
  val ans2: List[String] = List("us", "belen", "NM")
  val ans3: List[String] = List("ar", "ushuaia", "23")

  def compareans(d1:List[String],d2:List[String]): Boolean ={
    (d1 == d2)
  }

  test("test 1") {
    //assert(compareans(PaleBlueDot.closestCity(citiesFilename,List(42.91, -78.89)),ans1))
    assert(compareans(PaleBlueDot.closestCity(citiesFilename,List(34.6627778,-106.7758333)).sorted,ans2.sorted))
    assert(compareans(PaleBlueDot.closestCity(citiesFilename,List(1000.0,1000.0)).sorted,ans3.sorted))
  }
}

