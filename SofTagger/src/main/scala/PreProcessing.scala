import org.stackexchange.dumps.importer.services.{GenericImporter, GenericOurStaff, GenericOurStaffImpl}
import org.stackexchange.querying._

class PreProcessing {
  val post: PostR = null
  val ourStuff: GenericOurStaff = GenericOurStaffImpl.create()
  ourStuff.getEm.find(classOf[PostR], 1)
  ourStuff.getEm.createQuery("SELECT * FROM post p")
}

