import sbt._
import sbt.Keys._

object ShowcaseBuild extends samskivert.MavenBuild {

  override val globalSettings = Seq(
    crossPaths    := false,
    javacOptions  ++= Seq("-Xlint", "-Xlint:-serial", "-source", "1.6", "-target", "1.6"),
    javaOptions   ++= Seq("-ea"),
    fork in Compile := true,
    autoScalaLibrary in Compile := false // no scala-library dependency
  )

  override def moduleSettings (name :String, pom :pomutil.POM) = name match {
    case "java" => LWJGLPlugin.lwjglSettings ++ seq(
      LWJGLPlugin.lwjgl.version := pom.getAttr("lwjgl.version").get
    ) ++ spray.revolver.RevolverPlugin.Revolver.settings
    case _ => Nil
  }

  override def profiles = Seq("java")
}
