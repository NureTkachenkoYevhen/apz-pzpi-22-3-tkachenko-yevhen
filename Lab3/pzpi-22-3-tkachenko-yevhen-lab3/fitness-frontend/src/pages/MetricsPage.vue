<template>
  <MetricChart
    :labels="labels"
    :heightValues="heights"
    :tiltValues="tilts"
  />
</template>

<script setup>
import { computed, onMounted } from 'vue'
import { useMetricsStore } from '../stores/metrics'
import MetricChart from '../components/MetricChart.vue'
import { useRoute } from 'vue-router'


const metrics = useMetricsStore()
const route = useRoute()
onMounted(() => metrics.fetchBySession(route.params.sessionId))

const labels = computed(() =>
  metrics.data.map(m => new Date(m.timestamp).toLocaleTimeString()))
const heights = computed(() => metrics.data.map(m => m.height))
const tilts = computed(() => metrics.data.map(m => m.tiltAngle))
</script>
