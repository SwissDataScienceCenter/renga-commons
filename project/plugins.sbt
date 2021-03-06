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

// Scalariform plugin for code formatting
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.2")

// Sonatype plugin
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "2.3")

// SBT PGP plugin
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.1")
