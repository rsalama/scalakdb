package org.lc

package object qscala {
  implicit class RichMap[K, V](m : Map[K, V]) {
    def apply(ks: Seq[K]) : Seq[V] = ks collect {case k if (m.contains(k)) => m(k)}
    def take(ks: Seq[K]) : Map[K, V] = ks collect {case k if (m.contains(k)) => (k -> m(k))} toMap
    def drop1(ks: Seq[K]) : Map[K, V] = take(m.keys.toSeq intersect ks)
    def drop(ks: Seq[K]) : Map[K, V] = take(m.keys.filterNot(ks.toSet).toSeq)
  }
}