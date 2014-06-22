import com.cra.figaro.language.Apply
import com.cra.figaro.library.atomic.continuous
import com.cra.figaro.library.atomic.discrete.Uniform

// let X and Y be a unif. random variables
val X = Uniform(1 to 5:_*)
val Y = Uniform(3, 4, 5, 6, 7)
val W = continuous.Uniform(0, 1)
// shiftBy5 is Z = Y + 5
val shiftBy5 = Apply(X, (i: Int) => i + 5)
// sum of random variables Z = X + Y
val sumXY = Apply(X, Y, (i: Int, j: Int) => i + j)
// weighted mixture of random variables wX + (1-w)Y
val mixture = Apply(X, Y, W, (i: Int, j: Int, w:Double) => w*i + (1 - w)*j)

for (i <- 1 to 10) {
  X.generate()
  Y.generate()
  W.generate()
  println(mixture.generateValue())
}









