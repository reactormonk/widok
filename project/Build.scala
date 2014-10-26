import sbt._
import sbt.Keys._
import scoverage.ScoverageSbtPlugin._
import scala.scalajs.sbtplugin.ScalaJSPlugin._
import org.typelevel.sbt.Developer
import org.typelevel.sbt.TypelevelPlugin._

object Build extends sbt.Build {
  val buildOrganisation = "org.widok"
  val buildScalaVersion = "2.11.2"
  val buildScalaOptions = Seq(
    "-unchecked", "-deprecation",
    "-encoding", "utf8")

  lazy val main = Project(id = "widok", base = file("."))
    .settings(scalaJSSettings: _*)
    .settings(instrumentSettings: _*)
    .settings(typelevelDefaultSettings ++ Seq(
      TypelevelKeys.signArtifacts := true,
      TypelevelKeys.githubDevs += Developer("Tim Nieradzik", "tindzk"),
      TypelevelKeys.githubProject := ("widok", "widok")
    ): _*)
    .settings(
      resolvers += "bintray-alexander_myltsev" at "http://dl.bintray.com/content/alexander-myltsev/maven",
      libraryDependencies ++= Seq(
        "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6",
        "org.scala-lang.modules.scalajs" %% "scalajs-jasmine-test-framework" % scalaJSVersion % "test",
        "name.myltsev" %% "shapeless_sjs0.5" % "2.0.0"
      ),
      organization := buildOrganisation,
      scalaVersion := buildScalaVersion,
      scalacOptions := buildScalaOptions,
      ScalaJSKeys.persistLauncher := true
    )
}
