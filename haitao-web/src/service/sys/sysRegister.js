import axios from './../util/axios.js'

export const List = (options) => axios.setAxiosGetPromise('sys/sysRegister/sysRegisterList', options)
export const Del = (options) => axios.setAxiosGetPromise('sys/sysRegister/sysRegisterDel', options)
export const One = (options) => axios.setAxiosGetPromise('sys/sysRegister/sysRegisterOne', options)
export const add = (options) => axios.setAxiosPostPromise('sys/sysRegister/sysRegisterAdd', options)
export const update = (options) => axios.setAxiosPostPromise('sys/sysRegister/sysRegisterUpdate', options)
