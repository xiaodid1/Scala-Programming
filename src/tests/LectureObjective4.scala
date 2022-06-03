package tests

import org.scalatest._
import pbd.PaleBlueDot

class LectureObjective4 extends FunSuite {

  val citiesFilename: String = "data/cities.csv"
  val ans1:List[String] = List("acheng", "anda", "baoshan", "beian", "harbin", "hegang", "hengshan", "honggang", "hulan ergi", "jiamusi", "jixi", "longfeng", "mudanjiang", "qiqihar", "qitaihe", "ranghulu", "shuangcheng", "shuangyashan", "suihua", "yichun", "zhaodong")
  val ans2:List[String] = List()
  val ans3:List[String] = List()
  def compareans(d1:List[String],d2:List[String]): Boolean ={
    (d1 == d2)
  }
  test("test 1"){
    assert(compareans(PaleBlueDot.bigCities(citiesFilename,"cn","08",123791).sorted,ans1))
    assert(compareans(PaleBlueDot.bigCities(citiesFilename,"hn","05",1000000).sorted,ans2))
    assert(compareans(PaleBlueDot.bigCities(citiesFilename,"","",10000).sorted,ans3))


  }

}
