<template>
  <div>
    <vi :wndSize="injectSize" ref="child" />
    <el-button v-if="needFull" @click="full()">全屏</el-button>
    <br>
  </div>
</template>

<script>
/* eslint-disable */
import VI from './Video.vue'
export default {
  name: 'ocx',
  components: {
    'vi': VI
  },
  data() {
    return {
      onPlay: false
    }
  },
  props: ['needFull', 'injectSize'],
  methods: {
    full() {
      this.$refs.child.setFullScreenDisplay()
    },
    init() {
      this.$refs.child.setInit()
    },
    play(cameraIpEvt) {
      console.log('cameraIpEvt:' + cameraIpEvt)
      if (!this.$refs.child.isIE) {
        this.init()
      }
      const cameraIp = typeof (cameraIpEvt) === 'object' ? cameraIpEvt.target.id : cameraIpEvt
      this.$refs.child.getLivePlay(cameraIp)
    },
    shut(){
      this.$refs.child.setShut()
    }
  }
}
</script>

<style>

</style>
