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
