import com.cra.figaro.language.{Inject, Apply, Flip, Select}
import com.cra.figaro.library.compound.{CPD, If}


class Actor {
  var movies: List[Movie] = List()

  lazy val skillful = Flip(0.1)

  private def probFamous(qualities: Seq[Symbol]) =
    if (qualities.count(_ == 'high) >= 2) 0.8 else 0.1

  lazy val famous = Flip(Apply(Inject(movies.map(_.quality):_*), probFamous _))
}


class Movie {
  var actors:List[Actor] = List()
  lazy val actorsAllGood = Apply(Inject(actors map {_.skillful}:_*), (s: Seq[Boolean]) => !s.contains(false))
  lazy val quality =
    If(actorsAllGood, Select(0.2 -> 'low, 0.3 -> 'medium, 0.5 -> 'high),
    Select(0.5 -> 'low, 0.3 -> 'medium, 0.2 -> 'high))
}

class Appearance(actor: Actor, movie: Movie) {
  actor.movies ::= movie
  movie.actors ::= actor

  lazy val award = CPD(movie.quality, actor.famous,
    ('low, false) -> Flip(0.001),
    ('low, true) -> Flip(0.01),
    ('medium, false) -> Flip(0.01),
    ('medium, true) -> Flip(0.05),
    ('high, false) -> Flip(0.05),
    ('high, true) -> Flip(0.2)
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
