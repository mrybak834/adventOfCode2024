package adventOfCode.day2.partOne

import adventOfCode.Solution
import scala.io.Source
import scala.collection.mutable.PriorityQueue

object Solution1 extends Solution[String, Int]:
    val name = "Two Pointer"

    def solve(name: String): Int = 
        var safeReports = 0
        val source = Source.fromFile(name)

        for(report <- source.getLines())
            val levels = report.split("\\s+") 
            
            var guardHit = false
            var trend = 0
            for(
                Array(current, next) <- levels.sliding(2,1)

                if !guardHit
                if {
                    val difference = next.toInt - current.toInt
                    val validDifference = difference != 0 && math.abs(difference) <= 3
                    val validPattern = 
                        if (trend == 0 || (trend < 0 && difference < 0) || (trend > 0 && difference > 0))
                            trend = difference
                            true
                        else false

                    val continue = validDifference && validPattern
                    if(!continue) 
                        guardHit = true
                        println(s"report UNSAFE: $report")
                    
                    continue
                }
            ) {}
            
            if (!guardHit)
                println(s"report safe: $report")
                safeReports += 1

            guardHit = false

        source.close()
        safeReports