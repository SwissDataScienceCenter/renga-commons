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

package ch.datascience.service.models.resources

/**
  * Created by johann on 13/07/17.
  */
sealed abstract class ResourceScope(val name: String)

object ResourceScope {

  val scopes: Set[ResourceScope] = Set(StorageRead, StorageWrite, StorageCreate)

  def valueOf(name: String): ResourceScope = ResourceScope.apply(name)

  def apply(name: String): ResourceScope = name.toLowerCase match {
    case StorageRead.name => StorageRead
    case StorageWrite.name => StorageWrite
    case StorageCreate.name => StorageCreate
  }

  case object StorageRead extends ResourceScope("storage:read")

  case object StorageWrite extends ResourceScope("storage:write")

  case object StorageCreate extends ResourceScope("storage:create")

}
