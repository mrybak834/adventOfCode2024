package adventOfCode.day1.partOne

import adventOfCode.Solution
import scala.io.Source
import scala.collection.mutable.ArrayBuffer

object Solution3 extends Solution[String, Int]:
    val name = "GPT: ArrayBuffer"

    def solve(name: String): Int = 
        val (sortedA, sortedB) = getSortedNumbers(name)
        calculateTotalDistance(sortedA, sortedB)

    def getSortedNumbers(name: String): (Array[Int], Array[Int]) =
        val listA = ArrayBuffer[Int]()
        val listB = ArrayBuffer[Int]()

        val bufferedSource = Source.fromFile(name)
        for (line <- bufferedSource.getLines()) {
            val Array(a, b) = line.split("\\s+").map(_.toInt)
            listA += a
            listB += b
        }
        bufferedSource.close()

        // Sort during insertion by converting to arrays and sorting
        (listA.sorted.toArray, listB.sorted.toArray)

    def calculateTotalDistance(sortedA: Array[Int], sortedB: Array[Int]): Int = 
        var sum = 0
        for (i <- sortedA.indices) {
            sum += math.abs(sortedA(i) - sortedB(i))
        }
        sum
