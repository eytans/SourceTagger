import org.scalatest.Matchers
import org.scalatest.concurrent.TimeLimitedTests
import org.scalatest.time.{Millis, Minutes, Span}
import org.stackexchange.dumps.importer.domain.Tag
import sof.SqlQueries

class SqlQueriesTest extends org.scalatest.FlatSpec with Matchers with TimeLimitedTests {

  val timeLimit = Span(3, Minutes)
  val data = new SqlQueries

  "TagQuery" should "have more then one result" in {
    data.getAll[Tag](classOf[Tag]).size should be > 1
  }

  it should "keep values after run" in {
    data.getAll[Tag](classOf[Tag]).get(0)
    val data2 = new SqlQueries
    data2.getAll[Tag](classOf[Tag]).size should be > 1
  }

  "Post with Answer" should "be not empty" in {
    val res = data.joinPostWithAnswer()
    res.size() should be > 0
  }

}
