/* eslint-disable */
export default {
  install(Vue, options) {
    Vue.prototype.copyProperties = (target, properties) => {
      const src = {}
      if (properties) {
        for (const p of properties) {
          src[p] = target[p]
        }
      } else {
        for (const prop in target) {
          if (target.hasOwnProperty(prop)) {
            src[prop] = target[prop]
          }
        }
      }
      return src
    }
    Vue.prototype.copyWithIngorePro = (src, target, ignoreProperties = []) => {
      for (const p in target) {
        if (target.hasOwnProperty(p) && ignoreProperties.indexOf(p) < 0 && src[p]) {
          target[p] = src[p]
        }
      }
      return target
    }
    Vue.prototype.clearProperties = (target) => {
      if (typeof target !== 'object') {
        return
      }
      for (const prop in target) {
        if (target.hasOwnProperty(prop)) {
          if (target[prop] instanceof Array) {
            target[prop] = []
          } else if (target[prop] instanceof Object) {
            target[prop] = {}
          } else {
            target[prop] = ''
          }
        }
      }
    }
  },
  compareProperties: properties => {
    return (a, b) => {
      var value1 = a[properties]
      var value2 = b[properties]
      return value1 - value2
    }
  },
  compareDeep: (x, y) => {
    // If both x and y are null or undefined and exactly the same 
    if (x === y) {
      return true
    }
    // If they are not strictly equal, they both need to be Objects 
    if (!(x instanceof Object) || !(y instanceof Object)) {
      return false
    }
    //They must have the exact same prototype chain,the closest we can do is
    //test the constructor. 
    if (x.constructor !== y.constructor) {
      return false
    }
    for (var p in x) {
      //Inherited properties were tested using x.constructor === y.constructor
      if (x.hasOwnProperty(p)) {
        // Allows comparing x[ p ] and y[ p ] when set to undefined 
        if (!y.hasOwnProperty(p)) {
          return false
        }
        // If they have the same strict value or identity then they are equal 
        if (x[p] === y[p]) {
          continue
        }
        // Numbers, Strings, Functions, Booleans must be strictly equal 
        if (typeof (x[p]) !== "object") {
          return false
        }
        // Objects and Arrays must be tested recursively 
        if (!Object.equals(x[p], y[p])) {
          return false
        }
      }
    }
    for (p in y) {
      // allows x[ p ] to be set to undefined 
      if (y.hasOwnProperty(p) && !x.hasOwnProperty(p)) {
        return false
      }
    }
    return true
  },
  guid: () => {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, c => {
      var r = Math.random() * 16 | 0,
        v = c == 'x' ? r : (r & 0x3 | 0x8)
      return v.toString(16)
    })
  },
  DateToStandardString: DateObj => {
    if (typeof (DateObj) !== 'object') {
      console.warn('DateToStandardString')
      return false
    }
    const year = DateObj.getFullYear()
    const month = DateObj.getMonth() < 10 ? '0' + (DateObj.getMonth() + 1) : (DateObj.getMonth() + 1)
    const day = DateObj.getDate() < 10 ? '0' + DateObj.getDate() : DateObj.getDate()
    return year + '-' + month + '-' + day + 'T00:00:00.000Z'
  },
  DateTimeToString: (DateObj, showSEC) => {
    if (typeof (DateObj) !== 'object') {
      console.warn('DateToString')
      return false
    }
    const year = DateObj.getFullYear()
    const month = DateObj.getMonth() < 10 ? '0' + (DateObj.getMonth() + 1) : (DateObj.getMonth() + 1)
    const day = DateObj.getDate() < 10 ? '0' + DateObj.getDate() : DateObj.getDate()
    const hour = DateObj.getHours() < 10 ? '0' + DateObj.getHours() : DateObj.getHours()
    const minute = DateObj.getMinutes() < 10 ? '0' + DateObj.getMinutes() : DateObj.getMinutes()
    const second = showSEC ? DateObj.getSeconds() < 10 ? ':0' + DateObj.getSeconds() : ':' + DateObj.getSeconds() : ''
    return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + second
  },
  DateToString: DateObj => {
    if (typeof (DateObj) !== 'object') {
      console.warn('DateToString')
      return false
    }
    const year = DateObj.getFullYear()
    const month = DateObj.getMonth() < 10 ? '0' + (DateObj.getMonth() + 1) : (DateObj.getMonth() + 1)
    const day = DateObj.getDate() < 10 ? '0' + DateObj.getDate() : DateObj.getDate()
    return year + '-' + month + '-' + day
  },
  // 验证是否是数字 NAN
  validateNAN: function (targetParam, warnMessage, VueInst, successMessage) {
    if (isNaN(targetParam)) {
      VueInst.$message({
        message: warnMessage,
        type: 'error'
      })
      return false
    } else {
      if (successMessage) {
        VueInst.$message({
          message: successMessage,
          type: 'success'
        })
      }
      return true
    }
  },
  // 验证类型是否匹配
  validateType: function (targetParam, promiseType, warnMessage, VueInst, successMessage) {
    if (typeof (targetParam) !== promiseType) {
      VueInst.$message({
        message: warnMessage,
        type: 'error'
      })
      return false
    } else {
      if (successMessage) {
        VueInst.$message({
          message: successMessage,
          type: 'success'
        })
      }
      return true
    }
  },
  // 验证是否是空
  validateBlank: function (targetParam, warnMessage, VueInst, successMessage) {
    if (!warnMessage) {
      return !(targetParam === '' || targetParam === null)
    } else {
      if (targetParam === '' || targetParam === null) {
        VueInst.$message({
          message: warnMessage,
          type: 'error'
        })
        return false
      } else {
        if (successMessage) {
          VueInst.$message({
            message: successMessage,
            type: 'success'
          })
        }
        return true
      }
    }
  },
  // 验证IP地址正确性
  validateIP(ip, warnMessage, VueInst, successMessage) {
    var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
    if (!warnMessage) {
      return reg.test(ip)
    } else {
      if (!reg.test(ip)) {
        VueInst.$message({
          message: warnMessage,
          type: 'error'
        })
        return false
      } else {
        if (successMessage) {
          VueInst.$message({
            message: successMessage,
            type: 'success'
          })
        }
        return true
      }
    }
  },
  // 验证端口正确性
  validatePort(port, warnMessage, VueInst, successMessage) {
    const result = _.isInteger(port) && port > 0 && port < 65536
    if (!result) {
      VueInst.$message({
        message: warnMessage,
        type: 'error'
      })
      return false
    } else {
      if (successMessage) {
        VueInst.$message({
          message: successMessage,
          type: 'success'
        })
      }
      return true
    }
  }
}
