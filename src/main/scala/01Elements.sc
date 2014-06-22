import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language.Constant

// Build a model
val hw = Constant("Hello World!")
hw.generateValue()
hw.density("Hello World!")
hw.density("FooBar")

// Run an inference algorithm
val alg = Importance(1000, hw)
alg.start()

// query for a result (infer)
alg.probability(hw, "Hello World!")
alg.probability(hw, "Delip")
