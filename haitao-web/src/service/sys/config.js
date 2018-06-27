import axios from './../util/axios.js'

// 连接后台进行查询配置列表
export const querySysParamList = (configPagination) => axios.setAxiosGetPromise('sys/sysconf/querySysParam', configPagination)

// 新增配置
export const addConfig = (config) => axios.setAxiosPostPromise('sys/sysconf/saveSysParam', config)

// 删除配置
export const delConfig = (confId) => axios.setAxiosGetPromise('sys/sysconf/deleteSysParam', confId)

// 编辑配置
export const updateConfig = (config) => axios.setAxiosPostPromise('sys/sysconf/updateSysParam', config)

// 查找配置
export const getConfig = (confId) => axios.setAxiosGetPromise('sys/sysconf/getByConfId', confId)

// 查找配置参数组
export const getConfTypes = () => axios.setAxiosGetPromise('sys/enum/listEnumValues', { enumName: 'ConfTypeEnum' })
