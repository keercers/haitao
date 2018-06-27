import axioss from './../util/axios.js'

// 获取系统字典Group列表
export const queryDictionaryGroupList = () => axioss.setAxiosGetPromise(
  'sys/dictionary/querySysDictionaryGroupList')

// 获取系统字典Item列表
export const queryDictionaryItemList = (options) => axioss.setAxiosGetPromise(
  'sys/dictionary/querySysDictionaryItemListByDictGroupId', options)

// 增加
export const addSysDictionaryItem = (options) => axioss.setAxiosPostPromise(
  'sys/dictionary/addSysDictionaryItem', options)

// 编辑
export const updateSysDictionaryItem = (options) => axioss.setAxiosPostPromise(
  'sys/dictionary/updateSysDictionaryItem', options)

// 根据ID查询item
export const queryBySysDictionaryItemId = (options) => axioss.setAxiosGetPromise(
  '/sys/dictionary/queryBySysDictionaryItemId', options)

