import org.apache.spark.ml.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import scala.collection.JavaConverters._
import sof.SqlQueries

class SofClassifier {
  val Array(training, test) = new PreProcessing((asScalaBufferConverter (new SqlQueries getAllQuestions)).asScala.toStream).apply().randomSplit(Array(0.7, 0.3))

  val model = new NaiveBayes().fit(training)

  val predictions = model.transform(test)
  val evaluator = new MulticlassClassificationEvaluator()
    .setLabelCol("label")
    .setPredictionCol("prediction")
    .setMetricName("accuracy")
  val accuracy = evaluator.evaluate(predictions)
  println("Test set accuracy = " + accuracy)

  // Save and load model
}
