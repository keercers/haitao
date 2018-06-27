import axios from '../util/axios.js'

// 数据调整列表
export const getMonitorPointDataList = (option) => axios.setAxiosPostPromise('/monitoring/dataProcess/getMonitorPointDataExModify', option)
// 数据调整列表批量删除(修改)
export const batchUpdateMonitorPointData = (option) => axios.setAxiosGetPromise('transport/monitorPointData/delMonitorPointData', option)
// MonitorPointData mpdId查询
export const findByMpdId = (mpdId) => axios.setAxiosGetPromise('transport/monitorPointData/findByMpdId', mpdId)
// 数据调整MonitorPointDataModify 新增
export const addMonitorPointDataModify = (option) => axios.setAxiosPostPromise('/monitoring/dataProcess/addMonitorPointDataModify', option)
// MonitorPointDataModify mpdId查询
export const queryMonitorPointDataModifyByMpdId = (mpdId) => axios.setAxiosGetPromise('/monitoring/dataProcess/queryMonitorPointDataModifyByMpdId', mpdId)
// MonitorPointDataModify 更新
export const updateMonitorPointDataModify = (option) => axios.setAxiosPostPromise('/monitoring/dataProcess/updateMonitorPointDataModify', option)
// 数据调整 新增
export const addMonitorPointData = (option) => axios.setAxiosPostPromise('transport/monitorPointData/addMonitorPointData', option)
