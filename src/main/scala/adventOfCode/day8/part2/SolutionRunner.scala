package adventOfCode.day8.part2

import adventOfCode.day8.part2.*
import adventOfCode.{Solution, benchmarkSolutions, getSolutions}
import org.openjdk.jmh.annotations.*

import java.util.concurrent.TimeUnit

@main def solution(): Unit =
    val solutions = getSolutions[Solution[String, Int]](SolutionRunner.packageName)
    println()
    benchmarkSolutions(solutions, SolutionRunner.input, printResult = true)

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
class SolutionRunner:
    @Benchmark
    def iterative(): Unit =
        Solution1.solve(SolutionRunner.input)


object SolutionRunner:
    val packageName: String = "adventOfCode.day8.part2"
    val input: String = "src/main/scala/adventOfCode/day8/input.txt"