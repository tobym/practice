package functional.practice

// Most basic impl of option "monad" (using "maybe" name that Haskell uses)
sealed trait Maybe[+A] {
  def flatMap[B](f: A => Maybe[B]): Maybe[B]
}

case class Just[+A](a: A) extends Maybe[A] {
  def flatMap[B](f: A => Maybe[B]) = f(a)
}

case object Empty extends Maybe[Nothing] {
  def flatMap[B](f: Nothing => Maybe[B]) = Empty
}
