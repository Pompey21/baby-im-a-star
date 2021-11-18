package imdb

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

import scala.io.Source

case class TitleBasics(tconst: String, titleType: Option[String], primaryTitle: Option[String],
                      originalTitle: Option[String], isAdult: Int, startYear: Option[Int], endYear: Option[Int],
                      runtimeMinutes: Option[Int], genres: Option[List[String]]) {
  def getGenres(): List[String] = genres.getOrElse(List[String]())
}
case class TitleRatings(tconst: String, averageRating: Float, numVotes: Int)
case class TitleCrew(tconst: String, directors: Option[List[String]], writers: Option[List[String]])
case class NameBasics(nconst: String, primaryName: Option[String], birthYear: Option[Int], deathYear: Option[Int],
                      primaryProfession: Option[List[String]], knownForTitles: Option[List[String]])

object ImdbAnalysis {

  val conf: SparkConf = new SparkConf().setAppName("kapodistrias").setMaster("local[1]")
  val sc: SparkContext = new SparkContext(conf)

  // Hint: use a combination of `ImdbData.titleBasicsPath` and `ImdbData.parseTitleBasics`
  val titleBasicsRDD: RDD[TitleBasics] = sc.textFile(ImdbData.titleBasicsPath).map(ImdbData.parseTitleBasics)
  println(titleBasicsRDD)

  // // Hint: use a combination of `ImdbData.titleRatingsPath` and `ImdbData.parseTitleRatings`
  // val titleRatingsRDD: RDD[TitleRatings] = sc.textFile(ImdbData.titleBasicsPath).map(ratings => ImdbData.parseTitleRatings(ratings))

  // // Hint: use a combination of `ImdbData.titleCrewPath` and `ImdbData.parseTitleCrew`
  // val titleCrewRDD: RDD[TitleCrew] = sc.textFile(ImdbData.titleBasicsPath).map(crew => ImdbData.parseTitleCrew(crew))

  // // Hint: use a combination of `ImdbData.nameBasicsPath` and `ImdbData.parseNameBasics`
  // val nameBasicsRDD: RDD[NameBasics] = sc.textFile(ImdbData.titleBasicsPath).map(nameBasics => ImdbData.parseNameBasics(nameBasics))






  def task1(rdd: RDD[TitleBasics]): Unit = {// RDD[(Float, Int, Int, String)] = {
    val genre_time_instances = rdd.filter(movie =>
      movie.genres.isDefined
      && movie.runtimeMinutes.isDefined
      && movie.runtimeMinutes.get >= 0)
    //   .flatMap(movie => movie.genres.get
    //   .map(genre => (genre, movie.runtimeMinutes.get)))
    //   .groupBy(movie => movie._1)
    //   .map(genre => (genre._2.map(movie => movie._2).sum.toFloat/(genre._2.length),
    //     genre._2.map(movie => movie._2).min,
    //     genre._2.map(movie => movie._2).max,
    //     genre._1))
    sc.emptyRDD
  }




  // def task2(l1: RDD[TitleBasics], l2: RDD[TitleRatings]): RDD[String] = {



  //   val rddTitleBasics = sc.emptyRDD[String] 
  //   return rddTitleBasics
  // }





  // def task3(l1: RDD[TitleBasics], l2: RDD[TitleRatings]): RDD[(Int, String, String)] = {
  //   val rddTitleBasics = sc.emptyRDD[(Int, String, String)] 
  //   return rddTitleBasics
  // }





  // // Hint: There could be an input RDD that you do not really need in your implementation.
  // def task4(l1: RDD[TitleBasics], l2: RDD[TitleCrew], l3: RDD[NameBasics]): RDD[(String, Int)] = {
  //   val rddTitleBasics = sc.emptyRDD[(String, Int)] 
  //   return rddTitleBasics
  // }






  def main(args: Array[String]) {

    println("Hello world\tHappy learning!") 
    // val x = task1(titleBasicsRDD)
    // val durations = timed("Task 1", task1(titleBasicsRDD).collect().toList)
    // val titles = timed("Task 2", task2(titleBasicsRDD, titleRatingsRDD).collect().toList)
    // val topRated = timed("Task 3", task3(titleBasicsRDD, titleRatingsRDD).collect().toList)
    // val crews = timed("Task 4", task4(titleBasicsRDD, titleCrewRDD, nameBasicsRDD).collect().toList)
    // println(durations)
    // println(titles)
    // println(topRated)
    // println(crews)
    // println(timing)
    sc.stop()
  }

  val timing = new StringBuffer
  def timed[T](label: String, code: => T): T = {
    val start = System.currentTimeMillis()
    val result = code
    val stop = System.currentTimeMillis()
    timing.append(s"Processing $label took ${stop - start} ms.\n")
    result
  }
}
