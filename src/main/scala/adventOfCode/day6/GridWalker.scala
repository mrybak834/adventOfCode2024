package adventOfCode.day6

import scala.annotation.tailrec

class GridWalker(val originalGrid: Grid):

    var grid: Grid = originalGrid

    def stepsToLeaveGrid: Int =
        var steps = 0
        while (grid.inBounds(grid.guard.position)) {
            val nextGuard = nextGuardPosition(grid.guard)
            grid = grid.moveGuard(nextGuard)
            steps += 1
        }
        steps

    def amountOfLoops: Int =
        var loops = 0

        for ((row, rowIndex) <- originalGrid.rows.zipWithIndex)
            for ((col, colIndex) <- row.zipWithIndex)
                if (col == Empty)
                    grid = originalGrid.mark(Position(rowIndex, colIndex), Obstacle)
                    try stepsToLeaveGrid
                    catch case e: IllegalStateException => loops += 1

        loops

    def nextPosition(guard: Guard): Guard = Guard(guard.position.move(guard.tile.direction), guard.tile)

    def print(): Unit = grid.print()

    @tailrec
    private final def nextGuardPosition(guard: Guard): Guard =
        val nextPosition = this.nextPosition(guard)
        val nextTile = grid.tile(nextPosition.position)

        nextTile match
            case Obstacle => nextGuardPosition(Guard(guard.position, guard.tile.turn))
            case Visited => nextGuardPosition(nextPosition)
            case Empty | Visited | Exit => nextPosition
            case nextTile: GuardTile => throw new IllegalStateException(s"Going in a circle!")


object GridWalker:
    def apply(fileName: String): GridWalker =
        val paddedRows = Grid.paddedRows(Grid.rows(fileName))
        new GridWalker(Grid(paddedRows, Grid.findGuard(paddedRows)))