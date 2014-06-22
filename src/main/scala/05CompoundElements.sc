import com.cra.figaro.language.{Select, Constant, Flip}
import com.cra.figaro.library.atomic.continuous.Uniform
import com.cra.figaro.library.compound.If
import scala.collection.mutable

val flipUnif = Flip(Uniform(0, 1)) //  true is uniformly distributed between 0 and 1

def compoundDemo() {
  val genModel = If(Flip(0.7), Constant(1), Select(0.4 -> 2, 0.6 -> 3))
  // P(1 | model) = 0.7
  // P(2 | model) = 0.3*0.4 = 0.12
  // P(3 | model) = 0.3*0.6 = 0.18
  val trials = 100000
  val counts = mutable.Map(1 -> 0.0, 2 -> 0.0, 3 -> 0.0)
  for (i <- 0 to trials) {
    genModel.generate()
    val key = genModel.value
    counts(key) = counts(key) + 1.0
  }
  for (i <- counts.keys) {
    println( i + ": " + counts(i)/trials)
  }
}

compoundDemo()


































