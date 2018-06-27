import axios from './../util/axios.js'

export const List = (options) => axios.setAxiosGetPromise('sys/menu/menuList', options)
export const Del = (options) => axios.setAxiosGetPromise('sys/menu/menuDel', options)
export const All = (options) => axios.setAxiosGetPromise('sys/menu/menuAll', options)
export const One = (options) => axios.setAxiosGetPromise('sys/menu/menuOne', options)
export const Add = (options) => axios.setAxiosPostPromise('sys/menu/menuAdd', options)
export const Update = (options) => axios.setAxiosPostPromise('sys/menu/menuUpdate', options)
