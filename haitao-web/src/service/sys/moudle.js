import axios from './../util/axios.js'

export const queryTopList = (options) => axios.setAxiosGetPromise('sys/moudle/getMoudleByLevel', options)
export const querySideTree = (options) => axios.setAxiosGetPromise('sys/moudle/getMoudleTreeBySign', options)
// 获取禁止权限
export const getForbid = () => axios.setAxiosGetPromise('sys/moudle/getForbid')
