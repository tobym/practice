package practice.chapter3

import annotation.tailrec

object Exercise2 {
  def tail[A](as: List[A]): List[A] = as match {
    case Nil => throw new Exception("called tail on empty list")
    case h :: t => t
  }
}

object Exercise3 {
  def setHead[A](a: A, as: List[A]): List[A] = as match {
    case Nil => a :: Nil
    case h :: t => a :: t
  }
}

object Exercise4 {
  @tailrec
  def drop[A](l: List[A], n: Int): List[A] =
    if (n <= 0) l
    else l match {
      case Nil => Nil
      case h :: t => drop(t, n - 1)
    }
}

object Exercise5 {
  @tailrec
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] =
    l match {
      case Nil => Nil
      case h :: t => if (f(h)) dropWhile(t, f) else h :: t
    }
}

object Exercise6 {
  def init[A](l: List[A]): List[A] = {
    l match {
      case Nil => Nil
      case h :: Nil => Nil
      case h :: t => h :: init(t)
    }
  }
}

object Exercise7 {
  // Can short-circuit only if there is another function that checks for some z: B which is known to be short-circuiting.
  def foldRight[A,B](l: List[A], z: B)(f: (A,B) => B): B =
    l match {
      case Nil => z
      case x :: xs => f(x, foldRight(xs, z)(f))
    }
}

// Same as 7, just uses our own list type.
object Exercise8 {
  import practice.datastructures._

  def foldRight[A,B](l: Lst[A], z: B)(f: (A,B) => B): B = l match {
    case Nil => z
    case Cons(head, tail) => f(head, foldRight(tail, z)(f))
  }

}

object Exercise9 {
  import practice.datastructures._
  import Exercise8.foldRight

  def length[A](l: Lst[A]): Int = foldRight(l, 0) { (_, len) => len + 1 }
}

object Exercise10 {
  import practice.datastructures._

  @tailrec
  def foldLeft[A,B](l: Lst[A], z: B)(f: (B, A) => B): B = l match {
    case Nil => z
    case Cons(head, tail) => foldLeft(tail, f(z, head))(f)
  }
}

object Exercise11 {
  import practice.datastructures._
  import Exercise10.foldLeft

  def sum(l: Lst[Int]): Int = foldLeft(l, 0)(_ + _)
  def product(l: Lst[Int]): Int = foldLeft(l, 1)(_ * _)
  def length[A](l: Lst[A]): Int = foldLeft(l, 0){ (acc, _) => acc + 1 }
}

object Exercise12 {
  import practice.datastructures._
  import Exercise10.foldLeft

  def reverse[A](l: Lst[A]): Lst[A] = foldLeft(l, Nil: Lst[A]) { (acc, a) => Cons(a, acc) }
}

object Exercise12p5 {
  import practice.datastructures._
  import Exercise8.foldRight

  // Reverse in terms of foldRight. Note that the performance is terrible because ++ traverses all
  // of the first list in order to append the second. This happens for each element in the original
  // list, so it's quadratic performance.
  def reverseR[A](l: Lst[A]): Lst[A] = foldRight(l, Nil: Lst[A]) { (a, acc) => acc ++ Lst(a) }
}

object Exercise13 {
  import practice.datastructures._

  import Exercise8.foldRight
  import Exercise12p5.reverseR

  import Exercise10.foldLeft
  import Exercise12.{reverse => reverseL} // reverse in terms of foldLeft

  // Miserable big-oh for runtime and memory, but it works.
  // This uses a reverse function which is also implemented in terms of foldRight.
  def foldLeftR[A,B](l: Lst[A], z: B)(f: (B, A) => B): B = foldRight(reverseR(l), z) { (a, acc) => f(acc, a) }

  // foldRight in terms of foldLeft. tail-recursion is possible now!
  def foldRightL[A,B](l: Lst[A], z: B)(f: (A,B) => B): B = foldLeft(reverseL(l), z) { (acc, a) => f(a, acc) }
}
