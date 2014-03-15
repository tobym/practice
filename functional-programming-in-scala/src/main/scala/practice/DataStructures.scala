package practice.datastructures

sealed trait Lst[+A] {
  /** Append given list. */
  def ++[B >: A](other: Lst[B]): Lst[B]
}
case object Nil extends Lst[Nothing] {
  def ++[B >: Nothing](other: Lst[B]): Lst[B] = other
}
case class Cons[+A](head: A, tail: Lst[A]) extends Lst[A] {
  def ++[B >: A](other: Lst[B]): Lst[B] = Cons[B](head, tail ++ other)
}

object Lst {
  def apply[A](as: A*): Lst[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}
