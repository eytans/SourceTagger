import org.scalatest.{FunSuite, Matchers}
import org.stackexchange.dumps.importer.domain.Post
import org.scalamock.scalatest.MockFactory
import org.stackexchange.querying.PostR

class PreProcessingTest extends FunSuite with Matchers with MockFactory {
  test("testProccessed") {
    val mockedPost = mock[PostR]
    (mockedPost.getAnswer _).expects().returning(mockedPost)
    (mockedPost.getBody _).expects().returning("hello world \"a string\".pdate(whatever)")

    val results = new PreProcessing(mockedPost #:: Stream.empty).proccessed
    println(results.flatten.mkString(" "))
    results.filter(_.length > 0).length should be > 0
    results.flatten.mkString(" ") should contain noElementsOf Seq('(', '.', '"')
  }
}
