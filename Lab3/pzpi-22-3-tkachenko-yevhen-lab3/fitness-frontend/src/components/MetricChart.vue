<template>
  <canvas ref="canvas"></canvas>
</template>

<script setup>
import { onMounted, ref, watch } from 'vue'
import {
  Chart,
  LineController,
  LineElement,
  PointElement,
  LinearScale,
  Title,
  CategoryScale,
  Tooltip,
  Legend
} from 'chart.js'

Chart.register(LineController, LineElement, PointElement, LinearScale, Title, CategoryScale, Tooltip, Legend)

const props = defineProps({
  labels: Array,         
  heightValues: Array,   
  tiltValues: Array      
})

const canvas = ref(null)
let chart

onMounted(() => {
  chart = new Chart(canvas.value, {
    type: 'line',
    data: {
      labels: props.labels,
      datasets: [
        {
          label: 'Height (cm)',
          data: props.heightValues,
          borderColor: 'blue',
          yAxisID: 'y1',
          tension: 0.3
        },
        {
          label: 'Tilt (°)',
          data: props.tiltValues,
          borderColor: 'orange',
          yAxisID: 'y2',
          tension: 0.3
        }
      ]
    },
    options: {
      responsive: true,
      interaction: {
        mode: 'index',
        intersect: false
      },
      stacked: false,
      plugins: {
        legend: { position: 'top' },
        title: { display: true, text: 'Analysis' }
      },
      scales: {
        y1: {
          type: 'linear',
          position: 'left',
          title: { display: true, text: 'Height (cm)' }
        },
        y2: {
          type: 'linear',
          position: 'right',
          grid: { drawOnChartArea: false },
          title: { display: true, text: 'Tilt (°)' }
        }
      }
    }
  })
})

watch(() => [props.labels, props.heightValues, props.tiltValues], () => {
  chart.data.labels = props.labels
  chart.data.datasets[0].data = props.heightValues
  chart.data.datasets[1].data = props.tiltValues
  chart.update()
})
</script>
