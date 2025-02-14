package mikhail.shell.commis.voyager

import java.util.*
import kotlin.math.pow

class TSPAntAlgorithm(
    val distances: Array<IntArray>
) : TSP {
    private val n = distances.size
    private var pheromones = Array(n) { DoubleArray(n) }

    init {
        for (i in 0 until n) {
            for (j in 0 until n) {
                pheromones[i][j] = 1.0
            }
        }
    }

    private val ALPHA = 1.0 // Влияние феромонов
    private val BETA = 5.0 // Влияние расстояний
    private val EVAPORATION_RATE = 0.5 // Скорость испарения феромонов
    private val Q = 100.0 // Количество феромонов, производимых
    private var random = Random(System.nanoTime())

    override fun calculate() {
        val iterations = 100 // Количество итераций
        var bestPathLength = Double.MAX_VALUE
        var bestPath: MutableList<Int>?

        for (iteration in 0 until iterations) {
            val currentPath: MutableList<Int> =
                ArrayList()
            var currentLength = 0.0

            // Генерация пути муравья
            val startCity = random.nextInt(n)
            currentPath.add(startCity)
            val visited = BooleanArray(n)
            visited[startCity] = true

            for (step in 1 until n) {
                val nextCity = selectNextCity(currentPath[step - 1], visited)
                currentPath.add(nextCity)
                visited[nextCity] = true
                currentLength += distances[currentPath[step - 1]][nextCity].toDouble()
            }

            // Добавляем расстояние до начального города для завершения пути
            currentLength += distances[currentPath[currentPath.size - 1]][startCity]
                .toDouble()

            // Если найден лучший путь, обновляем его
            if (currentLength < bestPathLength) {
                bestPathLength = currentLength
                bestPath = ArrayList(currentPath)
                bestPath.add(startCity) // Возвращаемся в начальную точку
            }

            // Обновляем феромоны на основании текущего пути
            updatePheromones(currentPath, currentLength)
            evaporatePheromones()
        }
    }

    private fun selectNextCity(currentCity: Int, visited: BooleanArray): Int {
        var total = 0.0
        val probabilities = DoubleArray(n)

        for (city in 0 until n) {
            if (!visited[city]) {
                probabilities[city] =
                    pheromones[currentCity][city].pow(ALPHA) * (1.0 / distances[currentCity][city]).pow(
                        BETA
                    )
                total += probabilities[city]
            }
        }

        val rand = random!!.nextDouble() * total
        total = 0.0

        for (city in 0 until n) {
            if (!visited[city]) {
                total += probabilities[city]
                if (total >= rand) {
                    return city
                }
            }
        }

        return -1 // Это не должно происходить
    }

    private fun updatePheromones(path: List<Int>, length: Double) {
        val pheromoneAmount = Q / length

        for (i in 0 until path.size - 1) {
            val cityFrom = path[i]
            val cityTo = path[i + 1]
            pheromones[cityFrom][cityTo] += pheromoneAmount
            pheromones[cityTo][cityFrom] += pheromoneAmount // Симметричный граф
        }
    }

    private fun evaporatePheromones() {
        for (i in 0 until n) {
            for (j in 0 until n) {
                pheromones[i][j] *= (1 - EVAPORATION_RATE)
            }
        }
    }
}