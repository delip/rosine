import com.cra.figaro.language.{Chain, Flip}
import com.cra.figaro.library.atomic.continuous.Uniform
import com.cra.figaro.library.atomic.discrete
import com.cra.figaro.library.compound.{^^, If}

class Firm {
  val efficient = Flip(0.3)
  val bid = If(efficient, Uniform(5, 15), Uniform(10, 20))
  def value = {
    efficient.generate()
    bid.generate()
    bid.value
  }
}
val firmArray = Array.fill(20)(new Firm)
val firms = discrete.Uniform(firmArray:_*)
val winningBid = Chain(firms, (f: Firm) => f.bid)
winningBid.setConstraint((d: Double) => 20 - d)

