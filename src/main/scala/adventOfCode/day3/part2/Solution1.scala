package adventOfCode.day3.part2

import adventOfCode.Solution
import scala.io.Source
import java.io.{BufferedWriter, FileWriter}
import scala.util.Using
import scala.util.matching.Regex
import scala.io.BufferedSource

object Solution1 extends Solution[String, Int]:
    val name = "foldLeft"

    def solve(name: String): Int =
        Using(Source.fromFile(name)) { source =>
            var enabled = true
            matches(source).foldLeft(0) { (acc, matchString) =>
                if (matchString.equals("do()")) enabled = true
                if (matchString.equals("don't()")) enabled = false

                if (enabled && matchString.contains("mul"))
                    acc + multiply(matchString)
                else
                    acc
            }
        }.getOrElse(0)

    def matches(source: BufferedSource): List[String] = 
        "mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don\\'t\\(\\)".r.findAllIn(source.mkString).toList

    def multiply(matchString: String): Int =
        "\\d+".r.findAllIn(matchString).toList
            .map(_.toInt) match 
                case List(a, b) => a * b
                case _ => 0