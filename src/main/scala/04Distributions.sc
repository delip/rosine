import com.cra.figaro.library.atomic.continuous._
import com.cra.figaro.library.atomic.discrete

def unifDemo() {
  val unif = Uniform(0, 2)
  var meanSum = 0.0
  val trials = 10000
  for (i <- 0 to trials) {
    unif.generate()
    meanSum = meanSum + unif.value
  }
  println(meanSum / trials)
}

def contDemo() {
  val norm = Normal(10, 1) // (mean, stddev)
  val exp = Exponential(0.1) // lambda
  val gamma = Gamma(1, 2) // (k, theta)
  val beta = Beta(1, 1)  // (a, b)
}

def discDemo() {
  val unif = discrete.Uniform("a", "b", "c", "d")
  val dice = discrete.Uniform(1, 2, 3, 4, 5, 6)
  val bin = discrete.Binomial(10, 0.5) // (n, p)
  // Geometric, Poisson
}