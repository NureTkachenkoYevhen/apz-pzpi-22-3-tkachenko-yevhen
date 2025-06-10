<!-- src/pages/AdminPage.vue -->
<template>
    <div>
      <h2 class="text-2xl font-bold mb-4">Користувачі</h2>
  
      <table class="w-full text-left border">
        <thead>
          <tr class="bg-gray-200">
            <th class="p-2">ID</th>
            <th class="p-2">Ім’я</th>
            <th class="p-2">Email</th>
            <th class="p-2">Зріст</th>
            <th class="p-2">Дії</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in users.items" :key="user.userId">
            <td class="p-2">{{ user.userId }}</td>
            <td class="p-2">{{ user.username }}</td>
            <td class="p-2">{{ user.email }}</td>
            <td class="p-2">{{ user.height }} см</td>
            <td class="p-2 space-x-2">
              <button class="text-red-600" @click="deleteUser(user.userId)" :disabled="user.userId === 1">
                Видалити
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script setup>
  import { onMounted } from 'vue'
  import { useUsersStore } from '../stores/users'
  import { useAuthStore } from '../stores/auth'
  import { useRouter } from 'vue-router'
  
  const auth = useAuthStore()
  const router = useRouter()
  
  // 🔒 Якщо не адмін — перенаправити
  if (auth.getUserId !== '1') {
    router.push('/sessions')
  }
  
  const users = useUsersStore()
  onMounted(users.fetchAll)
  
  const deleteUser = async (id) => {
    if (confirm('Ви впевнені, що хочете видалити користувача?')) {
      await users.deleteUser(id)
    }
  }
  </script>
  