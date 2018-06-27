import api from '../../../service'
import * as types from '../../mutation-types'

// initial state
const state = {
  reportData: null,
  oprReportData: {},
  monitorPointList: []
}
// getters
const getters = {
  reportData: state => state.reportData,
  monitorPointList: state => state.monitorPointList,
  oprReportData: state => state.oprReportData
}
// actions
const actions = {
  async getMonitorPointListByMonitorType ({ commit }, options) {
    const result = await api.transport_queryMonitorPointListByMonitorType(options)
    commit(types.MONITOR_POINT_LIST, result.data)
    return await result
  },
  async getReportData ({ commit }, option) {
    const result = await api.monitoring_getReportData(option)
    commit(types.MONITORING_DATA_REPORT_DATA, result.data)
  },
  async getOprReportData ({ commit }, option) {
    const result = await api.operation_getReportData(option)
    commit(types.OPR_DATA_REPORT_DATA, result.data)
  }
}

// mutations
const mutations = {
  [types.MONITOR_POINT_LIST] (state, data) {
    state.monitorPointList = data
  },
  [types.MONITORING_DATA_REPORT_DATA] (state, data) {
    state.reportData = data.reportData
  },
  [types.OPR_DATA_REPORT_DATA] (state, data) {
    state.oprReportData = data.reportData
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
