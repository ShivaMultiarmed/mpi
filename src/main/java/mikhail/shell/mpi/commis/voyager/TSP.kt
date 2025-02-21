package mikhail.shell.mpi.commis.voyager

import kotlinx.coroutines.runBlocking
import mikhail.shell.mpi.commis.voyager.TSPUtils.generateSymmetricRandomTSPMatrix
import mikhail.shell.mpi.common.print
import java.io.FileOutputStream
import java.io.PrintStream
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = runBlocking {
    val output = FileOutputStream("accompanying_folder/lab1/task2/results.txt").use { fos ->
        PrintStream(fos).use { printStream ->
            for (i in 5..1000) {
                val time = measureTimeMillis {
                    val d = generateSymmetricRandomTSPMatrix(i, 10 * i)
                    val tsp: TSP = TSPAntAlgorithm(d)
                    tsp.calculate()
                }
                printStream.println("$i\t$time")
            }
        }
    }
}

interface TSP {
    fun calculate()
}
object TSPUtils {
    fun generateRandomTSPMatrix(n: Int, maxCost: Int): Array<IntArray> {
        val matrix = Array(n) { IntArray(n) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                matrix[i][j] = if (i == j) 0 else (1..maxCost).random()
            }
        }
        matrix.print()
        return matrix
    }
    fun generateSymmetricRandomTSPMatrix(n: Int, maxCost: Int): Array<IntArray> {
        val matrix = Array(n) { IntArray(n) }
        for (i in 1 until n) {
            for (j in 0 until i) {
                val number = (1..maxCost).random()
                matrix[i][j] = number
                matrix[j][i] = number
            }
        }
        matrix.print()
        return matrix
    }
}