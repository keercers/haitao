/**
 * Created by Ye on 2017/5/26.
 */
import api from '../../../service'
import * as types from '../../mutation-types'

// initial state
const state = {
  positionPage: {
    positionsCount: 0,
    positionList: []
  },
  positionPagination: {
    pageIndex: 1,
    pageSize: 10,
    posName: '',
    posType: ''
  },
  positionTypes: []
}

// actions
const actions = {
  async queryAllPosition ({
    commit
  }) {
    const data = (await api.pos_queryAllPosition()).data
    commit(types.POS_GET_ALL_POSITION, data)
  },
  async searchPositionList ({
    commit
  }) {
    const positionListData = (await api.pos_getPositionList(state.positionPagination)).data
    positionListData.position.forEach(position => {
      if (position.posType === '0') {
        position.posType = '全局岗位'
      } else if (position.posType === '1') {
        position.posType = '部门岗位'
      } else if (position.posType === '2') {
        position.posType = '个人岗位'
      } else if (position.posType === '9') {
        position.posType = '其他'
      }
    }, this)
    commit(types.POS_GET_SEARCH_LIST, positionListData)
  },
  async addPosition ({
    dispatch
  }, position) {
    await api.pos_addPosition(position)
    dispatch('searchPositionList')
  },
  async delPosition ({
    dispatch
  }, posId) {
    await api.pos_delPosition(posId)
    dispatch('searchPositionList')
  },
  async updatePosition ({
    dispatch
  }, position) {
    await api.pos_updatePosition(position)
    dispatch('searchPositionList')
  },
  async getPosition (context, posId) {
    return (await api.pos_getPosition(posId)).data
  },
  async getPositionTypes ({
    commit
  }) {
    const positionTypesData = (await api.pos_getPositionTypes()).data
    commit(types.POS_SET_POSITIONTYPES, positionTypesData)
  }
}

// mutations
const mutations = {
  [types.POS_GET_ALL_POSITION] (state, data) {
    state.positionPage.positionList = data
    state.positionPage.positionsCount = parseInt(data.length)
  },
  [types.POS_GET_SEARCH_LIST] (state, data) {
    state.positionPage.positionList = data.position
    state.positionPage.positionsCount = parseInt(data.count)
  },
  [types.POS_SET_POSITIONTYPES] (state, data) {
    state.positionTypes = data
  }
}

export default {
  state,
  actions,
  mutations
}
