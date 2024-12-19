package adventOfCode.day6.part2

import adventOfCode.Solution
import adventOfCode.day6.*

object Solution2 extends Solution[String, Int]:
    val name = "walker"

    def solve(fileName: String): Int =
        val walker = GridWalker(fileName)
        walker.amountOfLoops