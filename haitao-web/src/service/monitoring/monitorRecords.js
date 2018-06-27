import axios from './../util/axios.js'

// 按条件获取监控信息列表
export const queryAllMonitorRecordsData = (options) => axios.setAxiosGetPromise(
  'monitoring/monitorRecordsData/queryAllMonitorRecordsData', options)
export const queryMonitorRecordByMpdId = (options) => axios.setAxiosGetPromise(
  'monitoring/monitorRecordsData/queryMonitorRecordByMpdId', options)
// 删除监控记录
export const delMonitorData = (options) => axios.setAxiosGetPromise(
  'monitoring/monitorRecordsData/delMonitorData', options)
