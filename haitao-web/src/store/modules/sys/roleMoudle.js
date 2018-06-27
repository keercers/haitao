import api from '../../../service'
import * as types from '../../mutation-types'

const state = {
  RoleMoudleList: []
}

const getters = {
  getMoudelIdList: state => state.RoleMoudleList
}

const actions = {
  findMoudleIdList ({ commit }, options) {
    api.roleMoudle_getList(options).then(reply => {
      commit(types.ROLEMOUDLE_SET_MOUDLELIST, reply.data)
    })
  },
  saveRoleMoudel ({ commit }, options) {
    return api.saveRoleMoudel(options).then(reply => {
      return reply
    })
  }
}

const mutations = {
  [types.ROLEMOUDLE_SET_MOUDLELIST] (state, data) {
    state.RoleMoudleList = []
    for (var i = 0; i < data.length; i++) {
      state.RoleMoudleList.push(parseInt(data[i].moudelId))
    }
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
