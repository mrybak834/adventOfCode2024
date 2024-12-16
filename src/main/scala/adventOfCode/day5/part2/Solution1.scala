package adventOfCode.day5.part2

import adventOfCode.Solution

import scala.collection.immutable.{Map, Set}
import scala.collection.mutable
import scala.io.Source
import scala.util.Using

object Solution1 extends Solution[String, Int]:
    val name = "graph"

    def solve(name: String): Int =
        val (orderingRules, pageSequences): (Map[Int, Set[Int]], List[Array[Int]]) = Using(Source.fromFile(name)) { source =>
            (getOrderingRules(source), getPageSequences(source))
        }.get // throw exception if failed reading

        getCorrectedUpdatesSum(orderingRules, pageSequences)

    private def getCorrectedUpdatesSum(orderingRules: Map[Int, Set[Int]], pageSequences: List[Array[Int]]): Int =
        pageSequences.map { sequence =>
            var pageIndex = 0
            val pagesSeen = mutable.Set.empty[Int]
            var validSequence = true
            var validPage = true

            while(pageIndex < sequence.length)
                val page = sequence(pageIndex)
                validPage = Solution1.validPage(orderingRules, pagesSeen, page)

                if(!validPage)
                    validSequence = false
                    val swapPageIndex = correctPageIndex(sequence, pageIndex, orderingRules)
                    val swapPage = sequence(swapPageIndex)
                    sequence(swapPageIndex) = page
                    sequence(pageIndex) = swapPage
                    pageIndex -= 1

                pagesSeen.add(page)
                pageIndex += 1

            if (!validSequence) sequence(sequence.length / 2) else 0
        }.sum

    private def correctPageIndex(sequence: Array[Int], pageIndex: Int, orderingRules: Map[Int, Set[Int]]): Int =
        val rules = orderingRules(sequence(pageIndex))

        var correctIndex = -1
        var index = 0
        while(index < sequence.length && correctIndex == -1)
            if(rules.contains(sequence(index))) correctIndex = index
            index += 1
        correctIndex

    private def validPage(orderingRules: Map[Int, Set[Int]], pagesSeen: mutable.Set[Int], page: Int): Boolean =
        orderingRules.get(page) match
            case Some(precedenceSet) => pagesSeen.intersect(precedenceSet).isEmpty
            case None => true


    private def getOrderingRules(source: Source): Map[Int, Set[Int]] =
        source.getLines().takeWhile(line => line.trim.nonEmpty).foldLeft(Map.empty[Int, Set[Int]]) { (acc, line) =>
            val Array(key, value) = line.split("\\|").map(_.toInt)
            val updatedSet = acc.getOrElse(key, Set.empty[Int]) + value
            acc.updated(key, updatedSet)
        }

    private def getPageSequences(source: Source): List[Array[Int]] =
        source.getLines().foldLeft(List.empty[Array[Int]]) { (acc, line) =>
            acc.appended(line.split(",").map(_.toInt).toArray)
        }