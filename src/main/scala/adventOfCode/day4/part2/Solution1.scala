package adventOfCode.day4.part2

import adventOfCode.Solution
import scala.io.Source
import java.io.{BufferedWriter, FileWriter}
import scala.util.Using
import scala.util.matching.Regex

object Solution1 extends Solution[String, Int]:
    val name = "imperative"

    case class Position(row: Int, col: Int)

    enum DIRECTION(val position: Position):
        case DOWN_RIGHT extends DIRECTION(Position(1, 1))
        case DOWN_LEFT  extends DIRECTION(Position(1, -1))
        case UP_RIGHT   extends DIRECTION(Position(-1, 1))
        case UP_LEFT    extends DIRECTION(Position(-1, -1))

    val word = "MS"
    type Matrix = Array[Array[(Char, Boolean)]]

    def printMatrix(matrix: Matrix): Unit =
        matrix.foreach { row => println(row.map {
            case (char, true)  => Console.YELLOW + char + Console.RESET
            case (char, false) => char.toString}.mkString(" "))}
        println()

    def markMatrix(position: Position, matrix: Matrix): Unit = matrix(position.row)(position.col) = (matrix(position.row)(position.col)._1, true)
    def markLetters(matrix: Matrix, position: Position): Unit = DIRECTION.values.foreach(direction => markMatrix(movePosition(position, direction.position), matrix))
    def valueExists(matrix: Matrix, position: Position): Boolean = matrix.lift(position.row).flatMap(_.lift(position.col)).isDefined
    def movePosition(position: Position, direction: Position): Position = Position(position.row + direction.row, position.col + direction.col)
    def positionValue(matrix: Matrix, position: Position): Char = matrix.lift(position.row).flatMap(_.lift(position.col)).get._1
    def canMove(matrix: Matrix, position: Position): Boolean = DIRECTION.values.forall(direction => valueExists(matrix, movePosition(position, direction.position)))

    def foundLetters(matrix: Matrix, position: Position): Boolean =
        val upLeft = positionValue(matrix, movePosition(position, DIRECTION.UP_LEFT.position))
        val bottomRight = positionValue(matrix, movePosition(position, DIRECTION.DOWN_RIGHT.position))
        val upRight = positionValue(matrix, movePosition(position, DIRECTION.UP_RIGHT.position))
        val bottomLeft = positionValue(matrix, movePosition(position, DIRECTION.DOWN_LEFT.position))
        
        s"$upLeft$bottomRight".toSet == word.toSet && s"$upRight$bottomLeft".toSet == word.toSet


    def wordSearch(matrix: Matrix, position: Position): Int =
        var sum = 0
        if (canMove(matrix, position) && foundLetters(matrix, position))
            sum += 1
            markLetters(matrix, position)
        sum

    def solve(name: String): Int =
        val matrix: Matrix = Using (Source.fromFile(name)) { source =>
            source.getLines().map(line => line.toCharArray().map((_, false))).toArray
        }.getOrElse(Array[Array[(Char, Boolean)]]())

        var sum = 0
        for(rowIndex <- matrix.indices)
            for (colIndex <- matrix(rowIndex).indices)
                if (matrix(rowIndex)(colIndex)._1 == 'A') 
                    sum += wordSearch(matrix, Position(rowIndex, colIndex))

        printMatrix(matrix)

        sum