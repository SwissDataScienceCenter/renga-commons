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

package ch.datascience.graph.elements.simple

import ch.datascience.graph.elements.{BoxedValue, ValidValue}

/**
  * Created by johann on 28/04/17.
  */
final case class SimpleVertexProperty[Key, Value : ValidValue, MetaKey](
    override val key: Key,
    override val value: Value,
    override val properties: Map[MetaKey, SimpleProperty[MetaKey, BoxedValue]]
) extends SimpleVertexPropertyBase[Key, Value, MetaKey, SimpleProperty](key, value, properties) {

  type SimpleVertexPropertyKV[K, V] = SimpleVertexProperty[K, V, MetaKey]

}

object SimpleVertexProperty {

  def apply[Key, Value: ValidValue, MetaKey](key: Key, value: Value): SimpleVertexProperty[Key, Value, MetaKey] = {
    SimpleVertexProperty(key, value, Map.empty)
  }

}