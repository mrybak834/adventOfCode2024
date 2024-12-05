package adventOfCode.day2.partOne

import adventOfCode.Solution
import scala.io.Source
import scala.collection.mutable.PriorityQueue
import java.nio.file.Files
import java.nio.file.Path
import scala.jdk.CollectionConverters.*
import math.{abs, signum}

object Solution2 extends Solution[String, Int]:
    val name = "zip(stolen from discord)"
    // https://github.com/makingthematrix/AdventOfCode2024/blob/main/src/main/scala/io/github/makingthematrix/AdventofCode2024/DayTwo.scala
    
    def solve(name: String): Int = 
        val seqs = readInput(name)
        seqs.count(isSafe(_, true))

    private def readInput(name: String): Seq[Seq[Int]] =
        Files.readAllLines(Path.of(name)).asScala.toSeq.map { _.split(" ").toSeq.map(n => n.trim.toInt) }

    private def isSafe(seq:Seq[Int], useDampener: Boolean): Boolean =
        val dir = signum(seq(1) - seq(0))
        val check = seq.zip(seq.tail).forall((a, b) => a != b && signum(b - a) == dir && abs(b - a) <= 3)
        if (check || !useDampener) check
        else seq.indices.view.map(i => seq.take(i) ++ seq.drop(i + 1)).exists(isSafe(_, false))

