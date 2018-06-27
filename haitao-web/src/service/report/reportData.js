import axios from './../util/axios.js'

// 报表数据的相关操作
export const getReportData = (options) => axios.setReportAxiosGetPromise(
  'monitoring/reportView/get', options)
export const getOprReportData = (options) => axios.setReportAxiosGetPromise(
  'operation/reportView/get', options)
