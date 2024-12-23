package adventOfCode.day8.part1

import adventOfCode.Solution

import scala.collection.mutable
import scala.io.Source
import scala.util.Using



object Solution1 extends Solution[String, Int]:
    val name = "iterative"

    case class Grid(rows: Array[Array[Char]]):

        def validPosition(position: Position): Boolean =
            rows.lift(position.row).flatMap(_.lift(position.col)).isDefined

        def isTower(position: Position): Boolean =
            ! Set('.', '#').contains(rows(position.row)(position.col))

        def isEmpty(position: Position): Boolean =
            rows(position.row)(position.col) == '.'

        def setAntenode(antenode: Position): Unit = rows(antenode.row)(antenode.col) = '#'

    
    case class Position(row: Int, col: Int):
        def +(other: Position) : Position =
            Position(this.row + other.row, this.col + other.col)

    def solve(name: String): Int =
        val antennas: mutable.Map[Char, mutable.Set[Position]] = mutable.Map.empty
        
        val grid : Grid = Grid(Using(Source.fromFile(name)) { source =>
            source.getLines().zipWithIndex.map { (line, rowIndex) =>
                line.zipWithIndex.map { (char, colIndex) =>

                    if (char != '.')
                        val current = antennas.getOrElse(char, mutable.Set.empty[Position])
                        antennas(char) = current + Position(rowIndex, colIndex)

                    char
                }.toArray
            }.toArray
        }.get)



        val antenodes: Grid = Grid(Array.tabulate(grid.rows.length, grid.rows.head.length)((_, _) => '.'))
        antennas.foreach{ (antennaType, positions) =>
            positions.foreach{ position =>
                positions.diff(Set(position)).foreach{ otherPosition =>
                    val distance = Position(otherPosition.row - position.row, otherPosition.col - position.col)
                    val antenode = otherPosition + distance
                    if (grid.validPosition(antenode))
                        antenodes.setAntenode(antenode)
                }
            }
        }

        print(grid)
        println()
        print(antenodes)

        antenodes.rows.flatten.count(_ == '#')

    def print(grid: Grid): Unit =
        grid.rows.foreach { row =>
            Predef.print(row.mkString(" "))
            println()
        }