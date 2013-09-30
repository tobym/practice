package practice.chapter2

import annotation.tailrec

object Exercise1 {
  def fib(n: Int): Int = {
    assert(n >= 1, "n must be >= 1")
    @tailrec
    def fib(a: Int, b: Int, x: Int): Int = {
      if (x == 1) a
      else fib(b, a + b, x - 1)
    }
    fib(0, 1, n)
  }
}

object Exercise2 {
  def isSorted[A](as: Array[A], gt: (A,A) => Boolean): Boolean = {
    @tailrec
    def f(i: Int, j: Int): Boolean = {
      if (j >= as.length) true
      else if (gt(as(i), as(j))) false
      else f(i+1, j+1)
    }
    f(0, 1)
  }
}

object Exercise3 {
  def curry[A,B,C](f: (A, B) => C): A => (B => C) = {
    (a: A) => (b: B) => f(a, b)
  }
}

object Exercise4 {
  def uncurry[A,B,C](f: A => B => C): (A, B) => C = {
    (a: A, b: B) => f(a)(b)
  }
}

object Exercise5 {
  def compose[A,B,C](f: B => C, g: A => B): A => C = {
    (a: A) => f(g(a))
  }
}
