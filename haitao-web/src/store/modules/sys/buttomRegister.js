/**
 * Created by  on 2017/5/25.
 *  @author fuzhi
 */

import api from '../../../service'

// actions
const actions = {
  async buttomRegisterList ({ commit }, options) {
    var data = await api.buttomRegisterList_List(options)
    return data.data
  },
  async delButtomRegister ({ commit }, options) {
    await api.buttomRegister_Del(options)
  },
  async ButtomRegisterOne ({ commit }, options) {
    var data = await api.buttomRegister_One(options)
    return data
  },
  async addButtomRegister ({ commit }, options) {
    await api.buttomRegister_add(options)
  },
  async updataButtomRegister ({ commit }, options) {
    await api.buttomRegister_update(options)
  }
}

export default {
  actions
}
