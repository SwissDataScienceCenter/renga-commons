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

package ch.datascience.service.models.resources.json

import ch.datascience.service.models.resources._
import play.api.libs.functional.syntax._
import play.api.libs.json._


object ResourceRequestMappers {

  def resourcesRequestReads: Reads[ResourceRequest] = (
    (JsPath \ "app_id").readNullable[Long] and
      (JsPath \ "type").read[String].flatMap {
        case "read:file" => (JsPath \ "details").read[ReadResourceRequest]
        case "write:file" => (JsPath \ "details").read[WriteResourceRequest]
        case "create:bucket" => (JsPath \ "details").read[CreateBucketRequest]
      }
    )(ResourceRequest.apply _)

  def resourcesRequestWrites: Writes[ResourceRequest] = (
    (JsPath \ "app_id").writeNullable[Long] and
      (JsPath \ "type").write[String] and
      (JsPath \ "details").write[ResourceRequestDetails]
    ) { rr => rr.details match {
    case _: ReadResourceRequest => (rr.appId, "read:file", rr.details)
    case _: WriteResourceRequest => (rr.appId, "write:file", rr.details)
    case _: CreateBucketRequest => (rr.appId, "create:bucket", rr.details)
}}
}

