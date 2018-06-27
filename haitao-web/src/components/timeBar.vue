<script>
/* eslint-disable */
import util from '../util/util'

export default {
  name: 'time-bar',
  render(h) {
    if (this.width > 1700)
      return h('span', { staticClass: 'time-bar', style:({ height: this.height + 'px', lineHeight: this.height + 'px' }) }, [this.updateTime])
  },
  data() {
    return {
      time: this.$unwatch(new Date()),
      ins: null,
      width: null,
      logoutCD: window.config.logoutCountDown * 2,
      height: window.config.topBarHeight
    }
  },
  created() {
    window.onmousemove = _.debounce(_ => {
      this.logoutCD = window.config.logoutCountDown * 2
    }, 1000)
    this.ins = setInterval(() => {
      this.time = new Date()
      this.width = document.body.clientWidth
      this.logoutCD -= 1
    }, 500)
  },
  destroyed() {
    clearInterval(this.ins)
    window.onmousemove = null
  },
  computed: {
    updateTime() {
      return util.DateTimeToString(this.time, true)
    }
  },
  watch: {
    logoutCD(v) {
      if (v < 0) {
        this.$message({
          type: 'warning',
          message: window.config.logoutCountDown / 60 + '分钟未操作，请重新登陆'
        })
        this.$store.dispatch('logoutSystem')
          .then((res) => {
            this.savecookie('userName', '')
            this.$router.push('/')
          })
        console.log('System Logout via timeBar.vue')
      }
    }
  }
}
</script>
