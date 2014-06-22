import com.cra.figaro.language.Flip


def flipDemo() {

  val toss = Flip(0.7) // True with prob 0.7 and false with prob 0.3

  println(toss.density(true))

  var trueCount = 0.0
  val trials = 100000

  for (i <- 1 to trials) {
    /**
     * Alternate:
     * toss.generate()
     * if toss.value trueCount += 1.0
     */
    if (toss.generateValue(toss.generateRandomness()))
      trueCount += 1.0
  }

  println(trueCount / trials)
}

flipDemo()



