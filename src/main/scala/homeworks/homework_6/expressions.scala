package homeworks.homework_6


/**
 * EXPRESSIONS - EXERCISE SET 1
 *
 * Consider an application (such as the spreadsheet example) that needs to
 * calculate values in a user-defined way.
 */
object expressions {

  sealed trait CalculatedValue[+A]

  object CalculatedValue {
    final case class Integer(value: Int) extends CalculatedValue[Int]
    final case class Str(value: String)  extends CalculatedValue[String]

    /**
     * EXERCISE 1
     *
     * Add an operator that adds two integer expressions, yielding an integer
     * expression.
     *
     * NOTE: Be sure to modify the `calculate` method below, so that it can
     * handle the new operation.
     */
    final case class Add()

    /**
     * EXERCISE 2
     *
     * Add an operator that subtracts an integer from another integer expression,
     * yielding an integer expression.
     *
     * NOTE: Be sure to modify the `calculate` method below, so that it can
     * handle the new operation.
     */
    final case class Subtract()

    /**
     * EXERCISE 3
     *
     * Add an operator that multiplies two integer expressions, yielding an
     * integer expression.
     *
     * NOTE: Be sure to modify the `calculate` method below, so that it can
     * handle the new operation.
     */
    final case class Multiply()

    /**
     * EXERCISE 4
     *
     * Add an operator that concatenates two strings, yielding a string
     * expression.
     *
     * NOTE: Be sure to modify the `calculate` method below, so that it can
     * handle the new operation.
     */
    final case class Concat()

    /**
     * EXERCISE 5
     *
     * Add an operator that determines if a string starts with a specified
     * prefix, yielding a boolean expression.
     *
     * NOTE: Be sure to modify the `calculate` method below, so that it can
     * handle the new operation.
     */
    final case class StartsWith()
  }

  import CalculatedValue._

  def calculate[A](expr: CalculatedValue[A]) =
    expr match {
      case Integer(v) => ???
      case Str(v)     => ???
    }
}
