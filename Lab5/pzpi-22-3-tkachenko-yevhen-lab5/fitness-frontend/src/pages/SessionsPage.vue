<template>
  <div>
    <h2 class="text-2xl font-bold mb-4">Sessions</h2>

    <div class="mb-6">
      <button @click="addSession" class="bg-blue-600 text-white px-4 py-2 rounded">
        New session
      </button>
    </div>

    <ul class="space-y-2">
      <li v-for="s in sessions.items" :key="s.sessionId"
          class="bg-white p-4 rounded shadow flex flex-col md:flex-row md:justify-between">
        
        <div>
          <span class="font-mono text-xs text-gray-500">#{{ s.sessionId }}</span>
        </div>

        <div class="mt-2 md:mt-0 flex gap-4 text-sm">
          <RouterLink :to="`/metrics/${s.sessionId}`" class="text-blue-600">Metrics</RouterLink>
          <RouterLink :to="`/recommendations/${s.sessionId}`" class="text-green-600">Recommendations</RouterLink>
        </div>

        <span class="text-sm text-gray-600 mt-2 md:mt-0">
          {{ new Date(s.endTime).toLocaleString() }}
        </span>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useSessionsStore } from '../stores/sessions'

const sessions = useSessionsStore()

onMounted(sessions.fetch)

const addSession = () => {
  sessions.create()
}
</script>
