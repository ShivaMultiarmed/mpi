package mikhail.shell.commis.voyager

class TSPBruteForce(private val dist: Array<IntArray>): TSP {
    val n: Int = dist.size
    var bestCost: Int = Int.MAX_VALUE
    var bestRoute: List<Int> = emptyList()

    override fun calculate() {
        val cities = (1 until n).toMutableList()
        val currentRoute = mutableListOf(0)

        permute(cities, currentRoute, 0)

        println("Минимальная стоимость маршрута: $bestCost")
        println("Оптимальный маршрут: $bestRoute")
    }

    private fun permute(cities: MutableList<Int>, currentRoute: MutableList<Int>, currentCost: Int) {
        if (cities.isEmpty()) {
            val totalCost = currentCost + dist[currentRoute.last()][0]
            if (totalCost < bestCost) {
                bestCost = totalCost
                bestRoute = currentRoute + 0
            }
        } else {
            for (i in cities.indices) {
                val city = cities[i]
                val newCost = currentCost + dist[currentRoute.last()][city]
                if (newCost < bestCost) {
                    val newRoute = currentRoute.toMutableList().apply { add(city) }
                    val remaining = cities.toMutableList().apply { removeAt(i) }
                    permute(remaining, newRoute, newCost)
                }
            }
        }
    }
}

