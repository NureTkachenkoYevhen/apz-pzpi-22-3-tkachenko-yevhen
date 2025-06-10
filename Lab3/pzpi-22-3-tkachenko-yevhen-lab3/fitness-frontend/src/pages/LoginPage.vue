<template>
  <div class="max-w-md mx-auto mt-10 bg-white p-6 rounded shadow">
    <h2 class="text-xl font-bold mb-4">Login</h2>
    <form @submit.prevent="submit">
      <div class="mb-4">
        <label class="block mb-1">Username</label>
        <input v-model="form.username" required class="w-full p-2 border rounded" />
      </div>
      <div class="mb-4">
        <label class="block mb-1">Password</label>
        <input v-model="form.password" type="password" required class="w-full p-2 border rounded" />
      </div>
      <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded">Login</button>
    </form>
    <p class="mt-4"><router-link class="text-blue-600" to="/register">Register here</router-link></p>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useAuthStore } from '../stores/auth'
import { useRouter } from 'vue-router'

const router = useRouter()
const form = reactive({ username: '', password: '' })
const auth = useAuthStore()

const submit = () => auth.login(form, router)
</script>
