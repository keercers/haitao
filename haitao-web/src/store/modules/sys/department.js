import api from 'service'
import * as types from '../../mutation-types'

// initial state
const state = {
  departmentTreeData: []
}

// actions
const actions = {
  async getDepartmentTree ({ commit }) {
    const treeData = await api.dep_getDepartmentTree()
    commit(types.SET_DEPARTMENT_TREE_DATA, treeData.data)
  },
  async addDepartment ({ dispatch }, data) {
    await api.dep_addDepartment(data)
    dispatch('getDepartmentTree')
  },
  async initDepartment ({dispatch}, id) {
    const dept = await api.dep_initDepartment(id)
    return dept.data
  },
  async updateDepartment ({ dispatch }, data) {
    await api.dep_updateDepartment(data)
    dispatch('getDepartmentTree')
  },
  async deleteDepartment ({ dispatch }, id) {
    const delInfo = await api.dep_deleteDepartment(id)
    dispatch('getDepartmentTree')
    return delInfo
  }
}

// mutations
const mutations = {
  [types.SET_DEPARTMENT_TREE_DATA] (state, data) {
    state.departmentTreeData = data
  }
}

export default {
  state,
  actions,
  mutations
}
