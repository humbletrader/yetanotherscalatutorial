import Dependencies._

ThisBuild / scalaVersion     := "3.1.1"
ThisBuild / version          := "1.0.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val myproject = (project in file("."))
  .settings(
    name := "yetanotherscalatutorial",
    libraryDependencies += scalaTest % Test
  )

lazy val docs = project       // new documentation project
  .in(file("generated-docs")) // important: it must not be docs/
  .settings(mdocVariables := Map("VERSION" -> version.value))
  .dependsOn(myproject)
  .enablePlugins(MdocPlugin)
