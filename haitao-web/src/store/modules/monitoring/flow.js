import api from '../../../service'
import * as types from '../../mutation-types'

// initial state
const state = {
  flowList: [],
  shipTypeList: []
}
// getters
const getters = {
  flowList: state => state.flowList,
  shipTypeList: state => state.shipTypeList
}
// actions
const actions = {
  async queryFlow ({ commit }, option) {
    const result = await api.monitoring_queryFlow(option)
    commit(types.MONITORING_DATA_FLOW_LIST, result.data)
  },
  async queryFlowByShipType ({ commit }, option) {
    const result = await api.monitoring_queryFlowByShipType(option)
    commit(types.MONITORING_DATA_FLOW_TYPE_LIST, result.data)
  }
}

// mutations
const mutations = {
  [types.MONITORING_DATA_FLOW_LIST] (state, data) {
    state.flowList = data.list
  },
  [types.MONITORING_DATA_FLOW_TYPE_LIST] (state, data) {
    state.shipTypeList = data.list
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
