import axios from './../util/axios.js'

export const List = (options) => axios.setAxiosGetPromise('sys/buttomRegister/buttomRegisterList', options)
export const Del = (options) => axios.setAxiosGetPromise('sys/buttomRegister/buttomRegisterDel', options)
export const One = (options) => axios.setAxiosGetPromise('sys/buttomRegister/buttomRegisterOne', options)
export const add = (options) => axios.setAxiosPostPromise('sys/buttomRegister/buttomRegisterAdd', options)
export const update = (options) => axios.setAxiosPostPromise('sys/buttomRegister/buttomRegisterUpdate', options)
