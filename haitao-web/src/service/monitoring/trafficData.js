import axios from './../util/axios.js'

// 按条件交通量列表及融合相关操作
export const distinguish = (options) => axios.setAxiosGetPromise(
  'monitoring/trafficData/distinguish', options)
export const resetShipName = (options) => axios.setAxiosGetPromise(
  'monitoring/trafficData/resetShipName', options)
