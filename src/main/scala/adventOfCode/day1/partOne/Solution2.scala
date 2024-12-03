package adventOfCode.day1.partOne

import adventOfCode.Solution
import scala.io.Source
import scala.collection.immutable.TreeSet

object Solution2 extends Solution[String, Int]:
    val name = "GPT: FoldLeft"

    def solve(name: String): Int = 
        val (sortedA, sortedB) = getSortedNumbers(name)
        calculateTotalDistance(sortedA, sortedB)

    def getSortedNumbers(name: String): (List[Int], List[Int]) =
        // Read and split numbers into two lists, sorting directly
        val (listA, listB) = Source.fromFile(name).getLines().foldLeft((List.empty[Int], List.empty[Int])) {
            case ((accA, accB), line) =>
                val Array(a, b) = line.split("\\s+").map(_.toInt)
                (a :: accA, b :: accB)
        }
        (listA.sorted, listB.sorted)

    def calculateTotalDistance(sortedA: List[Int], sortedB: List[Int]): Int = 
        // Zip the sorted lists and calculate the sum of absolute differences
        sortedA.zip(sortedB).map { case (a, b) => math.abs(a - b) }.sum