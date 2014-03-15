package functional.practice

trait Monad[M[_]] {
  def flatMap[A, B](ma: M[A])(f: A => M[B]): M[B]
  def unit[A](a: A): M[A]
  def map[A, B](ma: M[A])(f: A => B): M[B] = flatMap(ma)(a => unit(f(a)))
  def zero: M[_]
}

// a type that takes a type param, to use with Monad
sealed trait Maybe[+A] {
  def get: A
}

case class Just[+A](a: A) extends Maybe[A] {
  def get = a
}

case object Empty extends Maybe[Nothing] {
  def get = throw new Exception("nothing to get")
}

val MaybeMonad = new Monad[Maybe] {
  def unit[A](a: A) = Just(a)
  def zero = Empty
  def flatMap[A, B](ma: Maybe[A])(f: A => Maybe[B]): Maybe[B] = ma match {
    case Empty => Empty
    case Just(a) => f(a)
  }
}
