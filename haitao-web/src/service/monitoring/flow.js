import axios from './../util/axios.js'

// 按时间获取交通量流量统计列表
export const queryFlow = (options) => axios.setAxiosGetPromise(
  'monitoring/dataProcess/queryFlow', options)

// 按船舶类型计算交通量流量数据
export const queryFlowByShipType = (options) => axios.setAxiosGetPromise(
  'monitoring/dataProcess/queryFlowByShipType', options)
