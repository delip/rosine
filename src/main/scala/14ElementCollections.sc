import com.cra.figaro.language.{ElementCollection, Constant, Select, Element}
import com.cra.figaro.library.compound.CPD

abstract class Engine extends ElementCollection {
  val power: Element[Symbol]
}

class I4 extends Engine {
  val power = Select(0.8 -> 'low, 0.2 -> 'high)("power", this)
}

class V8 extends Engine {
  val power = Select(0.8 -> 'low, 0.2 -> 'high)("power", this)
}

object MyEngine extends Engine {
  val power = Constant('high)("power", this)
}

class Car extends ElementCollection {
  val engine: Element[Engine] =
    Select(0.2 -> new I4, 0.3 -> new V8, 0.5 -> MyEngine)
  val speed = CPD(get[Symbol]("engine.power"),
  'low -> Constant(65),
  'high -> Select(0.5 -> 80, 0.5 -> 90))
}

val car = new Car
//car.speed

//TODO: Does not compile