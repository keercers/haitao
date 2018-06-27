
const actions = {
  checkPhone ({commit}, object) {
    if (object.value === '' || object.value === null) {
      object.callback(new Error('输入不能为空！'))
    }
    let reg = /^1[0-9]{10}$/
    if (reg.test(object.value)) {
      object.callback()
    } else {
      object.callback(new Error('手机格式不正确'))
    }
  },
  checkBlank ({commit}, object) {
    if (object.value === '' || object.value === null) {
      object.callback(new Error('输入不能为空！'))
    }
    if (object.value.replace(/(^\s*)|(\s*$)/g, '') === '' && object.value.length !== 0) {
      object.callback(new Error('输入的数据不能为空！'))
    } else {
      object.callback()
    }
  }
}

export default {
  actions
}
