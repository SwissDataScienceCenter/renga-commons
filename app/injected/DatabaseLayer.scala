package injected

import javax.inject.Inject

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import ch.datascience.typesystem.relationaldb.DatabaseStack
import groovy.lang.Singleton
import play.db.NamedDatabase
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

/**
  * Created by johann on 13/04/17.
  */
class DatabaseLayer @Inject()(@NamedDatabase("sqldb") protected val dbConfigProvider : DatabaseConfigProvider)
  extends DatabaseStack(dbConfigProvider.get)
