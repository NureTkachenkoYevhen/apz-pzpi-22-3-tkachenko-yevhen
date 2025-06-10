import { createRouter, createWebHistory } from 'vue-router'
import LoginPage from '../pages/LoginPage.vue'
import RegisterPage from '../pages/RegisterPage.vue'
import MetricsPage from '../pages/MetricsPage.vue'
import SessionsPage from '../pages/SessionsPage.vue'
import RecommendationsPage from '../pages/RecommendationsPage.vue'
import { useAuthStore } from '../stores/auth'
import AdminPage from '../pages/AdminPage.vue'

const routes = [
  { path: '/login', component: LoginPage, meta: { public: true } },
  { path: '/register', component: RegisterPage, meta: { public: true } },
  // { path: '/', component: DashboardPage },
  { path: '/sessions', component: SessionsPage },
  { path: '/metrics/:sessionId', component: MetricsPage, props: true },
  { path: '/recommendations/:sessionId', component: RecommendationsPage, props: true },
  { path: '/admin', component: AdminPage }

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to) => {
  const auth = useAuthStore()
  if (!to.meta.public && !auth.isAuthenticated) {
    return '/login'
  }
})

export default router
