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
}
