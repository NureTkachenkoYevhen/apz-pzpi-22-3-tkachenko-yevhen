<!-- src/components/Navbar.vue -->
<template>
  <header class="bg-white shadow">
    <div class="max-w-7xl mx-auto px-4 py-4 flex justify-between items-center">
      <h1 class="text-2xl font-bold">Workout</h1>
      
      <nav class="space-x-4 flex items-center">
        <RouterLink to="/sessions" class="hover:text-blue-600">Sessions</RouterLink>
        <RouterLink
  v-if="auth.getUserId === '1'"
  to="/admin"
  class="hover:text-indigo-600"
>
  Admin
</RouterLink>
        <button
          v-if="auth.isAuthenticated"
          @click="auth.logout(router)"
          class="text-red-600 hover:underline"
        >
          Quit
        </button>
      </nav>
    </div>
  </header>
</template>

<script setup>
import { useAuthStore } from '../stores/auth'
import { RouterLink } from 'vue-router'
import { useRouter } from 'vue-router'

const router = useRouter()

const auth = useAuthStore()
</script>
