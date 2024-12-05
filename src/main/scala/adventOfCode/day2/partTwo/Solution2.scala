package adventOfCode.day2.partTwo

import java.nio.file.{Files, Path}
import scala.jdk.CollectionConverters.*
import math.{abs, signum}
import adventOfCode.Solution

object Solution2 extends Solution[String, Int]:
    val name = "redditSolution"

    def solve(name: String): Int =
        val seqs = readLines(name).map(_.split(" ").map(_.toInt))
        seqs.count(isSafe(_, true))

    private def isSafe(arr: Array[Int], useDampener: Boolean): Boolean =
        val dir   = signum(arr(1) - arr(0))
        val check = arr.zip(arr.tail).forall((a, b) => a != b && signum(b - a) == dir && abs(b - a) <= 3)
        if check || !useDampener then check
        else arr.indices.view.map(i => arr.take(i) ++ arr.drop(i + 1)).exists(isSafe(_, false))

    inline def readLines(fileName: String): Seq[String] = Files.readAllLines(Path.of(fileName)).asScala.toSeq
    inline def readString(fileName: String): String = Files.readString(Path.of(fileName))