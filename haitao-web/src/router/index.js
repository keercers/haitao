
import login from '../views/login.vue'
import sysIndex from './sysIndex.js'
import Index from '../views/index.vue'
import monitoring from './monitoring.js'
import sys from './sys.js'

const routers = [
  { path: '/', redirect: '/login' },
  { path: '/login', name: 'login', component: login },
  {
    path: '/index',
    component: Index,
    children: [
      monitoring,
      sysIndex,
      sys
    ]
  }
]

export default routers
