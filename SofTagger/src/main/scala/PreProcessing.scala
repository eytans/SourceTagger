import org.stackexchange.dumps.importer.domain.Post
import org.stackexchange.querying._

import scala.collection.immutable.HashMap

class PreProcessing(posts: Stream[Post]) {
  private val legalIdentRE =
    "^[a-zA-Z_$][a-zA-Z\\d_$]*$".r

  def extractCode(post: String): String = {
    // TODO: implement
    post
  }

  val words = posts map ((post: Post) => extractCode(post.getAnswer.getBody)) map (_.split(Array(' ', ';', '\n', '.', '(', ')', '{', '}', ',')))

  val vocab: Map[String, Int] = HashMap[String, Int](words.flatten.distinct.zipWithIndex: _*)

  val proccessed = {
    words map (_.filter (s => legalIdentRE.findFirstIn(s).isDefined))
  }

  val bow = {

  }
}

