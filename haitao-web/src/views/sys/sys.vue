<template>
  <div>
    <sideBar style="float:left;"></sideBar>
    <router-view class="content" :style="{marginLeft: width}"></router-view>
  </div>
</template>

<script>
import sideBar from '../../components/base/zhhdSideBars.vue'

export default {
  name: 'sys',
  data () {
    return {
      width: window.config.sideBarWidth + 'px'
    }
  },
  components: {
    'sideBar': sideBar
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.content{
  padding:0px 15px;
}
</style>
