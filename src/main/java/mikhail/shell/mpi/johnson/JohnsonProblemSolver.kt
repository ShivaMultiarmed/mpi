package mikhail.shell.mpi.johnson

import kotlinx.coroutines.runBlocking
import mikhail.shell.mpi.common.print
import java.io.FileOutputStream
import java.io.PrintWriter
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val fos = FileOutputStream("accompanying_folder/lab2/task1/results.txt")
    PrintWriter(fos).use {
        for (i in 2..10) {
            val matrix = JohnsonProblemUtils.generateRandomMatrix(i, 2)
            matrix.print()
            val solver: JohnsonProblemSolver = JpsBruteForce()
            val timeExecuted = measureTimeMillis {
                val (bestSequence, bestMakespan) = solver.solve(matrix)
                println(
                    "Лучшая последовательность обработки деталей: " + bestSequence.joinToString(
                        ", ",
                        postfix = "."
                    )
                )
                println("Лучшее время обработки деталей: $bestMakespan.")
            }
            println("Выполнено за $timeExecuted мс.")
            it.println("$i\t$timeExecuted")
        }
    }
}

interface JohnsonProblemSolver {
    fun solve(matrix: Array<Array<Int>>): Pair<List<Int>, Int>
}
object JohnsonProblemUtils {
    fun generateRandomMatrix(m: Int, n: Int): Array<Array<Int>> {
        return Array(m) {
            Array(n) {
                (1..40).random()
            }
        }
    }
}