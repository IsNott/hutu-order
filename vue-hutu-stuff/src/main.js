import Vue from 'vue'
import App from './App.vue'
import Router from 'vue-router'
import router from './router/router'
import ElementUI from 'element-ui'

import 'element-ui/lib/theme-chalk/index.css';

import "./assets/icon/iconfont.css"

Vue.config.productionTip = false
Vue.use(Router).use(ElementUI)
Vue.config.errorHandler = (err, vm, info) => {
  console.error(err);
}
const vue = new Vue({
  render: h => h(App),
  router
}).$mount('#app')
