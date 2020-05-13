package org.lc.qscala

import scala.concurrent._
import scala.util.{ Try, Success, Failure }
import java.util.Date
import java.text.SimpleDateFormat

object server extends App {
  def stringOf(x: Any): String = x match {
    case a: Array[_]  => "(" + a.foldLeft("")((acc, el) => acc + (if (acc eq "") "" else ";") + stringOf(el)) + ")"
    case f: kx.c.Flip => s"Flip: ${f.x.length} x ${f.y.length}" // f.toString
    case d: kx.c.Dict => d.toMap.toString
    case _            => if (x != null) x.toString else "NULL"
  }

  var promises = Map[String, Promise[_]]()

  val funcs: Map[String, (Any) => Unit] = Map(
    ("Show" -> ((x: Any) => println(s"show (${Thread.currentThread.getName}): ${stringOf(x)}")))) withDefaultValue 
    ((x: Any) => println(s"Error (${Thread.currentThread.getName}): ${stringOf(x)}"))

  class Handler(val k: kx.c) extends Thread {
    var running = true
    def end() = running = false
    override def run() {
      println("Starting...")
      while (true) {
        Try(k.k()) match {
          case Success(x) => x match {
            case a: Array[_] => funcs(a.head.toString)(a.tail)
            case b @ _       => println(s"Any: ${stringOf(b)}")
          }
          case Failure(ex) => println(s"${ex.getClass.getName}: ${ex.getMessage}")
        }
        /*
        val x = k.k()
        println(x)
        x match {
          case a: Array[_] =>
            funcs(a.head.toString)(a.tail)
          case b @ _ =>
            println(s"Any: ${stringOf(b)}")
        }
         */
      }
    }
  }

  val df = new SimpleDateFormat("yyyy.MM.dd")
  val k = new kx.c("localhost", 5010)

  val h = new Thread(new Handler(k))
  h.start()
  val ns: Seq[Object] = (0 to 5) map (_.asInstanceOf[Object])
  k.ks("{(neg .z.w) 0N!(z; x*y)}", 6, 7, "show")
  k.ks("{(neg .z.w) 0N!.[{(z;(3*).(x;y))};(x;y;z);{(`err;`$x)}]}", 6, 7, "show")
  k.ks("{(neg .z.w) 0N!.[{(z;(*).(x;y))};(x;y;z);{(`err;`$x)}]}", 16, 17, "show")

  /*
  k.ks("{(neg .z.w) 0N!.[{(`show;x . y)};y;{(`err;`$x)}]}", "(*)".asInstanceOf[Any], Array(6, 7))
  k.ks("{(neg .z.w) 0N!(z; *[3;x;y])}", 6, 7, "show")
  k.ks("{(neg .z.w) 0N!(z; .[*[3;x;y];(x;y);`err])}", 6, 7, "show")
  k.ks("{[a;b;c;d;e;f;g] (neg .z.w) 0N!(a;b;c;d;e;f;g)}", "show", ns(0), ns(1), ns(2), ns(3), ns(4), ns(5))
  k.ks("{(neg .z.w) 0N!(z; x cut til y)}", 10, 100, "show")
  k.ks("{(neg .z.w) 0N!(y; exec (c!t) from meta x)}", "a".asInstanceOf[Any], "show")
  k.ks("{[t;dts;tcks;cb] (neg .z.w) 0N!(cb;?[t;((within;`dt;dts);(in;`tck;tcks));0b;()])}", "a", Array(df.parse("1999.01.01"), df.parse("1999.12.13")), Array("aapl"), "show")
  * 
  */
}