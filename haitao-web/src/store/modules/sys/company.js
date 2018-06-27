/**
 * 2017/6/16
 */

import api from '../../../service'
import * as types from '../../mutation-types'

// initial state
const state = {
  companyCount: 0,
  companyList: [],
  companyPagination: {
    pageIndex: 1,
    pageSize: 10,
    comName: '',
    comAddr: ''
  }
}

// actions
const actions = {
  async findCompany ({ commit }, options) {
    return (await api.com_queryCompany(options)).data
  },
  async findAllCompany ({ commit }, options) {
    return (await api.com_findAllCompany(options)).data
  },
  async findCompanyList ({ commit }) {
    const companyListData = (await api.com_queryCompanyList(state.companyPagination)).data
    commit(types.WS_RECEIVE_COMPANY_LIST, companyListData)
    return companyListData
  },
  async addCompany ({dispatch}, options) {
    await api.com_addCompany(options)
    dispatch('findCompanyList')
  },
  async delCompany ({ dispatch }, options) {
    await api.com_delCompany(options)
    dispatch('findCompanyList')
  },
  async updateCompany ({ commit, dispatch }, company) {
    await api.com_updateCompany(company)
  }
}

// mutations
const mutations = {
  [types.WS_RECEIVE_COMPANY_LIST] (state, data) {
    state.companyList = data.content
    state.companyCount = parseInt(data.totalElements)
  }
}

export default {
  state,
  actions,
  mutations
}
