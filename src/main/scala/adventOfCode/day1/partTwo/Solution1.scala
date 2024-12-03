package adventOfCode.day1.partTwo

import scala.io.Source
import adventOfCode.Solution

object Solution1 extends Solution[String, Int]:
    val name = "Map of Tuples"

    def solve(name: String): Int = 
        solution(name)

    def solution(name: String): Int =
        val map = collection.mutable.Map[Int, (Int, Int)]()

        val bufferedSource = Source.fromFile(name)
        for (line <- bufferedSource.getLines())
            val Array(aS,bS) = line.split("\\s+")
            val a = aS.toInt
            val b = bS.toInt

            val aValue = map.getOrElse(a, (0,0))
            map(a) = (aValue._1 + 1, aValue._2)

            val bValue = map.getOrElse(b, (0,0))
            map(b) = (bValue._1, bValue._2 + 1)

        var sum = 0
        for((key, value) <- map)
            val appearances = value._1
            val multiplier = value._2
            sum += key * (appearances * multiplier)
        sum
