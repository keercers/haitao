import api from '../../../service'
import * as types from '../../mutation-types'

// initial state
const state = {
  tonnageList: []
}
// getters
const getters = {
  tonnageList: state => state.tonnageList
}
// actions
const actions = {
  async queryTonnageStatisticData ({ commit }, option) {
    const result = await api.monitoring_queryTonnageStatisticData(option)
    commit(types.MONITORING_DATA_TONNAGE_LIST, result.data)
  }
}

// mutations
const mutations = {
  [types.MONITORING_DATA_TONNAGE_LIST] (state, data) {
    state.tonnageList = data.list
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
