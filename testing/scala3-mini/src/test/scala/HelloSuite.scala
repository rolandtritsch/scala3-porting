import minitest._

object HelloSuite extends SimpleTestSuite {
  test("message") {
    assertEquals(message, "Hello Scala 3!")
  }
}
