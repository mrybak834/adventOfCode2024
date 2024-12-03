package adventOfCode.day1.partOne

import adventOfCode.Solution
import scala.io.Source
import scala.collection.mutable.PriorityQueue

object Solution1 extends Solution[String, Int]:
    val name = "PriorityQueue"

    def solve(name: String): Int = 
        val (pqA, pqB) = getSortedNumbers(name)
        calculateTotalDistance(pqA, pqB)

    def getSortedNumbers(name: String): (PriorityQueue[Int], PriorityQueue[Int]) =
        // Ascending order
        val pqA = PriorityQueue[Int]()(Ordering.Int.reverse)
        val pqB = PriorityQueue[Int]()(Ordering.Int.reverse)

        val bufferedSource = Source.fromFile(name)
        for(line <- bufferedSource.getLines())
            val Array(a,b) = line.split("\\s+")
            pqA.enqueue(a.toInt)
            pqB.enqueue(b.toInt)
        bufferedSource.close()

        (pqA, pqB)

    def calculateTotalDistance(pqA: PriorityQueue[Int], pqB: PriorityQueue[Int]): Int =
        var sum = 0
        while(pqA.nonEmpty) {
            sum += math.abs(pqA.dequeue() - pqB.dequeue())
        }
        sum