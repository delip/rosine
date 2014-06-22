import com.cra.figaro.language.Select

val x1 = Select(0.1 -> 1, 0.2 -> 2, 0.3 -> 3, 0.4 -> 4)

for (i <- 1 to 10) {
  x1.generate()
  println(x1.value)
}

// now let's put a condition that only 1 or 2 can be generated
//x1.setCondition((i: Int) => (i == 1 || i == 2))
x1.observe(1)
// for some reason observe works but not setCondition
for (i <- 1 to 10) {
  x1.generate()
  println(x1.value)
}

// a condition or an observation holds with a prob of 1.0
// suppose we observe something and we're not sure about it
// the way to represent it is by adding a constraint. See next
// worksheet











