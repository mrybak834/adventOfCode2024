package adventOfCode.day1.partOne

import scala.io.Source
import scala.collection.mutable.PriorityQueue

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

import org.reflections.Reflections
import org.reflections.scanners.Scanners.SubTypes
import scala.jdk.CollectionConverters._
import adventOfCode.getSolutions
import adventOfCode.Solution
import adventOfCode.benchmarkSolutions
import scala.collection.immutable.TreeSet

@main def solution =
    val solutions = getSolutions[Solution[String, Int]]("adventOfCode.day1.partOne")
    println()

    benchmarkSolutions(solutions, "/workspaces/adventofcode/src/main/scala/day1/input.txt", printResult = true)

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

// GPT GENERATED FROM HERE
object Solution2 extends Solution[String, Int]:
    val name = "TreeSet"

    def solve(name: String): Int = 
        val (sortedA, sortedB) = getSortedNumbers(name)
        calculateTotalDistance(sortedA, sortedB)

    def getSortedNumbers(name: String): (List[Int], List[Int]) =
        // Use TreeSet to maintain sorted order during insertion
        val (sortedA, sortedB) = Source.fromFile(name).getLines().foldLeft((TreeSet.empty[Int], TreeSet.empty[Int])) {
            case ((setA, setB), line) =>
                val Array(a, b) = line.split("\\s+").map(_.toInt)
                (setA + a, setB + b) // Insert into the TreeSets
        }
        (sortedA.toList, sortedB.toList)

    def calculateTotalDistance(sortedA: List[Int], sortedB: List[Int]): Int = 
        // Zip the sorted lists and calculate the sum of absolute differences
        sortedA.zip(sortedB).map { case (a, b) => math.abs(a - b) }.sum

object Solution3 extends Solution[String, Int]:
    val name = "FoldLeft"

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

object Solution4 extends Solution[String, Int]:
    val name = "DirectSorting"

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
