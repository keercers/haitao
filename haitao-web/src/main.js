import Vue from 'vue'
import Vuex from 'vuex'
import ElementUI from 'element-ui' // 饿了么插件
import VueBlu from 'vue-blu'
import 'element-ui/lib/theme-default/index.css'
import App from './App.vue'
import axios from 'axios'
import routers from './router/index'
import VueRouter from 'vue-router'
import base from './assets/js/base/base' // 自定义组件
import store from './store'
import mainCss from './assets/css/main.css'
import util from './util/util.js'

Vue.use(base)
Vue.use(VueRouter)
Vue.use(ElementUI)
Vue.use(Vuex)
Vue.use(VueBlu)
Vue.use(mainCss)
Vue.use(util)

try {
  const wb = new window.WebStorageCache({ storage: 'localStorage', exp: 604800 })
  const wb2 = new window.WebStorageCache({ storage: 'localStorage', exp: 86400 })
  wb.deleteAllExpires()
  wb2.deleteAllExpires()
} catch (error) {
  console.warn(error)
}

// 路由初始化 history模式
var router = new VueRouter({
  routes: routers
})
let countMsg = 0
// 路由启用
const app = new Vue({
  router,
  store,
  data () {
    return {
      httpStatus: '',
      httpMsg: '',
      httpType: ''
    }
  },
    // 监听路由检查登录
  watch: {
    '$route': 'checkLogin',
    'httpStatus': 'showMsg'
  },
  created () {
    this.checkLogin()
  },
  methods: {
    checkLogin () {
    // 检查是否存在userName
      if (!this.getcookie('userName')) {
        // 如果没有登录状态则跳转到登录页
        this.$router.push('/')
      }
    },
    showMsg () {
      this.$message({
        message: this.httpMsg,
        type: this.httpType
      })
    }
  },
  render: h => h(App)
}).$mount('#app')
// 请求发送前拦截
axios.interceptors.request.use(
  config => {
    var source = axios.CancelToken.source()
    config.cancelToken = source.token
    var forbidList = localStorage.getItem('forbidList')
    if (forbidList !== null && forbidList.indexOf(config.url.match(/\w+$/)) > -1) {
      source.cancel('没有权限,请求被取消！')
    }
    return config
  },
  err => {
    return Promise.reject(err)
  }
)
// 请求返回拦截
axios.interceptors.response.use(
  response => {
    // 设置token
    if (response.headers.token) {
      localStorage.setItem('token', response.headers.token)
      axios.defaults.headers.common.token = localStorage.getItem('token')
    }
    return response
  },
  error => {
    if (!error.response && error.message) {
      throw error
    }
    let errorStatus = error.response.data.status
    // 400以上为错误信息，[300,400)信息为警告信息
    if (errorStatus >= 400) {
      app.httpType = 'error'
    } else {
      app.httpType = 'warning'
    }
    app.httpStatus = errorStatus + countMsg
    app.httpMsg = error.response.data.message
    countMsg++
    if (errorStatus === 506) {
      router.push('/')
    }
    throw error
  }
)

export default app
