import com.cra.figaro.language.{Chain, Select, Constant, Flip}
import scala.collection.mutable

def chainDemo() {
  val genModel = Chain(Flip(0.7), (b: Boolean) =>
    if(b) Constant(1); else Select(0.4 -> 2, 0.6 -> 3))
  // P(1 | model) = 0.7
  // P(2 | model) = 0.3*0.4 = 0.12
  // P(3 | model) = 0.3*0.6 = 0.18
  val trials = 100000
  val counts = mutable.Map(1 -> 0.0, 2 -> 0.0, 3 -> 0.0)
  for (i <- 0 to trials) {
    val key = genModel.generateValue()
    counts(key) = counts(key) + 1.0
  }
  for (i <- counts.keys) {
    println( i + ": " + counts(i)/trials)
  }
}


def chainDemo2() {
  val genModel = Chain(Flip(0.7), (b: Boolean) =>
    if(b) Constant(1); else Select(0.4 -> 2, 0.6 -> 3))
  // P(1 | model) = 0.7
  // P(2 | model) = 0.3*0.4 = 0.12
  // P(3 | model) = 0.3*0.6 = 0.18
  val trials = 100000
  val counts = mutable.Map(1 -> 0.0, 2 -> 0.0, 3 -> 0.0)
  for (i <- 0 to trials) {
    genModel.parent.generate()
    val parent = genModel.parent.value
    val resultingElem = genModel.get(parent)
    resultingElem.generate()
    val child = resultingElem.value
    counts(child) = counts(child) + 1.0
  }
  for (i <- counts.keys) {
    println( i + ": " + counts(i)/trials)
  }
}

def nestedChainDemo() {
  //TODO: create an intuitive example of chain nesting
}
chainDemo()
// chainDemo2 works as expected
chainDemo2()















