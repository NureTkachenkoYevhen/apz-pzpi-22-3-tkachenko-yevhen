import { defineStore } from 'pinia'
import http from '../api/http'

export const useMetricsStore = defineStore('metrics', {
  state: () => ({ data: [] }),

  actions: {
    async fetchBySession (sessionId) {
      const { data } = await http.get(`/metrics/${sessionId}`)
      // сортируем по времени
      this.data = data.sort((a, b) => new Date(a.timestamp) - new Date(b.timestamp))
    }
  }
})