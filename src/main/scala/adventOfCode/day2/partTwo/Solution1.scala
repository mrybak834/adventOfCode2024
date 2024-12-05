package adventOfCode.day2.partTwo

import adventOfCode.Solution
import scala.io.Source
import java.io.{BufferedWriter, FileWriter}
import scala.util.Using

object Solution1 extends Solution[String, Int]:
    val name = "I fucking hate that i did this. Consider every edge case of checking 4 elements.."

    def isValidLevel(current: Int, next: Int, trend: Int): Boolean =
        val difference = next - current
        trendPreserved(difference, trend) && validDifference(difference)

    def trendPreserved(difference: Int, trend: Int): Boolean = 
        (trend > 0 && difference > 0) || (trend < 0 && difference < 0)

    def validDifference(difference: Int): Boolean =
        val absoluteDifference = math.abs(difference)
        absoluteDifference <= 3 && absoluteDifference > 0

    def canDampenLevel(
        currentIndex: Int,
        previous: Option[Int],
        current: Option[Int],
        next: Option[Int],
        doubleNext: Option[Int],
        trend: Int
    ): (Boolean, Boolean, Int) =

        var dampened = false
        var skipIndex = false
        var newTrend = trend

        if (previous.isEmpty)
            dampened = true
            newTrend = doubleNext.get - next.get

        if (previous.isDefined && currentIndex == 1 && !dampened)
            val potentialNewTrend = next.get - current.get
            if (isValidLevel(current.get, next.get, potentialNewTrend)) 
                newTrend = potentialNewTrend
                dampened = true

        if (previous.isDefined && currentIndex == 1 && !dampened)
            val potentialNewTrend = next.get - previous.get
            if (isValidLevel(previous.get, next.get, potentialNewTrend)) 
                newTrend = potentialNewTrend
                dampened = true

        if (previous.isDefined && !dampened)
            if (isValidLevel(previous.get, next.get, trend))
                if (doubleNext.isDefined && isValidLevel(next.get, doubleNext.get, trend)) 
                    newTrend = trend
                    dampened = true
                else if (doubleNext.isEmpty)
                    newTrend = trend
                    dampened = true

        if (doubleNext.isEmpty && !dampened)
            dampened = true
            skipIndex = true

        if (doubleNext.isDefined && !dampened) 
            if (isValidLevel(current.get, doubleNext.get, trend))
                dampened = true
                skipIndex = true

        (dampened, skipIndex, newTrend)

    def isReportSafe(report: String): Boolean =
        val levels = report.split("\\s+").map(level => level.toInt)

        var trend = levels(1) - levels(0)
        var (usedDampener, index, isSafe) = (false, 0, true)

        while((index < levels.size - 1) && isSafe) // Until (n-1)th element
            val isValidLevel = Solution1.isValidLevel(levels(index), levels(index+1), trend)

            if (!isValidLevel)
                if (usedDampener) isSafe = false
                else
                    val (canDampenLevel, skipIndex, newTrend) = Solution1.canDampenLevel(
                        index,
                        levels.lift(index-1), 
                        levels.lift(index), 
                        levels.lift(index+1),
                        levels.lift(index+2),
                        trend
                    )
                    if (!canDampenLevel) isSafe = false
                    else if (skipIndex) index += 1
                    trend = newTrend
                    usedDampener = true
            index += 1

        isSafe

    def solve(name: String): Int =
        val source = Source.fromFile(name)

        var sum = 0
        for(report <- source.getLines())
            if (isReportSafe(report)) 
                sum += 1
        sum

    // def solve(inputFile: String): Int =
    //     var sum = 0
    //     Using.Manager { use =>
    //         val source = use(Source.fromFile(inputFile))
    //         val writer = use(new BufferedWriter(new FileWriter("outputA.txt")))

    //         for report <- source.getLines() do
    //             val safeStatus = if isReportSafe(report) 
    //             then 
    //                 "SAFE"
    //                 sum += 1
    //             else "UNSAFE"
    //             writer.write(s"$report - $safeStatus\n")
    //     }.getOrElse(throw new RuntimeException("Error processing files"))

    //     sum
