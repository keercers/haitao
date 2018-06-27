import axios from './../util/axios.js'

// 查询日志
export const getLogList = (params) => axios.setAxiosGetPromise('sys/log/listLogByParams', params)

// 查询日志类型
export const getLogTypes = () => axios.setAxiosGetPromise('sys/enum/listEnumValues', { enumName: 'LogTypeEnum' })
