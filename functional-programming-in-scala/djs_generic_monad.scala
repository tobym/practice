package fpinscala

object generic_monad {

  sealed trait Maybe[+T]
  case class Just[T](t: T) extends Maybe[T]
  case object Empty extends Maybe[Nothing]

  trait Monad[M[_]] {
    def unit[A](a: A): M[A]
    def bind[A, B](m: M[A])(f: A => M[B]): M[B]
  }

  implicit object MonadicOption extends Monad[Maybe] {
    def unit[A](a: A) = Just(a)
    def bind[A, B](opt: Maybe[A])(f: A => Maybe[B]) = opt flatMap f
  }

  implicit class MonadicSyntax[M[_], A](m: M[A])(implicit tc: Monad[M]) {
    def map[B](f: A => B): M[B] = tc.bind(m)(f andThen tc.unit)
    def flatMap[B](f: A => M[B]): M[B] = tc.bind(m)(f)
  }

}
