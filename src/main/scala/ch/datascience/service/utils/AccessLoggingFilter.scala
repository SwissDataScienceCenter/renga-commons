/*
 * Copyright 2017-2018 - Swiss Data Science Center (SDSC)
 * A partnership between École Polytechnique Fédérale de Lausanne (EPFL) and
 * Eidgenössische Technische Hochschule Zürich (ETHZ).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.datascience.service.utils

import akka.stream.Materializer
import javax.inject.Inject
import play.api.Logger
import play.api.mvc.{ Filter, RequestHeader, Result }

import scala.concurrent.{ ExecutionContext, Future }
import scala.util.{ Failure, Success }

class AccessLoggingFilter @Inject() (
    implicit
    val mat: Materializer
) extends Filter {

  lazy val logger: Logger = Logger( "AccessLoggingFilter" )

  def apply( f: RequestHeader => Future[Result] )( rh: RequestHeader ): Future[Result] = {
    logger.info( s"Received request ${rh.id} - ${rh.path}" )

    implicit val ex: ExecutionContext = mat.executionContext
    val futureResult = f( rh )

    futureResult.onComplete {
      case Success( result ) =>
        logger.info( s"Responded ${result.header.status} to ${rh.id} - ${rh.path}" )
      case Failure( _ ) =>
        logger.error( s"Failed request ${rh.id} - ${rh.path}" )
    }

    futureResult
  }

}
