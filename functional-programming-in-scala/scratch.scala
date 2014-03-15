package fpinscala.scratch

object scratch {

  // From "Functional Programming in Scala"
  trait Monoid[A] {
    def op(a1: A, a2: A): A
    def zero: A
  }

  // a monad is a monoid in the category of endofunctors :)
  // This trait defines the Monad typeclass. A specific instance of this type is
  // required to be useful. Note that all the functions take an A or an M[A] since
  // they don't exist on their own in the typeclass.
  trait Monad[M[_]] {
    // from Monoid
    def zero[A]: A
    // def mappend[A](a1: A, a2: A): A

    // from Functor
    def map[A, B](ma: M[A])(f: A => B): M[B] = flatMap(ma)(a => unit(f(a)))

    def flatMap[A, B](ma: M[A])(f: A => M[B]): M[B]

    // All synonyms
    def unit[A](a: A): M[A]
    // def apply = unit // `apply` is too loaded of a term
    def point = unit _
    def pure = unit _
  }

  /*
  trait SumM[Int] extends M[Int] {
    type A = Int
    val zero = 0
    // From Monoid[Int]
    def mappend[A](a1: A, a2: A): A = a1 + a2
    def map[A, B](fa: A => B): M[B] = this
    def flatMap[A, B](fa: A => M[B]): M[B] = this
    def apply[A](a: A): M[A] = this
  }
  */

  /*
  def foldRight[A,B](l: List[A], z: B)(f: (A,B) => B): B =
    l match {
      case Nil => z
      case x :: xs => f(x, foldRight(xs, z)(f))
    }

  foldRight(List[Int](1, 0, 3, 4), 1){ (i: Int, acc: Int) =>
    println("Processing " + i)
    i * acc
  }
  */

}
