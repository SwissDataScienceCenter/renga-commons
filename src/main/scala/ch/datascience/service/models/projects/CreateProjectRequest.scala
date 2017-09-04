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

package ch.datascience.service.models.projects

import ch.datascience.service.models.resource.{ ScopeQualifier, SingleScopeAccessRequest }
import play.api.libs.json.JsObject

case class CreateProjectRequest( name: String ) extends SingleScopeAccessRequest.ToSingleScopeAccessRequest {

  def toAccessRequest( extraClaims: Option[JsObject] ): SingleScopeAccessRequest = {
    SingleScopeAccessRequest( permissionHolderId = None, CreateProjectRequest.scope, extraClaims )
  }

}

object CreateProjectRequest {
  lazy val scope: ScopeQualifier = ScopeQualifier.ProjectCreate
}