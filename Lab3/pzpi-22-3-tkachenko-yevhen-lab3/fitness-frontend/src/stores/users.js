// src/stores/users.js
import { defineStore } from 'pinia'
import http from '../api/http'

export const useUsersStore = defineStore('users', {
  state: () => ({
    items: []
  }),
  actions: {
    async fetchAll() {
      const { data } = await http.get('/users')
      this.items = data
    },
    async deleteUser(userId) {
      await http.delete(`/users/${userId}`)
      this.items = this.items.filter(u => u.userId !== userId)
    },
    async updateUser(user) {
      const { data } = await http.put(`/users/${user.userId}`, user)
      const idx = this.items.findIndex(u => u.userId === data.userId)
      if (idx !== -1) this.items[idx] = data
    }
  }
})
