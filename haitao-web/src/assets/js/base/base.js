/* eslint-disable */
import axios from 'axios'

export default {
    install(Vue) {
        const Observer = new (new Vue()).$data.__ob__.constructor({})
        Vue.prototype.getcookie = function(name) {
            var arr = document.cookie
                .match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
            if (arr != null)
                return unescape(arr[2])
            return null;
        }
        Vue.prototype.savecookie = function(name, value, days) {
            var dat = new Date()
            dat.setTime(dat.getTime() + days * 24 * 60 * 60 * 1000)
            document.cookie = name + "=" + escape(value) + ";path=/;expires=" +
                dat.toGMTString()
        }
        Vue.prototype.delCookie = function(name) {
            var exp = new Date()
            exp.setTime(exp.getTime() - 1)
            var cval = getCookie(name)
            if (cval != null)
                document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString()
        }
        Vue.prototype.$axios = axios,
        /**
         * 使VUE不监视某个对象或数组，减少页面载入消耗的CPU时间
         * @param {Object|Array} value - 需要取消监听的值，应当是vue的data中的某个成员，且不需要与页面进行双向绑定
         */
        Vue.prototype.$unwatch = function(value) {
            value.__ob__ = Observer
            return value
        }
    }
}
