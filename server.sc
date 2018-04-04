import scala.concurrent._
import java.util.Date
import java.text.SimpleDateFormat;

object server {
  println("Welcome to the Scala worksheet")

  def stringOf(x: Any): String = x match {
    case a: Array[_]  => "(" + a.foldLeft("")((acc, el) => acc + (if (acc eq "") "" else ";") + stringOf(el)) + ")"
    case f: kx.c.Flip => "Flip"
    case _            => if (x != null) x.toString else "NULL"
  }

  var promises = Map[String, Promise[_]]()

  val funcs: Map[String, (Any) => Unit] = Map(
    ("show" -> ((x: Any) => println(s"show (${Thread.currentThread.getName}): ${stringOf(x)}"))))

  class Handler(val k: kx.c) extends Thread {
    var running = true
    def end() = running = false
    override def run() {
      println("Starting...")
      while (true) {
        val x = k.k()
        println(s"x: $x (${x.getClass.getName}")
        x match {
          case a: Array[_] =>
            println(s"a -> head: ${a.head} ${a.mkString(",")}")
          // funcs(a.head.toString)(a.tail)
          case b @ _ =>
            println(s"b -> $b")
            println(stringOf(b))
        }
      }
    }
  }
  val k = new kx.c("localhost", 5010)
  val d = new Date(1999,1,1)
  new SimpleDateFormat("yyyy.MM.dd").parse("1999.01.01")
  
  k.ks("{(neg .z.w) 0N!(z; *[3;x;y])}", 6, 7, "show")
  val x = k.k()
  println(x)
  /*
  println(k.k(".z.Z"))
  val r = k.k("{[a;b;c;d;e;f;g] (neg .z.w) 0N!(a;b;c;d;e;f;g)}", "foo", 0.asInstanceOf[Object], 1.asInstanceOf[Object], 2.asInstanceOf[Object], 3.asInstanceOf[Object], 4.asInstanceOf[Object], 5.asInstanceOf[Object])
  */

/*
  val h = new Thread(new Handler(k))
  h.start()
  Thread.sleep(1000)
  */
  // println(">>> HERE")

  // k.ks("0N!.z.Z")
  // val a = List("{0N!(x;y;z); (neg .z.w) ((value z);x*y)}", 6, 7, "show")
  // k.ks("{(neg .z.w) 0N!(z; x*y)}", 6, 7, "show")
  /*
  k.ks("{[a;b;c;d;e;f;g] (neg .z.w) 0N!(a;b;c;d;e;f;g)}", "show", 0.asInstanceOf[Object], 1.asInstanceOf[Object], 2.asInstanceOf[Object], 3.asInstanceOf[Object], 4.asInstanceOf[Object], 5.asInstanceOf[Object])
  val zs = (1 to 5) zip (101 to 105)
  zs foreach { p => k.ks(s"{[x;y;z] (neg .z.w) 0N!(z; `${Thread.currentThread.getName}; (x;y); x*y)}", p._1, p._2, "show") }
  // k.ks("0N!({0N!x*y};6;7)")
  h.join()
  */
}