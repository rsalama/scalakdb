package org.lc.qscala
import org.scalatest._

class RichMapSpec extends FlatSpec with Matchers {
  "Drop existing elements from Map" should "return a map with less elements" in {
    val m = Map(("a" -> "FOO"), ("b" -> 3.14159), ("c" -> 42))
    m.drop("a" :: "b" :: Nil) shouldEqual Map("c" -> 42)
  }
  "Drop non-existing elements from Map" should "return a map with less elements" in {
    val m = Map(("a" -> "FOO"), ("b" -> 3.14159), ("c" -> 42))
    m.drop("a" :: "z" :: Nil) shouldEqual Map(("b" -> 3.14159), ("c" -> 42))
  }
}