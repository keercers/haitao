/**
 * Created by  on 2017/5/25.
 *  @author wangxu
 */

import api from '../../../service'
import * as types from '../../mutation-types'

// initial state
const state = {
  topList1: [],
  sideTree: [],
  rolemoudleTree: []
}

// getters
const getters = {
  getTopList1: state => state.topList1,
  getSideTree: state => state.sideTree,
  getRolemoudleTree: state => state.rolemoudleTree
}

// actions
const actions = {
  async queryTopList ({ commit }, options) {
    const list = await api.moudle_queryTopList({ moudleLevel: options })
    // console.log(list.data)
    commit(types.GET_MOUDLE_DATA_LEVEL_1, list.data)
  },
  async querySideTree ({ commit }, options) {
    const sideTree = await api.moudle_querySideTree(options)
    commit(types.GET_MOUDLE_DATA_BY_PARENT, sideTree.data)
    return await sideTree
  },
  getRoleMoudleTree ({ commit }, options) {
    api.moudle_querySideTree(options).then(reply => {
      // console.log('actions-moudle_querySideTree:' + reply.data[0].name)
      commit(types.GET_ROLEMOUDLE_TREE, reply.data)
    })
  },
  getForbid () {
    api.getForbid().then(reply => {
      localStorage.setItem('forbidList', reply.data.toString())
    })
  }
}

// mutations
const mutations = {
  [types.GET_MOUDLE_DATA_LEVEL_1] (state, data) {
    state.topList1 = data
  },
  [types.GET_MOUDLE_DATA_BY_PARENT] (state, data) {
    state.sideTree = data
  },
  [types.GET_ROLEMOUDLE_TREE] (state, data) {
    state.rolemoudleTree = data
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
