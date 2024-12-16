package adventOfCode.day5.part1

import adventOfCode.Solution

import scala.collection.immutable.{Map, Set}
import scala.collection.mutable
import scala.io.Source
import scala.util.Using

object Solution1 extends Solution[String, Int]:
    val name = "graph"

    def solve(name: String): Int =
        val (orderingRules, pageSequences): (Map[Int, Set[Int]], List[List[Int]]) = Using(Source.fromFile(name)) { source =>
            (getOrderingRules(source), getPageSequences(source))
        }.get // throw exception if failed reading

        getValidSequencesMiddlePageSum(orderingRules, pageSequences)

    private def getValidSequencesMiddlePageSum(orderingRules: Map[Int, Set[Int]], pageSequences: List[List[Int]]): Int =
        pageSequences.map { sequence =>
            val middleIndex = sequence.size / 2
            val pagesSeen = mutable.Set.empty[Int]
            var validSequence = true
            var pageIndex = 0

            while(pageIndex < sequence.size && validSequence)
                val page = sequence(pageIndex)
                validSequence = orderingRules.get(page) match
                    case Some(precedenceSet) => pagesSeen.intersect(precedenceSet).isEmpty
                    case None => true

                pagesSeen.add(page)
                pageIndex += 1

            if (validSequence) sequence(middleIndex) else 0
        }.sum

    private def getOrderingRules(source: Source): Map[Int, Set[Int]] =
        source.getLines().takeWhile(line => line.trim.nonEmpty).foldLeft(Map.empty[Int, Set[Int]]) { (acc, line) =>
            val Array(key, value) = line.split("\\|").map(_.toInt)
            val updatedSet = acc.getOrElse(key, Set.empty[Int]) + value
            acc.updated(key, updatedSet)
        }

    private def getPageSequences(source: Source): List[List[Int]] =
        source.getLines().foldLeft(List.empty[List[Int]]) { (acc, line) =>
            acc.appended(line.split(",").map(_.toInt).toList)
        }