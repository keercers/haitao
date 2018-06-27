import api from '../../../service'
import * as types from '../../mutation-types'

const state = {
  logPage: {
    logsCount: 0,
    logList: []
  },
  logTypeInfo: [ {label: '0', value: '错误日志'}, {label: '1', value: '数据新增'}, {label: '2', value: '数据更新'}, {label: '3', value: '数据删除'}, {label: '4', value: '数据查看'}, {label: '5', value: '数据导出'}, {label: '6', value: '系统登录'}, {label: '7', value: '系统退出'}, {label: '8', value: '其他日志'} ],
  systemInfo: [ {label: '1', value: '基础设置'}, {label: '2', value: '综合安全管理'} ],
  logPagination: {
    pageIndex: 1,
    pageSize: 10,
    logType: '',
    system: '',
    userName: ''
  },
  logTypes: []
}

const actions = {
  async getLogList ({ commit }, params) {
    const logParamList = (await api.log_getLogList(params)).data
    logParamList.logList.forEach(log => {
      for (let i = 0; i < state.logTypeInfo.length; i++) {
        if (log.logType === state.logTypeInfo[i].label) {
          log.logType = state.logTypeInfo[i].value
          log.operationDesc = '用户' + log.logType
        }
      }
      for (let i = 0; i < state.systemInfo.length; i++) {
        if (log.system === state.systemInfo[i].label) {
          log.system = state.systemInfo[i].value
        }
      }
    }, this)
    commit(types.LOG_GET_SEARCH_LIST, logParamList)
  },
  async getLogTypes ({ commit }) {
    const getLogTypesData = (await api.log_getLogTypes()).data
    commit(types.LOG_GET_LOGTYPES, getLogTypesData)
  }
}

const mutations = {
  [types.LOG_GET_SEARCH_LIST] (state, data) {
    state.logPage.logList = data.logList
    state.logPage.logsCount = data.count
  },
  [types.LOG_GET_LOGTYPES] (state, data) {
    state.logTypes = data
  }
}

export default {
  state,
  actions,
  mutations
}
