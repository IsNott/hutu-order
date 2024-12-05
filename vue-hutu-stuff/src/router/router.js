import Router from 'vue-router'

export const routers = [
  {
    path: '/cust-broad',
    meta: { title: '叫号大屏' },
    component: () => import('@/views/cust-broad/index.vue')
  }
]

const createRouter = () => new Router({
  mode: 'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: routers
})

const router = createRouter()

router.beforeEach((to, from, next) => {
  to.meta.title && (document.title = to.meta.title);
  next();
});
export default router