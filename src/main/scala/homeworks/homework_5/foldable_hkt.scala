package homeworks.homework_5

/**
 * Generic foldLeft!
 * F[_] - type constructor with a single type argument, like List[T], Option[T], etc.
 * Types which are parameterized using type constructors called higher-kinded types (HKT)
 * Foldable here is a HKT
 *
 * Part 1.
 * Define an Foldable instance for Triple (should behave like a collection of 3 elements)
 *
 * Part 2.
 * Define another type-class - Summable[T] which should give us methods:
 * - def plus(left: T, right: T): T
 * - def zero: T
 *
 * Define Summable[T] instances for:
 * - any T which has the standard library Numeric[T] type-class provided
 * - Set[S] - zero should be Set.empty and plus should merge sets with + operation
 *
 * Part 3.
 * And finally - define generic collection sum method which works on any F[T]
 * where F is Foldable (F[_]: Foldable) and T is Summable (T: Summable)!
 * def genericSum... - work out the right method signature, should take F[T] and return T
 */
object foldable_hkt {

  trait Foldable[F[_]] {
    def foldLeft[T, S](ft: F[T], s: S)(f: (S, T) => S): S
  }

  case class Triple[T](v1: T, v2: T, v3: T)

}
