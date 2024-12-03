package adventOfCode.day2.partOne

import adventOfCode.Solution
import scala.io.Source
import scala.collection.mutable.PriorityQueue

object Solution1 extends Solution[String, Int]:
    val name = "Sliding: for loop guard"

    def solve(name: String): Int = 
        var safeReports = 0
        val source = Source.fromFile(name)

        for(report <- source.getLines())
            val levels = report.split("\\s+") 
            
            var guardHit = false
            var trend = 0
            for(
                (current, next) <- levels.sliding(2,1).map { case Array(c,n) => (c.toInt, n.toInt)}

                if !guardHit
                if {
                    val difference = next - current
                    val valid = validSequence(difference, trend)
                    trend = difference
                    if(!valid) guardHit = true
                    valid
                }
            ) {}
            
            if (!guardHit)
                safeReports += 1

            guardHit = false
            trend = 0

        source.close()
        safeReports

    def validSequence(difference: Int, trend: Int): Boolean =
        val validDifference = difference != 0 && math.abs(difference) <= 3
        val validPattern = 
            trend == 0 || 
            (trend < 0 && difference < 0) || 
            (trend > 0 && difference > 0)

        validDifference && validPattern
