
object monoid {

  // From "Functional Programming in Scala"
  trait Monoid[A] {
    def op(a1: A, a2: A): A
    def zero: A
  }

  def foldMap[A,B](as: List[A], m: Monoid[B])(f: A => B): B = {
    as.foldLeft(m.zero)((b,a) => m.op(b, f(a)))
  }

  val sumMonoid = new Monoid[Int] {
    def op(a1: Int, a2: Int): Int = a1 + a2
    def zero:Int = 0
  }


  trait MyOpt[+A] {
    def get: A
    def isEmpty: Boolean
    def isDefined = !isEmpty
  }

  class MySome[A](a: A) extends MyOpt[A] {
    def get = a
    def isEmpty = false
  }
  object MySome {
    def apply[A](a: A) = new MySome(a)
    def unapply[A](s: MySome[A]): Option[A] = Some(s.get)
  }
  case object MyNone extends MyOpt[Nothing] {
    def get = throw new Exception("Option is empty")
    def isEmpty = true
  }

  trait Monad[M[_]] {
    // from Monoid
    def zero: M[_]
    // def mappend[A](a1: A, a2: A): A

    // from Functor
    def map[A, B](ma: M[A])(f: A => B): M[B] = flatMap(ma)(a => unit(f(a)))
    def join = map _ // i think...not 100% sure

    def flatMap[A, B](ma: M[A])(f: A => M[B]): M[B]
    def bind = flatMap _ // i think...not 100% sure

    // All synonyms
    def unit[A](a: A): M[A]
    // def apply = unit // `apply` is too loaded of a term
    def point = unit _
    def pure = unit _
  }

  val optionMonad = new Monad[MyOpt] {
    def zero = MyNone
    def unit[A](a: A): MyOpt[A] = MySome(a)
    def flatMap[A, B](ma: MyOpt[A])(f: A => MyOpt[B]): MyOpt[B] = ma match {
      case MyNone => MyNone
      case MySome(a) => f(a)
    }
  }

}
