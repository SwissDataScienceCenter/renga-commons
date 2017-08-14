organization := "ch.datascience"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.11.8"

lazy val projectName = "service-commons"
name := projectName

lazy val root = Project(
  id   = projectName,
  base = file(".")
).dependsOn(
  core
)

lazy val core = RootProject(file("../graph-core"))

resolvers += DefaultMavenRepository
resolvers += "jitpack" at "https://jitpack.io"
resolvers += "Oracle Released Java Packages" at "http://download.oracle.com/maven"

lazy val play_version = "2.5.14"
libraryDependencies += "com.typesafe.play" %% "play" % play_version
libraryDependencies += "com.typesafe.play" %% "play-json" % play_version
libraryDependencies += "com.typesafe.play" %% "play-ws" % play_version
libraryDependencies += "com.typesafe.play" %% "play-test" % play_version % Test

lazy val java_jwt_version = "3.2.0"
libraryDependencies += "com.auth0" % "java-jwt" % java_jwt_version

lazy val janusgraph_version = "0.1.0"
libraryDependencies += "org.janusgraph" % "janusgraph-core" % janusgraph_version

// Source code formatting
import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

val preferences =
  ScalariformKeys.preferences := ScalariformKeys.preferences.value
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

SbtScalariform.scalariformSettings ++ Seq(preferences)
