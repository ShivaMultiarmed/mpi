package mikhail.shell.mpi.common

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.scene.chart.XYChart.Series
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File
import java.util.Collections.max

fun main() {
    Application.launch(PlotGraphMain::class.java)
}

class PlotGraphMain: Application() {
    override fun start(p0: Stage?) {
        GraphWindow().show()
    }
}

class GraphWindow: Stage() {
    private val root = VBox()
    init {
        val scene = Scene(root, 800.0, 800.0)
        this.scene = scene
        val fileChoosingButton = Button("Выбрать данные")
        root.children.add(fileChoosingButton)
        fileChoosingButton.setOnMouseClicked {
            val fileChooser = FileChooser()
            fileChooser.initialDirectory = File("./")
            val file = fileChooser.showOpenDialog(this)
            val series = convertToSeries(file)
            val lineChart = createLineChart(series)
            if (root.children.last() !is Button) {
                root.children.removeLast()
            }
            root.children.add(lineChart)
        }
    }

    private fun convertToSeries(file: File): Series<Number, Number> {
        val data = Series<Number, Number>()
        file.inputStream().use {
            it.bufferedReader().use {
                it.forEachLine {
                    val (x, y) = it.split("\t").map { it.toLong() }
                    data.data.add(XYChart.Data(x, y))
                }
            }
        }
        return data
    }

    private fun createLineChart(series: Series<Number, Number>): LineChart<Number, Number> {
        val xAxis = NumberAxis(0.0, max(series.data.map { it.xValue.toInt() }).toDouble(), 1.0)
        val yAxis = NumberAxis(0.0, max(series.data.map { it.yValue.toInt() }).toDouble(), 100.0)

        return LineChart(xAxis, yAxis).also {
            it.data.add(series)
            it.data[0].data.forEach { dataPoint ->
                dataPoint.node?.style = "-fx-background-radius: 2px; -fx-padding: 2px;"
            }
        }
    }
}

