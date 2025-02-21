package mikhail.shell.mpi.johnson

import kotlinx.coroutines.runBlocking
import mikhail.shell.mpi.common.print
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val matrix = JohnsonProblemUtils.generateRandomMatrix(10, 10)
    matrix.print()
    val solver: JohnsonProblemSolver = JpsBruteForce()
    val timeExecuted = measureTimeMillis {
        val (bestSequence, bestMakespan) = solver.solve(matrix)
        println("Лучшая последовательность обработки деталей: " + bestSequence.joinToString(", ", postfix = "."))
        println("Лучшее время обработки деталей: $bestMakespan.")
    }
    println("Выполнено за $timeExecuted мс.")
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