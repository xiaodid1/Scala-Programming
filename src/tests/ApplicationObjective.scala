package tests

import org.scalatest._
import pbd.PaleBlueDot

class ApplicationObjective extends FunSuite {

  val citiesFilename: String = "data/cities.csv"
  val ans2: List[String] = List("us", "buffalo", "NY")
  val ans1: List[String] = List("gl", "nanortalik", "03")
  val ans3: List[String] = List()

  def compareans(d1:List[String],d2:List[String]): Boolean ={
    (d1 == d2)
  }

  test("test 1"){
    assert(compareans(PaleBlueDot.whereToMeet(citiesFilename,List("us","buffalo","NY"),List("gb","kingussie","V3")).sorted,ans1.sorted))
    assert(compareans(PaleBlueDot.whereToMeet(citiesFilename,List("us","buffalo","NY"),List("us","buffalo","NY")).sorted,ans2.sorted))
    //assert(compareans(PaleBlueDot.whereToMeet(citiesFilename,)))

  }

}
