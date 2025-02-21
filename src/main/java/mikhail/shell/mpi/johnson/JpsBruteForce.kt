package mikhail.shell.mpi.johnson

import mikhail.shell.mpi.common.print

class JpsBruteForce: JohnsonProblemSolver {
    override fun solve(matrix: Array<Array<Int>>): Pair<List<Int>, Int> {
        return findBestSequence(matrix)
    }
    // Функция для генерации всех перестановок списка
    private fun <T> List<T>.permutations(): List<List<T>> {
        if (size <= 1) return listOf(this)
        val perms = mutableListOf<List<T>>()
        for (i in indices) {
            val element = this[i]
            val remaining = this.take(i) + this.drop(i + 1)
            for (perm in remaining.permutations()) {
                perms.add(listOf(element) + perm)
            }
        }
        return perms
    }
    // Функция для вычисления makespan по данной последовательности (перестановке)
    private fun computeMakespan(permutation: List<Int>, processingTimes: Array<Array<Int>>): Int {
        val numJobs = permutation.size
        val numMachines = processingTimes[0].size
        // dp[i][j] – время завершения обработки i-й детали на j-м станке
        val dp = Array(numJobs) { IntArray(numMachines) }

        // Инициализация: обработка первой детали на первом станке
        dp[0][0] = processingTimes[permutation[0]][0]
        // Первая деталь на всех станках
        for (j in 1 until numMachines) {
            dp[0][j] = dp[0][j - 1] + processingTimes[permutation[0]][j]
        }
        // Для каждого следующего задания на первом станке
        for (i in 1 until numJobs) {
            dp[i][0] = dp[i - 1][0] + processingTimes[permutation[i]][0]
        }
        // Заполнение остальных ячеек таблицы
        for (i in 1 until numJobs) {
            for (j in 1 until numMachines) {
                dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1]) + processingTimes[permutation[i]][j]
            }
        }

        dp.print()

        // Makespan – время окончания последней детали на последнем станке
        return dp[numJobs - 1][numMachines - 1]
    }

    // Функция перебора всех решений и выбора оптимального
    private fun findBestSequence(processingTimes: Array<Array<Int>>): Pair<List<Int>, Int> {
        val numJobs = processingTimes.size
        val jobs = (0 until numJobs).toList()
        var bestSequence: List<Int>? = null
        var bestMakespan = Int.MAX_VALUE

        // Перебираем все перестановки
        for (perm in jobs.permutations().also { it.print() }) {
            val currentMakespan = computeMakespan(perm, processingTimes)
            if (currentMakespan < bestMakespan) {
                bestMakespan = currentMakespan
                bestSequence = perm
            }
        }
        return Pair(bestSequence!!, bestMakespan)
    }
}