package adventOfCode.day6.part1

import adventOfCode.Solution
import adventOfCode.day6.GridWalker

object Solution1 extends Solution[String, Int]:
    val name = "walker"

    def solve(fileName: String): Int =
        val walker = GridWalker(fileName)
        val steps = walker.stepsToLeaveGrid
        walker.print()
        steps
        