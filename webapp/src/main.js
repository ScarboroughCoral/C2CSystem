import Vue from 'vue'

import 'normalize.css/normalize.css'// A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n

import '@/styles/index.scss' // global css

import App from './App'
import router from './router'
import store from './store'

import '@/icons' // icon
import '@/permission' // permission control
import uploader from 'vue-simple-uploader'

Vue.use(ElementUI, { locale })
Vue.use(uploader)

Vue.config.productionTip = false

Vue.prototype.meetingStatus = {
  WAITING:0,
  STARTING:1,
  END:2
}

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
