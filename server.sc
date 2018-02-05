object server {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def stringOf(x: Any): String = x match {
    case a: Array[_]  => "(" + a.foldLeft("")((acc, el) => acc + (if (acc eq "") "" else ";") + stringOf(el)) + ")"
    case f: kx.c.Flip => "Flip"
    case _            => if (x != null) x.toString else "NULL"
  }                                               //> stringOf: (x: Any)String

  val funcs: Map[String, (Any) => Unit] = Map(
    ("show" -> ((x: Any) => println(s"show (${Thread.currentThread.getName}): ${stringOf(x)}"))))
                                                  //> funcs  : Map[String,Any => Unit] = Map(show -> <function1>)

  class Handler(val k: kx.c) extends Thread {
    override def run() {
      while (true) {
        val x = k.k()
        x match {
          case a: Array[_] => funcs(a.head.toString)(a.tail)
          case _           => println(stringOf(x))
        }
      }
    }
  }
  val k = new kx.c("localhost", 5010)
  println(k.k(".z.Z"))
  val r = k.k("{[a;b;c;d;e;f;g] (neg .z.w) 0N!(a;b;c;d;e;f;g)}", "foo", 0.asInstanceOf[Object], 1.asInstanceOf[Object], 2.asInstanceOf[Object], 3.asInstanceOf[Object], 4.asInstanceOf[Object], 5.asInstanceOf[Object])
	
  val h = new Thread(new Handler(k))
  h.start()
  Thread.sleep(1000)
  println(">>> HERE")

  k.ks("0N!.z.Z")
  k.ks("{(neg .z.w) (z; 0N!x*y)}", 6, 7, "show")
  k.ks("{[a;b;c;d;e;f;g] (neg .z.w) 0N!(a;b;c;d;e;f;g)}", "show", 0.asInstanceOf[Object], 1.asInstanceOf[Object], 2.asInstanceOf[Object], 3.asInstanceOf[Object], 4.asInstanceOf[Object], 5.asInstanceOf[Object])
  val zs = (1 to 20) zip (101 to 120)
  zs foreach { p => k.ks(s"{[x;y;z] (neg .z.w) 0N!(z; `${Thread.currentThread.getName}; (x;y); x*y)}", p._1, p._2, "show") }
  // zs foreach { p => k.ks(s"""{[x;y;z] (neg .z.w) 0N!(z; `$$"${Thread.currentThread.getName}"; (x;y); x*y)}""", p._1, p._2, "show") }
  // k.ks("0N!({0N!x*y};6;7)")
  h.join()
}