package typeclassopedia

// From http://blog.tmorris.net/posts/scala-type-class-hierarchy/index.html

trait Semigroup[M] {
  def op: M => M => M
}

trait Monoid[M] extends Semigroup[M] {
  val id: M
}

trait Functor[F[_]] {
  def fmap[A, B](f: A => B): F[A] => F[B]
}

trait Apply[F[_]] extends Functor[F] {
  def ap[A, B](f: F[A => B]): F[A] => F[B]
}

trait Bind[F[_]] extends Apply[F] {
  def bind[A, B](f: A => F[B]): F[A] => F[B]
}

trait Applicative[F[_]] extends Apply[F] {
  def insert[A]: A => F[A]
}

trait Monad[F[_]] extends Applicative[F] with Bind[F]



// Now my stuff...

/*
type Car = Int
sealed trait Bridge[_] extends Semigroup[_] {
  def op(m1: Car) = (m2: Car) => m1 + m2
}
case class Standing(id: Car) extends Bridge[Car]
case object Fallen extends Bridge[Nothing]
*/
