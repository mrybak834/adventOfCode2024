package adventOfCode.day7.part1

import adventOfCode.Solution

import scala.annotation.tailrec
import scala.io.Source
import scala.util.Using



object Solution1 extends Solution[String, Long]:
    val name = "recursive"

    private def equations(fileName: String): List[(Long, List[Int])] =
        Using(Source.fromFile(fileName)) { source =>
            source.getLines().map(_.split(":").map(_.trim) match
                case Array(t, ns) => {
                    (t.toLong, ns.split("\\s+").map(_.trim).map(_.toInt).toList)
                }
            ).toList
        }.get

    private def canMakeTarget(target: Long, numbers: List[Int], concat: Boolean): Boolean =
        case class Branch(numbers: List[Int], acc: Long)

        @tailrec
        def tailRec(branches: List[Branch]): Boolean =
            if (branches.isEmpty) return false //exhausted all options

            val Branch(numbers, acc) = branches.head

            if (numbers.isEmpty)
                if (acc == target) true
                else tailRec(branches.tail) // exhausted branch
            else
                tailRec(
                    List(
                        Branch(numbers.tail, numbers.head + acc),
                        Branch(numbers.tail, numbers.head * acc),
                    ).concat(Option.when(concat) {
                        Branch(numbers.tail, s"$acc${numbers.head}".toLong)
                    })
                    .concat(branches.tail)
                )

        tailRec(List(Branch(numbers.tail, numbers.head)))

    def equationsCanMakeTarget(name: String, concat: Boolean = false): Long =
        equations(name).collect { case (target, numbers) if canMakeTarget(target, numbers, concat) => target }.sum


    def solve(name: String): Long =
        equationsCanMakeTarget(name)