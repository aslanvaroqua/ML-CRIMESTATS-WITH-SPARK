import org.apache.spark.{SparkConf, SparkContext}

object Analysis {

  val conf = new SparkConf().setAppName("Chicago Crime Data Analysis").setMaster("local")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {

    val data = sc.textFile("data/CrimeDataWithoutHeader.csv")
    data.cache()

    val communityCodesData = sc.textFile("data/CommunityCodes.csv").
                                map(rec => (rec.split(",")(0), rec.split(",")(1)))
    val totalNumberOfRecords = data.count()
    println(totalNumberOfRecords) 6508475

    // Cases with no community
    val casesWithNoCommunity = data.
                                  filter(rec => rec.split(",")(13) == "").
                                  count()

    // println(casesWithNoCommunity) (594681)

    // Top 10 communities with most/least crime
    data.
      filter(rec => rec.split(",")(13) != "").
      map(rec => (rec.split(",")(13), 1)).
      reduceByKey(_ + _, 1).
      join(communityCodesData).
      map(item => (item._2._1.toInt, (item._1,item._2._2))).
      takeOrdered(10)(Ordering[Int].on(x=>x._1)).
      foreach(println)

    // Top Crime Types
    data.
      map(rec => (rec.split(",")(5),1)).
      reduceByKey(_ + _, 4).
      map(rec => (rec._1, BigDecimal((rec._2.toDouble/6508475)*100).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble)).
      takeOrdered(3)(Ordering[Double].reverse.on(x=>x._2)).
      foreach(println)

    // What months do criminal like?
    data.
      map(rec => (rec.split(",")(2).split(" ")(0).split("/")(0).toInt, 1)).
      reduceByKey(_+_).
      map(rec => (rec._1,rec._2,BigDecimal((rec._2.toDouble/6508475)*100).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble)).
      takeOrdered(3)(Ordering[Double].reverse.on(x=>x._2)).
      foreach(println)

    // What months have lower criminal activities?
    data.
      map(rec => (rec.split(",")(2).split(" ")(0).split("/")(0).toInt, 1)).
      reduceByKey(_+_).
      map(rec => (rec._1,rec._2,BigDecimal((rec._2.toDouble/6508475)*100).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble)).
      takeOrdered(3)(Ordering[Double].on(x=>x._2)).
      foreach(println)

    // What is the most unsafe time to be in the streets?
    data.
      map(rec => rec.split(",")).
      map(rec => (rec(2).split(" ")(1).split(":")(0) + " " + rec(2).split(" ")(2), 1)).
      reduceByKey(_+_).
      takeOrdered(5)(Ordering[Int].reverse.on(x=>x._2)).
      map(rec => (rec._1, rec._2, BigDecimal((rec._2.toDouble/6508475)*100).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble)).
      foreach(println)

    // What is the most safe time to be in the streets?
    data.
      map(rec => rec.split(",")).
      map(rec => (rec(2).split(" ")(1).split(":")(0) + " " + rec(2).split(" ")(2), 1)).
      reduceByKey(_+_).
      takeOrdered(5)(Ordering[Int].reverse.on(x=>x._2)).
      map(rec => (rec._1, rec._2, BigDecimal((rec._2.toDouble/6508475)*100).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble)).
      foreach(println)

    // Which is the most unsafe street
    val mostUnsafeStreetData = data.
                              map(rec => rec.split(",")).
                              map(rec => (rec(3), 1)).
                              reduceByKey(_+_).
                              takeOrdered(1)(Ordering[Int].reverse.on(x=>x._2))

    val mostUnsafeStreetName = mostUnsafeStreetData.map(rec => rec._1).mkString("")
    val mostUnsafeStreetCaseCount = mostUnsafeStreetData.map(rec => rec._2).mkString("").toInt

    println(mostUnsafeStreetName) // 100XX W OHARE ST
    println(mostUnsafeStreetCaseCount) // 14952

    data.
      filter(rec => rec.split(",")(3) == mostUnsafeStreetName).
      map(rec => (rec.split(",")(5), 1)).
      reduceByKey(_+_).
      map(rec => rec.swap).
      sortByKey(false).
      map(rec => (rec._2, rec._1, BigDecimal((rec._1.toDouble/mostUnsafeStreetCaseCount)*100).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble)).
      collect().
      foreach(println)


    // Which is the most safe street
    val mostSafeStreetData = data.
      map(rec => rec.split(",")).
      map(rec => (rec(3), 1)).
      reduceByKey(_+_).
      takeOrdered(1)(Ordering[Int].on(x=>x._2))

    val mostSafeStreetName = mostSafeStreetData.map(rec => rec._1).mkString("")
    val mostSafeStreetCaseCount = mostSafeStreetData.map(rec => rec._2).mkString("").toInt

    println(mostSafeStreetName) // 027XX E 126TH ST
    println(mostSafeStreetCaseCount) // 1

    data.
      filter(rec => rec.split(",")(3) == mostSafeStreetName).
      map(rec => (rec.split(",")(5), 1)).
      reduceByKey(_+_).
      map(rec => rec.swap).
      sortByKey(false).
      map(rec => (rec._2, rec._1, BigDecimal((rec._1.toDouble/mostSafeStreetCaseCount)*100).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble)).
      collect().
      foreach(println)

    // Is Chicago PD doing a good job?
    val dataFiltered = data.
                      map(rec => rec.split(",")).
                      filter(rec => rec(8) == "true" || rec(8) == "false")

    val dataFilteredCount = dataFiltered.count()

    dataFiltered.
      map(rec => (rec(8), 1)).
      reduceByKey(_+_).
      map(rec => (rec._1, rec._2, BigDecimal((rec._2.toDouble/dataFilteredCount)*100).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble)).
      collect().foreach(println)
  }
}
