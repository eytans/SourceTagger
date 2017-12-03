import org.stackexchange.dumps.importer.services.SofManagerImpl

// language=SQL

class DataStatistic {
  val em = SofManagerImpl.create.getEm
  val tagsQuery = em.createQuery("SELECT * FROM ")
}
