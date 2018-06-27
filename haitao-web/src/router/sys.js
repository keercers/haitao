
import sys from '../views/sys/sys.vue'
export default {
  path: '/sys',
  component: sys,
  children: [
    { path: '', component: resolve => require(['../views/sys/sysDictionary.vue'], resolve) },
    { path: '/sysDictionary', name: 'sysDictionary', component: resolve => require(['../views/sys/sysDictionary.vue'], resolve) },
    { path: '/company', name: 'company', component: resolve => require(['../views/sys/company.vue'], resolve) },
    { path: '/position', name: 'position', component: resolve => require(['../views/sys/position.vue'], resolve) },
    { path: '/role', name: 'role', component: resolve => require(['../views/sys/role.vue'], resolve) },
    { path: '/roleManage', name: 'roleManage', component: resolve => require(['../views/sys/roleManage.vue'], resolve) },
    { path: '/department', name: 'department', component: resolve => require(['../views/sys/department.vue'], resolve) },
    { path: '/user', name: 'user', component: resolve => require(['../views/sys/user.vue'], resolve) },
    { path: '/menu', name: 'menu', component: resolve => require(['../views/sys/menu.vue'], resolve) },
    { path: '/sysRegister', name: 'sysRegister', component: resolve => require(['../views/sys/sysRegister.vue'], resolve) },
    { path: '/buttomRegister', name: 'buttomRegister', component: resolve => require(['../views/sys/buttomRegister.vue'], resolve) }
  ]
}
