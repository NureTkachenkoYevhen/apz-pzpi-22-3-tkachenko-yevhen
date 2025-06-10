<template>
  <div>
    <NavBar />
    <main class="p-4">
      <router-view />
    </main>
  </div>
</template>

<script setup>
import NavBar from './components/NavBar.vue'

</script>
