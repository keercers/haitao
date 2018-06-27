import Vue from 'vue'
import Vuex from 'vuex'
import login from './modules/login'
import position from './modules/sys/position'
import department from './modules/sys/department'
import moudle from './modules/sys/moudle'
import company from './modules/sys/company'
import config from './modules/sys/config'
import role from './modules/sys/role'
import user from './modules/sys/user'
import dictionary from './modules/sys/dictionary'
import roleMoudle from './modules/sys/roleMoudle'
import enumType from './modules/sys/enumType'
import sysRegister from './modules/sys/sysRegister'
import menu from './modules/sys/menu'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    login,
    position,
    department,
    moudle,
    company,
    config,
    role,
    user,
    roleMoudle,
    enumType,
    sysRegister,
    menu,
    dictionary
  }
})
