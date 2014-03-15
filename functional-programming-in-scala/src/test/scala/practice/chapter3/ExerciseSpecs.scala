package practice.chapter3

import org.scalatest._

class Chapter3Specs extends FunSpec with Matchers {
  describe("Exercise4") {
    it("should drop") {
      import Exercise4.drop
      drop(List(1, 2, 3), 0) should be (List(1, 2, 3))
      drop(List(1, 2, 3), 1) should be (List(2, 3))
      drop(List(1, 2, 3), 2) should be (List(3))
      drop(List(1, 2, 3), 3) should be (Nil)
      drop(List(1, 2, 3), 4) should be (Nil)
      drop(List.empty[Int], 5) should be (Nil)
    }
  }

  describe("Exercise5") {
    it("should dropWhile") {
      import Exercise5.dropWhile
      val f = (i: Int) => i <= 2
      dropWhile(List(1, 2, 3, 2, 1), f) should be (List(3, 2, 1))
      dropWhile(List(3, 2, 1), f) should be (List(3, 2, 1))
    }
  }

  describe("Exercise6") {
    it("should init list to all but last of source") {
      import Exercise6.init
      init(List(1, 2, 3, 4)) should be (List(1, 2, 3))
      init(List(1)) should be (Nil)
      init(List.empty[Int]) should be (Nil)
    }
  }

  describe("Exercise7") {
    it("should foldRight") {
      import Exercise7.foldRight
      foldRight(List(1, 2, 3), 0)(_ + _) should be (6)
      foldRight(List("a", "bb"), 0){ (x, acc) => x.length + acc } should be (3)
      foldRight[String, String](Nil, "foo")(_ + _) should be ("foo")
      foldRight(List("a", "b", "c"), "-"){ (x, acc) => acc + x } should be ("-cba")
    }
  }

  describe("Exercise8") {
    it("should show that foldRight has the same result as the data constructor of a list") {
      import practice.datastructures._
      import Exercise8._
      val orig = Lst(1, 2, 3)
      foldRight(orig, Nil: Lst[Int])(Cons(_, _)) should be (orig)
    }
  }

  describe("Exercise9") {
    it("should calculate list length using foldRight") {
      import practice.datastructures._
      import Exercise9.{length => len}

      len(Nil) should be (0)
      len(Lst(1, 2, 3)) should be (3)
    }
  }

  describe("Exercise11") {
    import practice.datastructures._
    import Exercise11.{sum, product, length => len}

    it("should calculate sum of list") {
      sum(Lst(-1, 0, 2, 5)) should be (6)
    }

    it("should calculate product of list") {
      product(Lst(2, 3, 4)) should be (24)
      product(Lst(2, 3, 0)) should be (0)
      product(Nil) should be (1)
    }
  }

  describe("Exercise12") {
    import practice.datastructures._
    import Exercise12.reverse

    it("should reverse a list with foldLeft") {
      reverse(Lst(1, 2, 3)) should be (Lst(3, 2, 1))
      reverse(Nil) should be (Nil)
    }
  }

  describe("Exercise12p5") {
    import practice.datastructures._
    import Exercise12p5.reverseR

    it("should reverse a list with foldRight") {
      reverseR(Lst(1, 2, 3)) should be (Lst(3, 2, 1))
      reverseR(Nil) should be (Nil)
    }
  }

  describe("Exercise13") {
    import practice.datastructures._

    it("should foldLeft in terms of foldRight") {
      import Exercise10.foldLeft
      import Exercise13.foldLeftR // foldLeft in terms of foldRight

      val l = Lst("a", "b", "c")
      val f: (String, String) => String = (acc, a) => acc + a
      val expected = "abc"

      foldLeft(l, "")(f) should be (expected) // sanity check
      foldLeftR(l, "")(f) should be (foldLeft(l, "")(f))
    }

    it("should foldRight in terms of foldLeft") {
      import Exercise8.foldRight
      import Exercise13.foldRightL // foldRight in terms of foldLeft

      val l = Lst("a", "b", "c")
      val f: (String, String) => String = (a, acc) => acc + a
      val expected = "cba"

      foldRight(l, "")(f) should be (expected) // sanity check
      foldRightL(l, "")(f) should be (foldRight(l, "")(f))
    }
  }

}
