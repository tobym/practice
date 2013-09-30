package practice.chapter2

import org.scalatest._

class Chapter2Specs extends FunSpec with Matchers {
  describe("Exercise1") {
    it("should calculate fib recursively") {
      import Exercise1.fib
      fib(1) should be (0)
      fib(2) should be (1)
      fib(3) should be (1)
      fib(4) should be (2)
      fib(5) should be (3)
      fib(6) should be (5)
    }
  }
  describe("Exercise2") {
    it("should calculate isSorted") {
      import Exercise2.isSorted
      val gt = (a: Int, b: Int) => a > b
      isSorted(Array(1, 2, 3, 4), gt) should be (true)
      isSorted(Array(4, 3, 2, 1), gt) should be (false)
      isSorted(Array(1, 2, 4, 4), gt) should be (true)
      isSorted(Array(1, 2, 4, 3), gt) should be (false)
      isSorted(Array.empty[Int], gt) should be (true)
      isSorted(Array(1), gt) should be (true)
    }
  }
}
