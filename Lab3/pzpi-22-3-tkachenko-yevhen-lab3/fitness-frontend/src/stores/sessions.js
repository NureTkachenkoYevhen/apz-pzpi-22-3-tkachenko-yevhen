import { defineStore } from 'pinia'
import http from '../api/http'
import { useAuthStore } from './auth'

export const useSessionsStore = defineStore('sessions', {
  state: () => ({
    items: []
  }),
  actions: {
    async fetch() {
      const auth = useAuthStore()
      const { data } = await http.get(`/sessions/user/${auth.getUserId}`)
      this.items = data
    },
    async create () {
      const auth = useAuthStore()
      const pullUpId = 1 // Упражнение "подтягивание", всегда одно
      console.log(auth.getUserId)
    
      const { data } = await http.post('/sessions', {
        userId: auth.getUserId,
        exerciseId: pullUpId
      })
    
      this.items.push(data)
    },
    async update(item) {
      const { data } = await http.put(`/sessions/${item.id}`, item)
      const idx = this.items.findIndex(i => i.id === data.id)
      if (idx !== -1) this.items[idx] = data
    },
    async remove(id) {
      await http.delete(`/sessions/${id}`)
      this.items = this.items.filter(i => i.id !== id)
    }
  }
})
