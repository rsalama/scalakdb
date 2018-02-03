object server {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val k = new kx.c("localhost", 5010)             //> k  : kx.c = kx.c@6e1f8f
  println(k.k(".z.Z"))                            //> Sat Feb 03 18:42:27 EST 2018

	def stringOf(x : Any) : String = x match {
	  case a:Array[_] => "[" + a.foldLeft("")((acc, el) => acc + (if (acc eq "") "" else ", ") + stringOf(el)) + "]"
	  case f:kx.c.Flip => "Flip"
	  case _ => x.toString
	}                                         //> stringOf: (x: Any)String
  class Handler(val k: kx.c) extends Thread {
    override def run() {
      while (true) {
        val x = k.k()
        println(stringOf(x))
      }
    }
  }
  val h = new Thread(new Handler(k))              //> h  : Thread = Thread[Thread-1,5,main]
  h.start()
  h.join()                                        //> 10
                                                  //| [,0,1,2,3,4,5,6,7,8,9]|
}