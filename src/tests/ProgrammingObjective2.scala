package tests

import org.scalatest._
import pbd.PaleBlueDot

class ProgrammingObjective2 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"
  val ans1: Int = 33638
  val ans2: Int = 218884084

  def compareans(d1:Int,d2:Int): Boolean ={
    (d1 == d2)
  }

  test("test 1"){
    assert(compareans(PaleBlueDot.countryPopulation(countriesFile,citiesFilename,"AnDorrA"),ans1))
    assert(compareans(PaleBlueDot.countryPopulation(countriesFile,citiesFilename,"ChInA"),ans2))

  }

}
