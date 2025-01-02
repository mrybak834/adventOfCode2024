package adventOfCode.day9.part1

import adventOfCode.Solution

import scala.io.Source
import scala.util.Using



object Solution1 extends Solution[String, Int]:
    def toBlocks(diskMap: Array[String]): Array[String] =
        diskMap.zipWithIndex.foldLeft((Array.empty[Char], 0)) {
            case ((blocks, id), (input, index))  =>
                if (index % 2 == 0)
                    (blocks ++ Array.fill(input)(#), id + 1)
                else
                    (blocks ++ Array.fill(input)('.'), id)
        }._1

    def solve(name: String): Int =
        val diskMap:  Array[String] = Using(Source.fromFile(name)) { source =>
            source.getLines().flatMap(_.map(_.toString)).toArray
        }.get

        println(diskMap.mkString(""))
        println()

        val blocks = toBlocks(diskMap)

        println(blocks.mkString(""))
        println()

        1
