import axios from './util/axios.js'

// 系统登录连接后台请求
export const basicPlatformLogin = (options) => axios.setAxiosGetPromise('sys/user/login', options)

// 系统退出连接后台请求
export const basicPlatformLogout = (options) => axios.setAxiosPostPromise('sys/user/logout', options)

// 修改密码
export const updatePwd = (options) => axios.setAxiosGetPromise('sys/user/changePass', options)
