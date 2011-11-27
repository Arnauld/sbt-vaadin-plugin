sbtPlugin := true

organization := "org.technbolts"

name := "sbt-vaadin-plugin"

scalaVersion := "2.9.1"

version <<= sbtVersion(v =>
  if(v.startsWith("0.11")) "0.0.2-SNAPSHOT"
  else if (v.startsWith("0.10")) "0.0.2-SNAPSHOT-%s".format(v)
  else error("unsupported sbt version %s" format v))

retrieveManaged := true // remove this once plugins are working or i understand their layout

publishTo := Some(Resolver.file("Local", Path.userHome / "Projects" / "arnauld.github.com" / "maven2" asFile)(Patterns(true, Resolver.mavenStyleBasePattern)))
