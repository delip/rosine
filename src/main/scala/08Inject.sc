import com.cra.figaro.language.{Inject, Select}

val oneTwo = Select(0.6 -> 1, 0.4 -> 2)
val threeFour = Select(0.7 -> 3, 0.3 -> 5)

/**
 * This should produce tuples with the following prob
 * (1, 3) -> 0.6 * 0.7 = 0.42
 * (1, 5) -> 0.6 * 0.3 = 0.18
 * (2, 3) -> 0.4 * 0.7 = 0.28
 * (2, 5) -> 0.4 * 0.4 = 0.12
 */
val oneToFour = Inject(oneTwo, threeFour)

for (i <- 1 to 10) {
  oneTwo.generate()
  threeFour.generate()
  oneToFour.generate()
  println(oneToFour.generateValue())
}

