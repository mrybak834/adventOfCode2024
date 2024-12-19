package adventOfCode.day6

import adventOfCode.day6.Tile.*

import scala.io.Source
import scala.util.Using

case class PositionedTile(position: Position, tile: Tile)

case class Guard(position: Position, tile: GuardTile)

case class Grid(rows: Vector[Vector[Tile]], guard: Guard):
    def moveGuard(next: Guard): Grid = this.markVisited().move(next)

    def move(next: Guard): Grid = move(next.position, next.tile)

    def move(position: Position, tile: GuardTile): Grid = Grid(rows = updatedRows(position, tile), guard = Guard(position, tile))

    def markVisited(): Grid = mark(guard.position, Visited)

    def mark(position: Position, tile: Tile): Grid = Grid(rows = updatedRows(position, tile), guard)

    def updatedRows(position: Position, tile: Tile): Vector[Vector[Tile]] = rows.updated(position.row, rows(position.row).updated(position.col, tile))


    def inBounds(position: Position): Boolean =
        position.row < rows.length - 1 && position.row > 0 &&
            position.col < rows.head.length - 1 && position.col > 0

    def print(): Unit =
        rows.foreach { row =>
            println(row.map {
                case tile@(Visited | Up | Down | Left | Right) => Console.YELLOW + tile.symbol + Console.RESET
                case tile => tile.symbol
            }.mkString(" "))
        }

    def tile(position: Position): Tile = rows(position.row)(position.col)

object Grid:
    def rows(fileName: String): Vector[Vector[Tile]] =
        Using(Source.fromFile(fileName)) {
            _.getLines().map {
                _.map(Tile(_)).toVector
            }.toVector
        }.get

    def findGuard(rows: Vector[Vector[Tile]]): Guard =
        rows.zipWithIndex.flatMap { case (row, rowIndex) =>
            row.zipWithIndex.collectFirst {
                case (tile: GuardTile, colIndex) => Guard(Position(rowIndex, colIndex), tile)
            }
        }.head

    def paddedRows(rows: Vector[Vector[Tile]]): Vector[Vector[Tile]] =
        rows
            .map(row => Exit +: row :+ Exit)
            .prepended(Vector.fill(rows.head.length + 2)(Exit))
            .appended(Vector.fill(rows.head.length + 2)(Exit))
