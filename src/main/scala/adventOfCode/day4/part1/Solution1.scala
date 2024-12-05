package adventOfCode.day4.part1

import adventOfCode.Solution
import scala.io.Source
import java.io.{BufferedWriter, FileWriter}
import scala.util.Using
import scala.util.matching.Regex

object Solution1 extends Solution[String, Int]:
    val name = "imperative"

    enum DIRECTION(val vector: (Int, Int)):
        case RIGHT      extends DIRECTION((0, 1))
        case LEFT       extends DIRECTION((0, -1))
        case DOWN       extends DIRECTION((1, 0))
        case UP         extends DIRECTION((-1, 0))
        case DOWN_RIGHT extends DIRECTION((1, 1))
        case DOWN_LEFT  extends DIRECTION((1, -1))
        case UP_RIGHT   extends DIRECTION((-1, 1))
        case UP_LEFT    extends DIRECTION((-1, -1))

    val word = "XMAS"

    type Matrix = Array[Array[(Char, Boolean)]]

    def printMatrix(matrix: Matrix): Unit =
        matrix.foreach { row =>
            println(row.map {
                case (char, true)  => Console.YELLOW + char + Console.RESET
                case (char, false) => char.toString
            }.mkString(" "))
        }
        println()

    def markMatrix(position: (Int, Int), matrix: Matrix): Unit =
        matrix(position._1)(position._2) = (matrix(position._1)(position._2)._1, true)

    def getEnd(direction: DIRECTION, rowIndex: Int, colIndex: Int): (Int, Int) =
        val appliedDirection = (direction.vector._1 * 3, direction.vector._2 * 3)
        (rowIndex + appliedDirection._1, colIndex + appliedDirection._2)
    
    def canMove(direction: DIRECTION, matrix: Matrix, rowIndex: Int, colIndex: Int): Boolean =
        val (endRow, endCol) = getEnd(direction, rowIndex, colIndex)
        matrix.lift(endRow).flatMap(_.lift(endCol)).isDefined

    def foundLetters(direction: DIRECTION, matrix: Matrix, rowIndex: Int, colIndex: Int): String =
        var letters = ""
        for(i <- 1 to 3)
            val (row, col) = (direction.vector._1 * i, direction.vector._2 * i)
            letters += matrix(row + rowIndex)(col + colIndex)._1
        letters

    def markLetters(direction: DIRECTION, matrix: Matrix, rowIndex: Int, colIndex: Int): Unit =
        for(i <- 0 to 3)
            val (row, col) = (direction.vector._1 * i, direction.vector._2 * i)
            markMatrix((row + rowIndex, col + colIndex), matrix)

    def wordSearch(matrix: Matrix, rowIndex: Int, colIndex: Int): Int =
        var sum = 0
        for (direction <- DIRECTION.values)
            if (canMove(direction, matrix, rowIndex, colIndex) && s"X${foundLetters(direction, matrix, rowIndex, colIndex)}" == word)
                sum += 1
                markLetters(direction, matrix, rowIndex, colIndex)
                // printMatrix(matrix)
        sum

    def solve(name: String): Int =
        val matrix: Matrix = Using (Source.fromFile(name)) { source =>
            source.getLines().map(line => line.toCharArray().map((_, false))).toArray
        }.getOrElse(Array[Array[(Char, Boolean)]]())

        var sum = 0
        for(rowIndex <- matrix.indices)
            for (colIndex <- matrix(rowIndex).indices)
                if (matrix(rowIndex)(colIndex)._1 == 'X') 
                    sum += wordSearch(matrix, rowIndex, colIndex)

        // printMatrix(matrix)

        sum