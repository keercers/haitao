import api from 'service'
import * as types from '../mutation-types.js'

// initial state
const state = {
  // 控制左侧sideBar收缩
  sideBarCollapsed: false,
  user: {}
}

// actions
const actions = {
  async loginSystem ({ commit }, options) {
    try {
      const loginData = await api.basicPlatform_login(options)
      commit(types.LOGIN, loginData.data)
    } catch (err) {
      throw err
    }
  },
  async logoutSystem () {
    try {
      const logout = await api.basicPlatform_logout()
      return logout
    } catch (err) {
      throw err
    }
  },
  async updateLogin ({ commit }, options) {
    try {
      const updatePassword = await api.basicPlatform_updatePwd(options)
      return updatePassword
    } catch (err) {
      throw err
    }
  },
  toggleSideBar ({ commit }) {
    commit(types.TOGGLE_SIDEBAR)
  }
}

// mutations同步 修改state
const mutations = {
  [types.LOGIN] (state, data) {
    state.user = data.user
  },
  [types.TOGGLE_SIDEBAR] (state) {
    state.sideBarCollapsed = !state.sideBarCollapsed
  }
}

export default {
  state,
  actions,
  mutations
}
