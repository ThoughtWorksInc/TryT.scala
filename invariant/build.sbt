enablePlugins(Example)

import scala.meta._
exampleSuperTypes += ctor"_root_.org.scalatest.Inside"

libraryDependencies += "org.scalaz" %%% "scalaz-core" % "7.2.29"

libraryDependencies += "org.scalaz" %%% "scalaz-effect" % "7.2.29"

libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.8" % Test

libraryDependencies += "org.scalaz" %% "scalaz-concurrent" % "7.2.29" % Test

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3")

sourceGenerators in Test := {
  (sourceGenerators in Test).value.filterNot { sourceGenerator =>
    import Ordering.Implicits._
    VersionNumber(scalaVersion.value).numbers >= Seq(2L, 13L) &&
    sourceGenerator.info
      .get(taskDefinitionKey)
      .exists { scopedKey: ScopedKey[_] =>
        scopedKey.key == generateExample.key
      }
  }
}
