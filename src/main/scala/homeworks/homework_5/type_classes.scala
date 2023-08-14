package homeworks.homework_5

/**
 * Реализуйте инстансы тайп класса Show для базовых типов и коллекций и вспомогательные функции,
 * чтобы был выполнен тест type_classes_test (раскоментируйте его)
 * В качестве аналогии возьмите пример JsonWriter
 */

object type_classes {

  trait Show[A] {
    def show(a: A): String
  }

}
