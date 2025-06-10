package com.example.workout.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.roundToInt


@Composable
fun MetricsScreen(id: Long, vm: MetricsVM = viewModel()) {
    LaunchedEffect(id) { vm.load(id) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Session #$id", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        if (vm.data.isEmpty()) {
            Text("No data")
        } else {
            val heightData = vm.data.map { it.height ?: 0f }
            val tiltData = vm.data.map { it.tiltAngle ?: 0f }

            // Определяем максимальное и минимальное значение для оси Y
            val maxHeight = (heightData + tiltData).maxOrNull() ?: 1f
            val minHeight = (heightData + tiltData).minOrNull() ?: 0f

            // Вычисляем высоту графика
            val chartHeight = 200.dp

            // Отображаем график
            ComposeLineChart(
                heightData = heightData,
                tiltData = tiltData,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(chartHeight)
            )

            // Отображаем значения под графиком
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Min: ${minHeight.roundToInt()} cm", color = Color.Blue)
                Text("Max: ${maxHeight.roundToInt()} cm", color = Color.Red)
            }
        }
    }
}
