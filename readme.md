Usage
=====

Add the plugin to your project in `project/plugins/build.sbt`:

    resolvers += "Arnauld" at "https://github.com/Arnauld/arnauld.github.com/raw/master/maven2",

e.g.

    resolvers ++= Seq(
      "Web plugin repo" at "http://siasia.github.com/maven2",
      "Arnauld" at "https://github.com/Arnauld/arnauld.github.com/raw/master/maven2"
    )

    libraryDependencies <++= sbtVersion({ sbt_version:String => 
        Seq("com.github.siasia" %% "xsbt-web-plugin" % sbt_version,
            "org.technbolts" %% "sbt-vaadin-plugin" %  "0.0.1-SNAPSHOT")})


Add the Web and Vaadin settings to your project in `build.sbt`:

e.g.

    seq((webSettings ++ vaadinSettings ++ Seq(
      jettyPort := 8081,
      vaadinWidgetSet := "scalaadin.gwt.CombinedWidgetset",
    )) :_*)


This override the jetty port, and define the name of the `widgetset`

To generate the widgetset used by vaadin

    sbt> vaadin-generate-widgetset
    ...

According to the settings, this will generate `scalaadin/gwt/CombinedWidgetset.gwt.xml` in `src/main/resources`

To compile the widgetset

    sbt> vaadin-compile-widgetset
    ...

According to the settings, this will generate all GWT files in `src/main/webapp/VAADIN/`


Settings
--------

* `gwtVersion` by default `2.3.0`
* `vaadinVersion` by default `6.6.3`
* `vaadinWidgetSet` the name of the combined widget set that aggregates all widgetset found in the classpath to a single one.
* `vaadinClientWidgetSetDestination` define where GWT compiles the widget, by default on `src/main/webapp/VAADIN/widgetsets`

Example:

    seq((webSettings ++ vaadinSettings ++ Seq(
      jettyPort := 8081,
      vaadinWidgetSet := "scalaadin.gwt.CombinedWidgetset",
      vaadinVersion := "6.6.3"
    )) :_*)


Vaadin
------

In order to use the widgetset, one must override the default widgetset used by Vaadin.
In `web.xml` add the widgetset declaration

    <init-param>
        <param-name>widgetset</param-name>
        <param-value>scalaadin.gwt.CombinedWidgetset</param-value>
    </init-param>

e.g.

    ...
    <servlet>
        <servlet-name>ScalaadinApplication</servlet-name>
        <servlet-class>com.vaadin.terminal.gwt.server.ApplicationServlet</servlet-class>
        <init-param>
            <description>Vaadin application class to start</description>
            <param-name>application</param-name>
            <param-value>scalaadin.ScalaadinApplication</param-value>
        </init-param>
        <init-param>
            <param-name>widgetset</param-name>
            <param-value>scalaadin.gwt.CombinedWidgetset</param-value>
        </init-param>
    </servlet>


Inspirations and related
========================

* [SBT 0.10.0](https://github.com/harrah/xsbt/wiki)
* [SBT web plugin](https://github.com/siasia/xsbt-web-plugin)
* [GWT web plugin](https://github.com/thunderklaus/sbt-gwt-plugin)
