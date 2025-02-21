package mikhail.shell.mpi.common

fun Array<IntArray>.print() {
    for (i in 0..<this.size) {
        for (j in 0..<this[0].size) {
            print("${this[i][j]}\t")
        }
        println()
    }
}

fun Array<Array<Int>>.print() {
    for (i in 0..<this.size) {
        for (j in 0..<this[0].size) {
            print("${this[i][j]}\t")
        }
        println()
    }
}