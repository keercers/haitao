/**
 * 2018/1/10
 */

import api from '../../../service'

// actions
const actions = {
  async getEnumType ({ commit }, options) {
    return (await api.enumType_list(options)).data
  }
}

export default {
  actions
}
