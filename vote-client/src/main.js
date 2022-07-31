import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import qs from 'qs'
axios.defaults.baseURL = 'http://localhost:8888'
Vue.config.productionTip = false
Vue.prototype.$qs = qs
Vue.use(ElementUI);

// Vue.use(router)
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
