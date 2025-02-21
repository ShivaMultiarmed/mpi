import org.junit.jupiter.api.Test
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.PrintWriter


class MyTest {
    @Test
    fun replaceColons() {
        PrintWriter(FileOutputStream("accompanying_folder/lab1/task2/results_copy.txt")).use { writer ->
            FileInputStream("accompanying_folder/lab1/task2/results.txt").bufferedReader().use { reader ->
                reader.forEachLine {
                    writer.println(it.replace(": ", "\t"))
                }
            }
        }
    }
}