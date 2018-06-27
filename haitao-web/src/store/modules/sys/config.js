import api from '../../../service'
import * as types from '../../mutation-types'

// initial state
const state = {
  configPage: {
    configsCount: 0,
    configList: []
  },
  configPagination: {
    pageIndex: 1,
    pageSize: 10,
    confType: '10'
  },
  config: { confKey: '', confDesc: '', remark: '', confValue: '', confType: '', createUser: '', createTime: '' },
  confTypes: []
}

// actions
const actions = {
  async findConfigList ({ commit }) {
    const confParamList = (await api.conf_querySysParamList(state.configPagination)).data
    commit(types.CONF_GET_SEARCH_LIST, confParamList)
  },
  async addConfig ({ commit, dispatch }, config) {
    await api.conf_addConfig(config)
  },
  async delConfig ({ commit, dispatch }, confId) {
    await api.conf_delConfig(confId)
    dispatch('findConfigList')
  },
  async updateConfig ({ commit, dispatch }, config) {
    await api.conf_updateConfig(config)
  },
  async getConfig ({ commit }, confId) {
    const getConfigData = (await api.conf_getConfig(confId)).data
    getConfigData.confType = getConfigData.confType.toString()
    commit(types.CONF_SET_CONFIG, getConfigData)
  },
  async getConfTypes ({ commit }) {
    const getConfTypesData = (await api.conf_getConfTypes()).data
    getConfTypesData.forEach(confType => {
      confType.index = confType.index.toString()
    })
    commit(types.CONF_SET_CONFTYPES, getConfTypesData)
  }
}

// mutations
const mutations = {
  [types.CONF_GET_SEARCH_LIST] (state, data) {
    state.configPage.configList = data.content
    state.configPage.configsCount = parseInt(data.totalElements)
  },
  [types.CONF_SET_CONFIG] (state, data) {
    state.config = data
  },
  [types.CONF_SET_CONFTYPES] (state, data) {
    state.confTypes = data
  }
}

export default {
  state,
  actions,
  mutations
}
