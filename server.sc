object server {
  println("Welcome to the Scala worksheet")
  val k = new kx.c("localhost", 5010)
  println(k.k(".z.Z"))

	def stringOf(x : Any) = x match {
	  case a:Array[_] => "Array"
	  case f:kx.c.Flip => "Flip"
	  case _ => "Something else"
	}
  class Handler(val k: kx.c) extends Thread {
    override def run() {
      while (true) {
        val x = k.k()
        println(stringOf(x))
      }
    }
  }
  val h = new Thread(new Handler(k))
  h.start()
  h.join()
}