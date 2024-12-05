package adventOfCode.day3.part1

import org.openjdk.jmh.annotations._
import java.util.concurrent.TimeUnit
import adventOfCode.day3.part1.*
import adventOfCode.getSolutions
import adventOfCode.Solution
import adventOfCode.benchmarkSolutions

@main def solution =
    val solutions = getSolutions[Solution[String, Int]](SolutionRunner.packageName)
    println()
    benchmarkSolutions(solutions, SolutionRunner.input, printResult = true)

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
class SolutionRunner:
    @Benchmark
    def imperative(): Unit =
        Solution1.solve(SolutionRunner.input)

    @Benchmark
    def foldLeft(): Unit =
        Solution1.solve(SolutionRunner.input)

    @Benchmark
    def map_and_sum(): Unit =
        Solution1.solve(SolutionRunner.input)



object SolutionRunner:
    val packageName: String = "adventOfCode.day3.part1"
    val input: String = "/workspaces/adventofcode/src/main/scala/adventOfCode/day3/input.txt"