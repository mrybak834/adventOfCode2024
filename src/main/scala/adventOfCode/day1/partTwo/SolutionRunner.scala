package adventOfCode.day1.partTwo

import org.openjdk.jmh.annotations._
import java.util.concurrent.TimeUnit
import adventOfCode.day1.partTwo.*
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
    def mapOfTuples(): Unit =
        Solution1.solve(SolutionRunner.input)

object SolutionRunner:
    val packageName: String = "adventOfCode.day1.partTwo"
    val input: String = "/workspaces/adventofcode/src/main/scala/adventOfCode/day1/input.txt"