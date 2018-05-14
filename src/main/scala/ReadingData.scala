import org.apache.spark.{SparkConf, SparkContext}

object ReadingData {

  val conf = new SparkConf().setAppName("Chicago Crime Data Analysis").setMaster("local")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {

    val data = sc.textFile("data/Crimes_-_2001_to_present .csv")

    val header = data.first()

    val dataWithoutHeader = data.filter(rec => rec != header)

    dataWithoutHeader.saveAsTextFile("data/CrimeDataWithoutHeader.csv")

  }
}