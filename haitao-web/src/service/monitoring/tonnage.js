import axios from './../util/axios.js'

// 按时间获取交通量吨位统计列表
export const queryTonnageStatisticData = (options) => axios.setAxiosGetPromise(
  'monitoring/dataProcess/queryTonnageStatisticData', options)
