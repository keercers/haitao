import axios from './../util/axios.js'

// 连接后台进行查询岗位列表
export const searchPositionList = (positionPagination) => axios.setAxiosGetPromise('sys/position/getPosList', positionPagination)

export const queryAllPosition = (positionPagination) => axios.setAxiosGetPromise('sys/position/listAllPosition')

// 新增岗位
export const addPosition = (position) => axios.setAxiosPostPromise('sys/position/addPosition', position)

// 删除岗位
export const delPosition = (posId) => axios.setAxiosGetPromise('sys/position/delPosition', posId)

// 编辑岗位
export const updatePosition = (position) => axios.setAxiosPostPromise('sys/position/updatePosition', position)

// 查找岗位
export const getPosition = (posId) => axios.setAxiosGetPromise('sys/position/getByPosId', posId)

// 加载岗位类型列表
export const getPositionTypes = () => axios.setAxiosGetPromise('sys/enum/listEnumValues', {
  enumName: 'PosTypeEnum'
})
