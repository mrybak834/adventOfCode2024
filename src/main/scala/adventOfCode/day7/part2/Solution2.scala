package adventOfCode.day7.part2

import adventOfCode.Solution

import adventOfCode.day7.part1.Solution1.equationsCanMakeTarget

object Solution2 extends Solution[String, Long]:
    val name = "recursive"

    def solve(name: String): Long =
        equationsCanMakeTarget(name, true)