package adventOfCode.day3.part1

import adventOfCode.Solution
import scala.io.Source
import java.io.{BufferedWriter, FileWriter}
import scala.util.Using
import scala.util.matching.Regex

object Solution1 extends Solution[String, Int]:
    val name = "imperative"

    def solve(name: String): Int =
        val pattern = "mul\\(\\d{1,3},\\d{1,3}\\)".r
        var product = 0

        val source = Source.fromFile(name)
        val matches = pattern.findAllIn(source.mkString).toList

        for (mult <- matches)
            val List(a, b) = "\\d+".r.findAllIn(mult).toList
            product += (a.toInt * b.toInt)
    
        source.close()
        product