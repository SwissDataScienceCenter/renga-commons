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

organization := "ch.datascience"
version := "0.2.0"
scalaVersion := "2.12.6"
name := "renku-commons"

lazy val play_version = "2.6.15"
libraryDependencies += "com.typesafe.play" %% "play" % play_version
lazy val play_json_version = "2.6.9"
libraryDependencies += "com.typesafe.play" %% "play-json" % play_json_version
libraryDependencies += "com.typesafe.play" %% "play-ahc-ws" % play_version
libraryDependencies += "com.typesafe.play" %% "play-test" % play_version

lazy val java_jwt_version = "3.3.0"
libraryDependencies += "com.auth0" % "java-jwt" % java_jwt_version

libraryDependencies += "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % "2.8.11"

// Source code formatting
import scalariform.formatter.preferences._

scalariformPreferences := scalariformPreferences.value
  .setPreference( AlignArguments,                               true  )
  .setPreference( AlignParameters,                              true  )
  .setPreference( AlignSingleLineCaseStatements,                true  )
  .setPreference( AlignSingleLineCaseStatements.MaxArrowIndent, 40    )
  .setPreference( CompactControlReadability,                    true  )
  .setPreference( CompactStringConcatenation,                   false )
  .setPreference( DanglingCloseParenthesis,                     Force )
  .setPreference( DoubleIndentConstructorArguments,             true  )
  .setPreference( DoubleIndentMethodDeclaration,                true  )
  .setPreference( FirstArgumentOnNewline,                       Force )
  .setPreference( FirstParameterOnNewline,                      Force )
  .setPreference( FormatXml,                                    true  )
  .setPreference( IndentPackageBlocks,                          true  )
  .setPreference( IndentSpaces,                                 2     )
  .setPreference( IndentWithTabs,                               false )
  .setPreference( MultilineScaladocCommentsStartOnFirstLine,    false )
  .setPreference( NewlineAtEndOfFile,                           true  )
  .setPreference( PlaceScaladocAsterisksBeneathSecondAsterisk,  false )
  .setPreference( PreserveSpaceBeforeArguments,                 false )
  .setPreference( RewriteArrowSymbols,                          false )
  .setPreference( SpaceBeforeColon,                             false )
  .setPreference( SpaceBeforeContextColon,                      true  )
  .setPreference( SpaceInsideBrackets,                          false )
  .setPreference( SpaceInsideParentheses,                       true  )
  .setPreference( SpacesAroundMultiImports,                     true  )
  .setPreference( SpacesWithinPatternBinders,                   false )

// Publishing
sonatypeProfileName := "ch.datascience"
publishMavenStyle := true
pomIncludeRepository := { _ => false }
licenses := Seq("The Apache License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://datascience.ch/renku-platform/"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/SwissDataScienceCenter/renku-commons"),
    "scm:git@github.com:SwissDataScienceCenter/renku-commons.git"
  )
)
developers := List(
  Developer(id="leafty", name="Johann-Michael Thiebaut", email="johann.thiebaut@gmail.com", url=url("https://github.com/leafty"))
)
publishTo := {
  if (isSnapshot.value)
    Some(Opts.resolver.sonatypeSnapshots)
  else
    Some(Opts.resolver.sonatypeStaging)
}
publishArtifact in Test := false

credentials ++= (for {
  username <- sys.env.get("SONATYPE_USERNAME")
  password <- sys.env.get("SONATYPE_PASSWORD")
} yield Credentials(
  "Sonatype Nexus Repository Manager",
  "oss.sonatype.org",
  username,
  password)).toSeq

pgpPublicRing := baseDirectory.value / "project" / ".gnupg" / "pubring.gpg"
pgpSecretRing := baseDirectory.value / "project" / ".gnupg" / "secring.gpg"
