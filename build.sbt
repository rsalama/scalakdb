name := "scalakdb"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.joda"      %  "joda-convert"      % "1.2",
  "joda-time"     %  "joda-time"         % "2.1",
  "log4j"         %  "log4j"             % "1.2.17",
  "org.mongodb"   %  "mongo-java-driver" % "2.7.2",
  "org.typelevel" %% "cats-effect"       % "0.5",
  "co.fs2"        %% "fs2-core"          % "0.9.7",
  "co.fs2"        %% "fs2-io"            % "0.9.7",
  "com.lihaoyi"   %% "ammonite"          % "1.0.3" % "test" cross CrossVersion.full,
  "commons-lang"  %  "commons-lang"      % "2.6"
)


// sbt scalakdb/test:run
sourceGenerators in Test += Def.task {
  val file = (sourceManaged in Test).value / "amm.scala"
  IO.write(file, """object amm extends App { ammonite.Main.main(args) }""")
  Seq(file)
}.taskValue

