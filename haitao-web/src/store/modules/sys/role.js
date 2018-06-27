/**
 * Created by xiaoyu on 2017/6/19.
 */

import api from '../../../service'
import * as types from '../../mutation-types'

// initial state
const state = {
  roleCount: 0,
  roleList: [],
  rolePagination: {
    pageIndex: 1,
    pageSize: 10,
    roleName: '',
    roleType: ''
  },
  roleTypes: []
}

// actions
const actions = {
  async searchAllRole ({ commit }) {
    const roleList = (await api.role_searchAllRole()).data
    commit(types.ROLE_FIND_ALLROLE, roleList)
  },
  async searchRoleList ({ commit }) {
    const roleListData = (await api.role_getRoleList(state.rolePagination)).data
    commit(types.ROLE_SET_SEARCH_LIST, roleListData)
  },
  async addRole ({ dispatch }, options) {
    await api.role_addRole(options)
    dispatch('searchRoleList')
  },
  async delRole ({ dispatch }, roleId) {
    await api.role_delRole(roleId)
    dispatch('searchRoleList')
  },
  async updateRole ({ dispatch }, options) {
    await api.role_updateRole(options)
    dispatch('searchRoleList')
  },
  async getRole (context, roleId) {
    return (await api.role_getRole(roleId)).data
  },
  async getRoleTypes ({ commit }) {
    const roleTypesData = (await api.role_getRoleTypes()).data
    commit(types.ROLE_SET_ROLETYPES, roleTypesData)
  }
}

// mutations
const mutations = {
  [types.ROLE_FIND_ALLROLE] (state, data) {
    state.roleList = data
    state.roleCount = data.length
  },
  [types.ROLE_SET_SEARCH_LIST] (state, data) {
    state.roleList = data.content
    state.roleList.forEach(function (e) {
      if (e.roleType) {
        if (e.roleType.toString() === '0') {
          e.roleType = '全局角色'
        } else if (e.roleType.toString() === '1') {
          e.roleType = '部门角色'
        } else if (e.roleType.toString() === '2') {
          e.roleType = '个人角色'
        } else {
          e.roleType = '其他'
        }
      }
      if (e.isdefault) {
        if (e.isdefault.toString() === '0') {
          e.isdefault = '否'
        } else if (e.isdefault.toString() === '1') {
          e.isdefault = '是'
        } else {
          e.isdefault = '其他'
        }
      }
      let name = ''
      e.userRoleList.forEach(function (roleUser) {
        if (roleUser.userName) {
          name += roleUser.userName + ' '
        }
      })
      e.userName = name
    })
    state.roleCount = parseInt(data.count)
  },
  [types.ROLE_SET_ROLETYPES] (state, data) {
    state.roleTypes = data
  }
}

export default {
  state,
  actions,
  mutations
}
