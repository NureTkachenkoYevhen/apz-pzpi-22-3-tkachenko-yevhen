import { defineStore } from 'pinia'
import http from '../api/http'

export const useRecommendationsStore = defineStore('recommendations', {
  state: () => ({ items: [] }),
  actions: {
    async fetchBySession (sessionId) {
      const { data } = await http.post(`/recommendations/${sessionId}`)
      this.items = [data]
    }
  }
})
