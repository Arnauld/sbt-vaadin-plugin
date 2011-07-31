sbtPlugin := true

organization := "org.technbolts"

name := "sbt-vaadin-plugin"

version := "0.0.1-SNAPSHOT"

retrieveManaged := true // remove this once plugins are working or i understand their layout

publishMavenStyle := true

publishTo := Some(Resolver.file("Local", Path.userHome / "Projects" / "arnauld.github.com" / "maven2" asFile)(Patterns(true, Resolver.mavenStyleBasePattern)))
