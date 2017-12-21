import org.apache.spark
import org.apache.spark.SparkContext
import org.stackexchange.dumps.importer.domain.Post
import org.stackexchange.querying._
import org.apache.spark.ml.feature.{CountVectorizer, CountVectorizerModel, Tokenizer}
import org.apache.spark.ml.feature.{HashingTF, IDF}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.Encoder
import org.apache.spark.sql.Encoders

import scala.collection.immutable.HashMap

class PreProcessing(posts: Stream[Post]) {
  val sparkConf = new spark.SparkConf().setMaster("local")
  val sparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
  import sparkSession.implicits._

  private val legalIdentRE =
    "^[a-zA-Z_$][a-zA-Z\\d_$]*$".r

  def extractCode(post: String): String = {
    // TODO: implement
    post
  }

  val words = posts map ((post: Post) => extractCode(post.getAnswer.getBody)) map (_.split(Array(' ', ';', '\n', '.', '(', ')', '{', '}', ',')))

  val proccessed = {
    words map (_.filter (s => legalIdentRE.findFirstIn(s).isDefined))
  }

  val sentenceData = sparkSession.createDataFrame(Stream.continually(true) zip proccessed).toDF("label", "words")

  val tf = new CountVectorizer()
    .setInputCol("words")
    .setOutputCol("rawFeatures")
    .setMinDF(0.05)
    .fit(sentenceData)

  val featurizedData = tf.transform(sentenceData)
  // alternatively, CountVectorizer can also be used to get term frequency vectors

  val idf = new IDF().setInputCol("rawFeatures").setOutputCol("features")
  val idfModel = idf.fit(featurizedData)

  val rescaledData = idfModel.transform(featurizedData)
  rescaledData.select("label", "features").show()
}

