package ch.datascience.graph.scope.persistence.remote

import ch.datascience.graph.NamespaceAndName
import ch.datascience.graph.json.NamespaceAndNameReads

import scala.concurrent.ExecutionContext

/**
  * Created by johann on 17/05/17.
  */
class StandardRemotePersistenceLayer(override val client: StandardClient)(implicit override val executionContext: ExecutionContext)
  extends RemotePersistenceLayer[NamespaceAndName, NamespaceAndName](client)(NamespaceAndNameReads, NamespaceAndNameReads, executionContext)

object StandardRemotePersistenceLayer {

  def makeStandaloneRemotePersistenceLayer(baseUrl: String): StandardRemotePersistenceLayer = {
    import scala.concurrent.ExecutionContext.Implicits._

    val client = StandardClient.makeStandaloneClient(baseUrl)
    new StandardRemotePersistenceLayer(client)
  }

}
