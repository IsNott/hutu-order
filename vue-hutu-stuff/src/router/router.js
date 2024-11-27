import Router from 'vue-router'

export const routers = [
  {
    path: '/cust-broad',
    component: () => import('@/views/cust-broad/index.vue')
  }
]

const createRouter = () => new Router({
  mode: 'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: routers
})

const router = createRouter()

export default router