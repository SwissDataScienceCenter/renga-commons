/*
 * Copyright 2017 - Swiss Data Science Center (SDSC)
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

package ch.datascience.service.models.storage.json

import ch.datascience.service.models.resource.json.RequestTypeMappers
import ch.datascience.service.models.storage.CreateBucketRequest
import play.api.libs.functional.syntax._
import play.api.libs.json._

object CreateBucketRequestMappers {

  def CreateBucketRequestFormat: OFormat[CreateBucketRequest] = RequestTypeMappers.format( "create_bucket" )( (
    ( JsPath \ "name" ).format[String] and
    ( JsPath \ "backend" ).format[String] and
    ( JsPath \ "options" ).formatNullable[JsObject] and
    ( JsPath \ "labels" ).formatNullable[Seq[String]] and
    ( JsPath \ "project_id" ).formatNullable[String]
  )( read, write ) )

  private[this] def read(
      name:           String,
      backend:        String,
      backendOptions: Option[JsObject],
      labels:         Option[Seq[String]],
      projectId:      Option[String]
  ): CreateBucketRequest = {
    CreateBucketRequest(
      name,
      backend,
      backendOptions,
      labels.map( _.toSet ).getOrElse( Set.empty ),
      projectId.map( _.toLong )
    )
  }

  private[this] def write( request: CreateBucketRequest ): ( String, String, Option[JsObject], Option[Seq[String]], Option[String] ) = {
    val labels = if ( request.labels.isEmpty ) None else Some( request.labels.toSeq )
    ( request.name, request.backend, request.backendOptions, labels, request.projectId.map( _.toString ) )
  }

}
