import com.cra.figaro.language._
import com.cra.figaro.library.compound.{CPD}

class Actor {
  val famous = Flip(0.1)
}

class Movie {
  val quality = Select(0.3 -> 'low, 0.5 -> 'medium, 0.2 -> 'high)
}

class Appearance(actor: Actor, movie: Movie) {
  val award = CPD(movie.quality, actor.famous,
    ('low, false) -> Flip(0.1),
    ('low, true) -> Flip(0.1),
    ('medium, false) -> Flip(0.01),
    ('medium, true) -> Flip(0.05),
    ('high, false) -> Flip(0.9),
    ('high, true) -> Flip(0.9)
 )
}
val actor1, actor2, actor3 = new Actor


val movie1, movie2 = new Movie

val appearance1 = new Appearance(actor1, movie1)
val appearance2 = new Appearance(actor2, movie2)
val appearance3 = new Appearance(actor3, movie2)
actor3.famous.observe(true)
movie2.quality.observe('high)
val appearances = List(appearance1, appearance2, appearance3)
def uniqueAwardCondition(awards: Seq[Boolean]) =
  awards.count((b: Boolean) => b) == 1
val allAwards = Inject(appearances map {_.award}:_*)
allAwards.setCondition(uniqueAwardCondition)

