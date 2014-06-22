import com.cra.figaro.algorithm.sampling.{MetropolisHastings, ProposalScheme}
import com.cra.figaro.language.Flip
import com.cra.figaro.library.compound.CPD

// CPD can be used to define contingency tables
// implements example from http://en.wikipedia.org/wiki/Bayesian_network
val rain = Flip(0.2)
val sprinkler = CPD(rain, true -> Flip(0.01), false -> Flip(0.4))
val grassWet = CPD(sprinkler, rain,
  (true, true) -> Flip(0.99),
  (true, false) -> Flip(0.9),
  (false, true) -> Flip(0.8),
  (false, false) -> Flip(0.0))

// now let's say we find the grass wet
grassWet.observe(true)

// how will it change the probability that it rained
val alg = MetropolisHastings(200000, ProposalScheme.default, rain)
alg.start()
println("P(rain | grassWet) = " + alg.probability(rain, true))

alg.kill()