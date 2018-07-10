import Vue from 'vue'

import 'normalize.css/normalize.css'// A modern alternative to CSS resets

import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import '@/styles/index.scss' // global css

import App from './App'
import router from './router'
import store from './store'

import i18n from './lang' // Internationalization
import './icons' // icon
import './errorLog'// error log
import './permission' // permission control
// import './mock' // simulation data


import * as filters from './filters' // global filters

// import VueNativeSock from 'vue-native-websocket'
// Vue.use(VueNativeSock, 'ws://118.24.0.78:9505')

Vue.use(Element, {
  size: 'medium', // set element-ui default size
  i18n: (key, value) => i18n.t(key, value)
})

// register global utility filters.
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false

window.bus = new Vue({
  el: '#app',
  router,
  store,
  i18n,
  template: '<App/>',
  components: { App }
})
import axios from "axios"
axios.interceptors.response.use(response => {
  if(response.data.code == -1){
    window.sessionStorage.removeItem("user")
    bus.$router.push({ name: 'login'})
    bus.$message({
          message: '登陆失效',
          type: 'warning'
        });
  }
  else if(response.data.code == -2){
    window.sessionStorage.removeItem("user")
    bus.$router.push({ name: 'login'})
    bus.$message({
          message: '未登陆',
          type: 'warning'
        });
  }
  else{
    if(response.data.code == 0){
      bus.$message({
        message: '操作失败',
        type: 'warning'
      });
      const err = new Error()

      throw err
      
    }else{
      return response
    }
  }
}, error => {
  return Promise.reject(error)
})
export default axios
window.axios = require('axios');