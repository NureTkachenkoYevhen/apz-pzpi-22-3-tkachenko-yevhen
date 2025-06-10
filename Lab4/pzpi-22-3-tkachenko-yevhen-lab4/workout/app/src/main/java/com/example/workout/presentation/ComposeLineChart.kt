package com.example.workout.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun ComposeLineChart(
    heightData: List<Float>,
    tiltData: List<Float>,
    modifier: Modifier = Modifier
) {
    Canvas (modifier = modifier) {
        val chartWidth = size.width
        val chartHeight = size.height

        val maxHeight = (heightData + tiltData).maxOrNull() ?: 1f
        val minHeight = (heightData + tiltData).minOrNull() ?: 0f

        val yScale = if (maxHeight - minHeight == 0f) 1f else chartHeight / (maxHeight - minHeight)
        val xStep = if (heightData.size > 1) chartWidth / (heightData.size - 1) else chartWidth

        // Функция для преобразования значения в Offset
        fun mapToOffset(index: Int, value: Float): Offset {
            return Offset(
                x = index * xStep,
                y = chartHeight - (value - minHeight) * yScale
            )
        }

        // Нарисовать heightData
        val heightPath = Path().apply {
            if (heightData.isNotEmpty()) {
                moveTo(0f, mapToOffset(0, heightData[0]).y)
                for (i in 1 until heightData.size) {
                    lineTo(i * xStep, mapToOffset(i, heightData[i]).y)
                }
            }
        }
        drawPath(
            path = heightPath,
            color = Color.Blue,
            style = Stroke(width = 4f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )

        // Нарисовать tiltData
        val tiltPath = Path().apply {
            if (tiltData.isNotEmpty()) {
                moveTo(0f, mapToOffset(0, tiltData[0]).y)
                for (i in 1 until tiltData.size) {
                    lineTo(i * xStep, mapToOffset(i, tiltData[i]).y)
                }
            }
        }
        drawPath(
            path = tiltPath,
            color = Color.Red,
            style = Stroke(width = 4f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }
}
