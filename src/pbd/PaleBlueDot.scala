package pbd

import java.awt.Desktop
import java.net.URI
import scala.io.{BufferedSource, Source}

object PaleBlueDot {


  /**
   * Lecture Objective 1
   *
   * Converts degrees into radians
   *
   * @param degrees A value provided in degrees
   * @return The radian equivalent of the input value
   */
  def degreesToRadians(degrees: Double): Double = {
    degrees * Math.PI / 180
  }



  /**
   * Lecture Objective 2
   *
   * Given a country name using and case (upper/lower), return the country code in all lowercase letters
   *
   * Ex. If "Heard Island and McDonald Islands#HM" is a line countriesFilename
   * and countryName is "hEaRd IsLaNd AnD mCdOnAlD iSlAnDs" the returned value is "hm"
   *
   * If countryName is not in the file, return the empty String: ""
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param countryName       The name of the country to lookup in the file with any mix of upper/lower-case
   * @return The two letter country code for countryName
   */
  def getCountryCode(countriesFilename: String, countryName: String): String = {
    var answer:String = ""
    val countriesFile: BufferedSource = Source.fromFile(countriesFilename)
    val countryname = countryName.toLowerCase
    for (line <- countriesFile.getLines()){
      val countryline: Array[String] = line.split("#")
      val country = countryline(0).toLowerCase
      if (country==countryname){
        answer = countryline(1).toLowerCase
      }
    }
    answer
  }


  /**
   * Lecture Objective 3
   *
   * Returns a Map[cityName -> population] for all cities in the given county and region. The name of each
   * city should match exactly how it appears in citiesFilename and the population is read from the file
   * and converted to an Int.
   *
   * Ex: PaleBlueDot.cityPopulations(citiesFilename, "ad", "04") returns Map("la massana" -> 7211) since
   * "la massana" is the only city in region "04" of Andorra (code "ad") and its population is 7211
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param countryCode    A two character country code
   * @param region         A two character region code
   * @return A Map containing the name and population of every city matching both the countryCode and region
   */
  def cityPopulations(citiesFilename: String, countryCode: String, region: String): Map[String, Int] = {
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    var answer:Map[String,Int] = Map()
    for (line <- citiesFile.getLines()){
      val line2:Array[String] = line.split(",")
      if (countryCode==line2(0)&&region==line2(2)){
        answer=answer+(line2(1)->line2(3).toInt)
      }
    }
    println(answer)
    answer
  }


  /**
   * Lecture Objective 4
   *
   * Returns a List of city names in the given county and region with a population at least minPopulation.
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param countryCode    A two character country code
   * @param region         A two character region code
   * @param minPopulation  the minimum population that could be returned
   * @return All city names in countryCode/region with a population >= minPopulation
   */
  def bigCities(citiesFilename: String, countryCode: String, region: String, minPopulation: Int): List[String] = {
    var list: List[String] = List()
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    for (line <- citiesFile.getLines()){
      val line2 = line.split(",")
      if (line2(0)==countryCode&&line2(2)==region&&line2(3).toInt>=minPopulation){
        list=list:+line2(1)
      }
    }
    list
  }


  /**
   * Lecture Objective 5
   *
   * Computes the grater circle distance ("As the crow flies") between two locations on Earth in kilometers.
   * The input locations are given as Lists of Double containing the latitude and longitude coordinates of that
   * location. For example, if the location is latitude: 35.685 and longitude: 139.751389 the input would be
   * List(35.685, 139.751389).
   *
   * @param location1 A location on Earth given as a List containing latitude and longitude coordinates
   * @param location2 A location on Earth given as a List containing latitude and longitude coordinates
   * @return The greater circle distance between the two input locations
   */
  def greaterCircleDistance(location1: List[Double], location2: List[Double]): Double = {
    val earthRadius: Double = 6371.0 // km
    var answer: Double = 0.0
    val lat1: Double = degreesToRadians(location1.head)
    val lon1: Double = degreesToRadians(location1(1))
    val lat2: Double = degreesToRadians(location2.head)
    val lon2: Double = degreesToRadians(location2(1))
    val latdis = lat1 - lat2
    val londis = lon1 - lon2
    val sinlat = Math.sin(latdis / 2)
    val sinlon = Math.sin(londis / 2)
    val dis = sinlat * sinlat + (Math.cos(lat1) * Math.cos(lat2) * sinlon * sinlon)
    val dis2 = Math.atan2(Math.sqrt(dis), Math.sqrt(1 - dis)) * 2
    answer+=(dis2 * earthRadius)
    answer
  }


