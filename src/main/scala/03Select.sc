import com.cra.figaro.language.Select

def selectDemo() {
  val toss = Select(0.7 -> "HEAD", 0.3 -> "TAIL")

  val trials = 100000
  var trueCount = 0.0
  for (i <- 1 to trials) {
    toss.generate()
    if (toss.value.equals("HEAD"))
      trueCount += 1.0
    //println(toss.value)
  }
  println(trueCount / trials)
  toss.density("HEAD")
}

selectDemo()
