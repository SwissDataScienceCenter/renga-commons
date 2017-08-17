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

package ch.datascience.service.models.deployment.json

import ch.datascience.service.models.deployment.ContainerDeploymentOptions
import play.api.libs.functional.syntax._
import play.api.libs.json._

object ContainerDeploymentOptionsMappers {

  def ContainerDeploymentOptionsFormat: OFormat[ContainerDeploymentOptions] = (
    ( JsPath \ "backend" ).formatNullable[String] and
    ( JsPath \ "image" ).format[String] and
    ( JsPath \ "environment" ).formatNullable[Map[String, String]] and
    ( JsPath \ "ports" ).formatNullable[Seq[Int]] and
    ( JsPath \ "entrypoint" ).formatNullable[String] and
    ( JsPath \ "command" ).formatNullable[Seq[String]]
  )( read, write )

  private[this] def read(
      backend:     Option[String],
      image:       String,
      environment: Option[Map[String, String]],
      ports:       Option[Seq[Int]],
      entrypoint:  Option[String],
      command:     Option[Seq[String]]
  ): ContainerDeploymentOptions = {
    ContainerDeploymentOptions( backend, image, environment.getOrElse( Map.empty ), ports.map( _.toSet ).getOrElse( Set.empty ), entrypoint, command )
  }

  private[this] def write( options: ContainerDeploymentOptions ): ( Option[String], String, Option[Map[String, String]], Option[Seq[Int]], Option[String], Option[Seq[String]] ) = {
    ( options.backend, options.image, Some( options.environment ), Some( options.ports.toSeq ), options.entrypoint, options.command )
  }

}