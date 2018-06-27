import api from '../../../service'
import * as types from '../../mutation-types'

// initial state
const state = {
  dictionaryGroupData: [],
  dictionaryItemData: [],
  dictionaryItemCount: 0,
  dictGroupId: null,
  dictionaryTargetItem: ''
}

// getters
const getters = {
  dictionaryGroupList: state => state.dictionaryGroupData,
  dictionaryItemList: state => state.dictionaryItemData,
  dictionaryItemNum: state => state.dictionaryItemCount
}

// actions
const actions = {
  async getDictionaryGroupList ({ commit }) {
    const result = await api.dictionary_getDictionaryGroupList()
    commit(types.DICTIONARY_GROUP_LIST, result.data)
  },
  getDictionaryItemList ({ commit }, options) {
    return api.dictionary_getDictionaryItemList(options).then(reply => {
      commit(types.DICTIONARY_ITEM_LIST, reply.data)
      return reply
    })
  },
  addDictionaryItem ({ commit }, options) {
    api.dictionary_addDictionaryItem(options).then(reply => {
      commit(types.DICTIONARY_SET_ADD_ITEM, reply.data)
    })
  },
  updateDictionaryItem ({ commit }, options) {
    api.dictionary_updateDictionaryItem(options).then(reply => {
      commit(types.DICTIONARY_SET_UPDATE_ITEM, reply.data)
    })
  },
  queryByItemId ({ commit }, options) {
    return api.dictionary_queryByItemId(options).then(reply => {
      commit(types.DICTIONARY_ITEM_BY_ID, reply.data)
      return reply
    })
  }
}

// mutations
const mutations = {
  [types.DICTIONARY_GROUP_LIST] (state, data) {
    state.dictionaryGroupData = data
  },
  [types.DICTIONARY_ITEM_LIST] (state, data) {
    state.dictionaryItemData = data.content
    state.dictionaryItemCount = parseInt(data.totalElements)
  },
  // [types.DICTIONARY_ITEM_LIST] (state, data) {
  //   const list = state.dictionaryItemData
  //   const listIndex = list.findIndex((pos) => pos.id === data.id)
  //   if (listIndex) {
  //     list.splice(listIndex, 1, data)
  //   }
  //   state.dictionaryItemData = list
  //   state.dictionaryItemCount = parseInt(data.totalElements)
  // },
  [types.DICTIONARY_SET_ADD_ITEM] (state, data) {
    state.dictionaryItemData.push(data)
    state.dictionaryItemCount++
  },
  [types.DICTIONARY_SET_UPDATE_ITEM] (state, data) {
    const posList = state.dictionaryItemData
    const posIndex = posList.findIndex((pos) => pos.id === data.id)
    if (posIndex) {
      posList.splice(posIndex, 1, data)
    }
    state.dictionaryItemData = posList
  },
  [types.DICTIONARY_ITEM_BY_ID] (state, data) {
    state.dictionaryTargetItem = data
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
