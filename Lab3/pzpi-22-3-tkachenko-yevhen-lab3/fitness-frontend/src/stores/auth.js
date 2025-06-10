import { defineStore } from 'pinia'
import http from '../api/http'
import { useRouter } from 'vue-router'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: null,
    token: localStorage.getItem('token') || null,
    userId: localStorage.getItem('userId') || null
  }),
  getters: {
    isAuthenticated: (state) => !!state.token,
    getUserId: (state) => state.userId
  },
  actions: {
    async login(credentials, router) {
      try {
        const { data } = await http.post('/auth/login', credentials)
        this.user = data
        this.userId = data.userId
        this.token = 'dummy-token' // Since your backend doesn't use tokens yet
        localStorage.setItem('token', this.token)
        localStorage.setItem('userId', this.userId)
        await router.push('/sessions')
      } catch (error) {
        console.error('Login failed:', error)
        throw error
      }
    },
    async register(payload, router) {
      try {
        const { data } = await http.post('/auth/register', payload)
        // After successful registration, redirect to login page
        router.push('/login')
      } catch (error) {
        console.error('Registration failed:', error)
        throw error
      }
    },
    async logout(router) {
      this.token = null
      this.user = null
      this.userId = null
      localStorage.removeItem('token')
      localStorage.removeItem('userId')
      await router.push('/login')
    }
  }
})
