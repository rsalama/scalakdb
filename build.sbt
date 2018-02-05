name := "scalakdb"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies += "com.lihaoyi" %% "ammonite" % "1.0.3" % "test" cross CrossVersion.full

sourceGenerators in Test += Def.task {
  val file = (sourceManaged in Test).value / "amm.scala"
  IO.write(file, """object amm extends App { ammonite.Main.main(args) }""")
  Seq(file)
}.taskValue

