<template>
  <div>
    <h2 class="text-2xl font-bold mb-4">Рекомендации</h2>

    <ul class="space-y-4">
      <li v-for="rec in recommendations.items" :key="rec.recommendationId"
          class="bg-white p-4 rounded shadow">
        <p class="text-gray-800 whitespace-pre-line">{{ rec.message }}</p>
        <p class="text-xs text-gray-500 mt-2">Создано: {{ formatDate(rec.createdAt) }}</p>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useRecommendationsStore } from '../stores/recommendations'

const props = defineProps({ sessionId: String })
const recommendations = useRecommendationsStore()

onMounted(() => recommendations.fetchBySession(props.sessionId))

function formatDate(dateStr) {
  return new Date(dateStr).toLocaleString()
}
</script>
