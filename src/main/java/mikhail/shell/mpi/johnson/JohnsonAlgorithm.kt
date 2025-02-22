package mikhail.shell.mpi.johnson

class JohnsonAlgorithm : JohnsonProblemSolver {
    override fun solve(matrix: Array<Array<Int>>): Pair<List<Int>, Int> {
        val queue: MutableList<Int> = mutableListOf()
        var detailWithMinSpan: Int
        while (matrix.size > queue.size) {
            var minSpan = Int.MAX_VALUE
            detailWithMinSpan = matrix.size
            matrix.forEachIndexed { i, detail ->
                if (i !in queue) {
                    if (detail[0] < minSpan) {
                        detailWithMinSpan = i
                        minSpan = detail[0]
                    } else if (detail[1] < minSpan) {
                        detailWithMinSpan = i
                        minSpan = detail[1]
                    }
                }
            }
            if (matrix[detailWithMinSpan][0] <= matrix[detailWithMinSpan][1]) {
                queue.add(0, detailWithMinSpan)
            } else {
                queue.add(detailWithMinSpan)
            }
        }
        return queue to 0
    }
}