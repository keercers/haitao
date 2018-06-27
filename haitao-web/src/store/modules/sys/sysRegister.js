/**
 * Created by  on 2017/5/25.
 *  @author fuzhi
 */

import api from '../../../service'

const actions = {
  async sysRegisterList ({ commit }, options) {
    var data = await api.sysRegisterList_List(options)
    return data.data
  },
  async delSysRegister ({ commit }, options) {
    await api.sysRegister_Del(options)
  },
  async sysRegisterOne ({ commit }, options) {
    var data = await api.sysRegister_One(options)
    return data
  },
  async addSysRegister ({ commit }, options) {
    await api.sysRegister_add(options)
  },
  async updataSysRegister ({ commit }, options) {
    await api.sysRegister_update(options)
  }
}

export default {
  actions
}
