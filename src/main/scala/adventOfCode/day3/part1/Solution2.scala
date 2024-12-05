package adventOfCode.day3.part1

import adventOfCode.Solution
import scala.io.Source
import java.io.{BufferedWriter, FileWriter}
import scala.util.Using
import scala.util.matching.Regex

object Solution2 extends Solution[String, Int]:
    val name = "foldLeft"

    def solve(name: String): Int =
        Using(Source.fromFile(name)) { source =>
            "mul\\(\\d{1,3},\\d{1,3}\\)".r.findAllIn(source.mkString).toList
                .foldLeft(0) { (acc, multString) =>
                    acc + ("\\d+".r.findAllIn(multString).toList
                        .map(_.toInt) match 
                            case List(a, b) => a * b
                            case _ => 0
                        )
                }
        }.getOrElse(0)