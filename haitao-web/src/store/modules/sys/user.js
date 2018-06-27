/**
 * 2017/6/16
 */

import api from '../../../service'
import * as types from '../../mutation-types'
import axios from 'axios'

// initial state
const state = {
  userCount: 0,
  userList: [],
  certificateTypes: [],
  eduTypes: [],
  politicalTypes: [],
  sexTypes: []
}

// actions
const actions = {
  async resetPass ({ commit }, options) {
    await api.user_resetPass(options)
  },
  async listAllUser ({ commit }, options) {
    const data = (await api.user_queryAllUser(options)).data
    return data
  },
  async listUserByParams ({ commit }, options) {
    const data = (await api.user_queryUserList(options)).data
    commit(types.USER_SET_SEARCH_LIST, data)
  },
  async addUser ({ dispatch, commit }, user) {
    await api.user_addUser(user)
  },
  async updateUser ({ dispatch, commit }, user) {
    await api.user_updateUser(user)
  },
  async getUser ({ commit }, userId) {
    var user = (await api.user_getUser(userId)).data.user
    user.education = user.education.toString()
    user.sex = user.sex.toString()
    user.politicalStatus = user.politicalStatus.toString()
    user.certificateType = user.certificateType.toString()
    return user
  },
  async delUser ({ dispatch, commit }, userId) {
    await api.user_delUser(userId)
  },
  async getPoliticalTypes ({ commit }, enumname) {
    const data = (await api.user_getPoliticalTypes(enumname)).data
    commit(types.USER_GET_POLITICAL_TYPE, data)
  },
  async getCertificateTypes ({ commit }, enumname) {
    const data = (await api.user_getCertificateTypes(enumname)).data
    commit(types.USER_GET_CERTIFICATE_TYPE, data)
  },
  async getEduTypes ({ commit }, enumname) {
    const data = (await api.user_getEduTypes(enumname)).data
    commit(types.USER_GET_EDU_TYPE, data)
  },
  async getSexTypes ({ commit }, enumname) {
    const data = (await api.user_getSexTypes(enumname)).data
    commit(types.USER_GET_SEX_TYPE, data)
  }
}

// mutations
const mutations = {
  [types.USER_SET_SEARCH_LIST] (state, data) {
    let userList = []
    data.user.forEach(user => {
      let roleName = ''
      user.userRoleList.forEach(role => {
        roleName += role.roleName + ' '
      })
      user.enable = user.enable === 0 ? '不启用' : '启用'
      user.roleName = roleName
      if (user.userImages === axios.defaults.fileLoadUrl + 'null' ||
        user.userImages === axios.defaults.fileLoadUrl) {
        user.userImages = ''
      }
      userList.push(user)
    })
    state.userList = userList
    state.userCount = data.count
  },
  [types.USER_SET_ADD_USER] (state, data) {
    state.userList.push(data)
    state.userCount += 1
  },
  [types.USER_GET_POLITICAL_TYPE] (state, data) {
    state.politicalTypes = data
  },
  [types.USER_GET_CERTIFICATE_TYPE] (state, data) {
    state.certificateTypes = data
  },
  [types.USER_GET_EDU_TYPE] (state, data) {
    state.eduTypes = data
  },
  [types.USER_GET_SEX_TYPE] (state, data) {
    state.sexTypes = data
  }
}

export default {
  state,
  actions,
  mutations
}
