package mikhail.shell.commis.voyager

import kotlinx.coroutines.runBlocking
import mikhail.shell.commis.voyager.TSPUtils.generateSymmetricRandomTSPMatrix
import java.io.FileOutputStream
import java.io.PrintStream
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = runBlocking {
//    val times = mutableListOf<Long>()
//    for (i in 5 .. 25) {
//        val graph = generateSymmetricRandomTSPMatrix(i, 10 * i)
//        val time = measureTimeMillis {
//            TSPBruteForce(graph).calculate()
//        }
//        println("Время выполнения: $time ms.")
//        times.add(time)
//    }
//    println("Прогрессия времени:")
//    times.forEach {
//        print("$it мс \t")
//    }

    val output = FileOutputStream("output.txt").use { fos ->
        PrintStream(fos).use { printStream ->
            for (i in 5..1000) {
                val time = measureTimeMillis {
                    val d = generateSymmetricRandomTSPMatrix(i, 10 * i)
                    val tsp: TSP = TSPAntAlgorithm(d)
                    tsp.calculate()
                }
                printStream.println("$i: $time")
            }
        }
    }
}