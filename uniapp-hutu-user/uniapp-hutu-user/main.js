
// #ifndef VUE3
import Vue from 'vue'
import App from './App'
import { isEmpty,isNotEmpty } from './utils/CommonUtils'
Vue.config.productionTip = false
Vue.prototype.baseUrl = 'http://localhost:10220'
App.mpType = 'app'
Vue.prototype.isEmpty = isEmpty
Vue.prototype.isNotEmpty = isNotEmpty
const app = new Vue({
    ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
import App from './App.vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif