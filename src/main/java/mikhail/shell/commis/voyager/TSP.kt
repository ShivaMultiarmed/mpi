package mikhail.shell.commis.voyager

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
fun Array<IntArray>.print() {
    for (i in 0..this.size -1 ) {
        for (j in 0..this[0].size - 1) {
            print("${this[i][j]}\t")
        }
        println()
    }
}