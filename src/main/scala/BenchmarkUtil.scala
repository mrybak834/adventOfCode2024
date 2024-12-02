//> using dep "org.reflections:reflections:0.10.2"
//> using dep "org.slf4j:slf4j-simple:2.0.16"

package adventOfCode

import scala.concurrent.duration._

import org.reflections.Reflections
import org.reflections.scanners.Scanners.SubTypes
import scala.jdk.CollectionConverters._
import scala.reflect.ClassTag

// Define a generic trait for solutions
trait Solution[I, O] {
    def name: String
    def solve(input: I): O
}

// Function to measure performance of a single solution
def measurePerformance[I, O](solution: Solution[I, O], input: I): (O, Duration, Long) = {
    val runtime = Runtime.getRuntime
    runtime.gc() // Suggest garbage collection

    val startMem = runtime.totalMemory() - runtime.freeMemory()
    val startTime = System.nanoTime()

    val result = solution.solve(input)

    val endTime = System.nanoTime()
    val endMem = runtime.totalMemory() - runtime.freeMemory()
        
    val timeElapsed = (endTime - startTime).nanos
    val memUsed = endMem - startMem

    (result, timeElapsed, memUsed)
}

// Function to benchmark multiple solutions
def benchmarkSolutions[I, O](solutions: Seq[Solution[I, O]], input: I, printResult: Boolean = true): Unit = {
    def formatResult(result: Any): String = result match {
        case arr: Array[_] => arr.mkString("[", ", ", "]")
        case seq: Seq[_] => seq.mkString("[", ", ", "]")
        case _ => result.toString
    }
    for (solution <- solutions) {
        val (result, time, memoryUsed) = measurePerformance(solution, input)
        println(s"${solution.name}:\n Result = ${if(printResult) formatResult(result) else ""}, Time = ${time.toMillis} ms, Memory used = ${memoryUsed / 1024} KB")
    }
}


def getSolutions[S](packageName: String)(implicit ct: ClassTag[S]): Seq[S] = {
  val reflections = new Reflections(packageName, SubTypes)
  val subtypes = reflections.getSubTypesOf(ct.runtimeClass.asInstanceOf[Class[S]]).asScala
  subtypes.flatMap { cls =>
    Some(cls.getField("MODULE$").get(null).asInstanceOf[S])
  }.toSeq
}
