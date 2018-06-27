/**
 * Created by  on 2017/5/25.
 *  @author fuzhi
 */

import api from '../../../service'
import * as types from '../../mutation-types'

// initial state
const state = {
  moudels: [],
  moudelList: [],
  moudelCount: 0
}

// actions
const actions = {
  async menuList ({ commit }, options) {
    await api.menu_All().then(reply => {
      commit(types.MOUDLE_ALL, reply.data)
      api.menu_List(options).then(reply => {
        commit(types.MOUDLE_LIST, reply.data)
      })
    })
  },
  async menuDel ({ commit }, options) {
    await api.menu_Del(options)
  },
  async menuAll ({ commit }, options) {
    var moudels = await api.menu_All(options)
    return moudels
  },
  async menuOne ({ commit }, options) {
    var moudel = await api.menu_One(options)
    return moudel
  },
  async menuAdd ({ commit }, options) {
    await api.menu_Add(options)
  },
  async menuUpdate ({ commit }, options) {
    await api.menu_Update(options)
  }
}

// mutations
const mutations = {
  [types.MOUDLE_ALL] (state, data) {
    state.moudels = data
  },
  [types.MOUDLE_LIST] (state, data) {
    state.moudelList = data.content
    for (var index in state.moudelList) {
      var sys = state.moudelList[index].moudleSign
      if (sys.indexOf('0-1-') > -1) {
        state.moudelList[index].sys = '基础设置'
      } else if (sys.indexOf('0-2-') > -1) {
        state.moudelList[index].sys = '综合安全管理'
      } else {
        state.moudelList[index].sys = '其他'
      }
      var parentId = state.moudelList[index].moudleParentId
      for (var jndex in state.moudels) {
        var moudleId = state.moudels[jndex].moudleId
        if (moudleId === parentId) {
          state.moudelList[index].moudleParentName = state.moudels[jndex].moudleName
        }
      }
    }
    state.moudelCount = data.totalElements
  }
}

export default {
  state,
  actions,
  mutations
}