  /**
   * Programming Objective 1
   *
   * You find yourself stranded in an unfamiliar place with no signs of civilization. You don't have much with you,
   * but you do have a locator that gives your current latitude/longitude, a csv file of cities, and your final
   * submission to the PaleBlueDot assignment from CSE116 (What luck!). You decide that finding and walking
   * directly to the closest city will give you the best chance to survive.
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param location       A location on Earth given as a List containing latitude and longitude coordinates
   * @return The city closest to the given location as a List containing country code, city name, and region
   *         exactly as they appear in the cities file
   */
  def closestCity(citiesFilename: String, location: List[Double]): List[String] = {
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    var count: Int = 0
    var distance: Double = 0.0
    var mindistance: Double = 0.0
    var mincityname: List[String] = List()
    for (line <- citiesFile.getLines().drop(1)){
      val city = line.split(",")
      val location2=List((city(4).toDouble),(city(5).toDouble))
      distance=greaterCircleDistance(location,location2)
      if (count == 0){
        mindistance=distance
        mincityname=List(city(0),city(1),city(2))
        count+=1
      } else if (distance < mindistance){
        mindistance=distance
        mincityname=List(city(0),city(1),city(2))
      }
    }
    mincityname
  }


  /**
   * Programming Objective 2
   *
   * Find the population of a country by name. Not quite a life or death situation, but interesting information
   * regardless.
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @return The total population of the given country
   */
  def countryPopulation(countriesFilename: String, citiesFilename: String, countryName: String): Int = {
    var population: Int = 0
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    val countrycode  = getCountryCode(countriesFilename,countryName)
    for (line <- citiesFile.getLines()){
      val line2 = line.split(",")
      if (countrycode==line2(0)){
        population = population + line2(3).toInt
      }
    }
    population
  }

  /**
   * Application Objective
   *
   * You're in a city. I'm in a city. We want to meet in a city with a fair split of travel distance for each of us.
   * We happen to both own helicopters so we'll travel "as the crow flies" and we're not concerned about roads or
   * oceans. We just need to find city closest to the midpoint between our two cities and we'll meet there.
   *
   * Each city is provided to this method as a List containing the country code, name, and region exactly as they
   * appear in the cities file (ie. Don't do anything with upper/lower-case in this method.) The returned city should
   * follow the same formatting (Don't modify the upper/lower-case of any Strings).
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param city1          A city as a List containing country code, name, and region exactly as they appear in the
   *                       cities file
   * @param city2          A city as a List containing country code, name, and region exactly as they appear in the
   *                       cities file
   * @return The city closest to the midpoint of the two input cities as a List containing country code, city name,
   *         and region exactly as they appear in the cities file
   */
  def whereToMeet(citiesFilename: String, city1: List[String], city2: List[String]): List[String] = {
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    var latlon1: List[String] = List()
    var latlon2: List[String] = List()
    for (line <- citiesFile.getLines().drop(1)){
      val city = line.split(",")
      if ((city1(0)==city(0))&&(city1(1)==city(1))){
        latlon1=List(city(4),city(5))
      }
      if ((city2(0)==city(0))&&(city2(1)==city(1))){
        latlon2=List(city(4),city(5))
      }
    }
    val lat1: Double = degreesToRadians(latlon1(0).toDouble)
    val lon1: Double = degreesToRadians(latlon1(1).toDouble)
    val lat2: Double = degreesToRadians(latlon2(0).toDouble)
    val lon2: Double = degreesToRadians(latlon2(1).toDouble)
    val dislon: Double = lon2 - lon1
    val x: Double = Math.cos(lat2) * Math.cos(dislon)
    val y: Double = Math.cos(lat2) * Math.sin(dislon)
    val midx: Double = Math.atan2(Math.sin(lat1)+Math.sin(lat2), Math.sqrt((Math.cos(lat1)+x)*(Math.cos(lat1)+x)+y*y))
    val midy: Double = lon1 + Math.atan2(y,Math.cos(lat1)+x)
    val Midx: Double = Math.toDegrees(midx)
    val Midy: Double = Math.toDegrees(midy)
    val answer: List[String] = closestCity(citiesFilename,List(Midx,Midy))
    answer
  }


  /**
   * Helper Method
   *
   * Opens Google Maps at a specific location. The location is a List containing the latitude then longitude as Doubles
   *
   * @param location The location to open in the format List(Latitude, Longitude)
   */
  def openMap(location: List[Double]): Unit = {
    if (Desktop.isDesktopSupported && Desktop.getDesktop.isSupported(Desktop.Action.BROWSE)) {
      val url: String = "http://maps.google.com/maps?t=m&q=loc:" + location.head.toString + "+" + location(1).toString
      Desktop.getDesktop.browse(new URI(url))
    } else {
      println("Opening the browser not supported")
    }
  }


  def main(args: Array[String]): Unit = {
    //openMap(List(43.002743, -78.7874136))
    //cityPopulations("data/cities.csv","ne","01")
    //getCountryCode("data/countries.txt","ChiNa")
    //println(bigCities("data/cities.csv","hn","05",1000000))
    //println(greaterCircleDistance(List(14.3166667,-88.9833333),List(15.5833333,-87.65)))
    //println(closestCity("data/cities.csv",List(1000.0,1000.0)))
    //println(whereToMeet("data/cities.csv",List("us","buffalo","NY"),List("gb","kingussie","V3")))
    println(countryPopulation("data/countries.txt","data/cities.csv","AnDorrA"))
  }

}
