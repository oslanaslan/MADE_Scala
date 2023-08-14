package homeworks.homework_6

object parsers extends App {

  type Error = String
  type Input = String

  // `Parser[A]` is a model of a series of parse operations that consume
  // characters and ultimately use the consumed input construct a value of
  // type `A`.
  sealed trait Parser[+A] {
    self =>
    def atLeast(n: Int): Parser[List[A]] = Parser.Repeat(self, Some(n), None)

    def atMost(n: Int): Parser[List[A]] = Parser.Repeat(self, None, Some(n))

    def between(min: Int, max: Int): Parser[List[A]] = Parser.Repeat(self, Some(min), Some(max))

    def * : Parser[List[A]] = Parser.Repeat(self, None, None)

    def + : Parser[List[A]] = atLeast(1)
  }

  object Parser {
    final case object OneChar extends Parser[Char]

    final case class Repeat[A](value: Parser[A], min: Option[Int], max: Option[Int]) extends Parser[List[A]]

    /**
     * EXERCISE 1
     *
     * Add a constructor that models the production of the specified value (of
     * any type at all), without consuming any input.
     *
     * NOTE: Be sure to modify the `parse` method below, so that it can
     * handle the new operation.
     */
    final case class Succeed()

    /**
     * EXERCISE 2
     *
     * Add a constructor that models failure with a string error message.
     *
     * NOTE: Be sure to modify the `parse` method below, so that it can
     * handle the new operation.
     */
    final case class Fail()

    /**
     * EXERCISE 3
     *
     * Add an operator that can try one parser, but if that fails, try
     * another parser.
     *
     * NOTE: Be sure to modify the `parse` method below, so that it can
     * handle the new operation.
     */
    final case class OrElse()

    /**
     * EXERCISE 4
     *
     * Add an operator that parses one thing, and then parses another one,
     * in sequence, producing a tuple of their results.
     *
     * NOTE: Be sure to modify the `parse` method below, so that it can
     * handle the new operation.
     */
    final case class Sequence[A, B]()
  }

  import Parser._

  def parse[A](parser: Parser[A], input: Input): Either[Error, (Input, Any)] =
    parser match {
      case OneChar =>
        input.headOption
          .map(a => Right(input.drop(1) -> a))
          .getOrElse(Left("Expected a character but found an empty string"))

      case repeat: Repeat[A] =>
        val min = repeat.min.getOrElse(0)
        val max = repeat.max.getOrElse(Int.MaxValue)

        (min to max)
          .foldLeft[Either[Error, (Input, List[_])]](Right((input, Nil))) {
            case (e@Left(_), _) => e

            case (Right((input, as)), _) =>
              parse(repeat.value, input) match {
                case Left(error) => if (as.length >= min) Right((input, as)) else Left(error)
                case Right((input, a)) => Right((input, a :: as))
              }
          }
          .map {
            case (input, as) => (input, as.reverse)
          }
    }
}
