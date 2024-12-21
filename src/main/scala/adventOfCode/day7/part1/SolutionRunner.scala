package adventOfCode.day7.part1

import adventOfCode.day7.part1.*
import adventOfCode.{Solution, benchmarkSolutions, getSolutions}
import org.openjdk.jmh.annotations.*

import java.util.concurrent.TimeUnit

@main def solution(): Unit =
    val solutions = getSolutions[Solution[String, Long]](SolutionRunner.packageName)
    println()
    benchmarkSolutions(solutions, SolutionRunner.input, printResult = true)

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
class SolutionRunner:
    @Benchmark
    def recursive(): Unit =
        Solution1.solve(SolutionRunner.input)


object SolutionRunner:
    val packageName: String = "adventOfCode.day7.part1"
    val input: String = "src/main/scala/adventOfCode/day7/input.txt"