<template>
  <div>
    <zhhdSideBars style="float:left;"></zhhdSideBars>
    <router-view class="content" :style="{marginLeft: width}"></router-view>
  </div>
</template>

<script>
import zhhdSideBars from '../../components/base/zhhdSideBars.vue'

export default {
  name: 'monitoring',
  data () {
    return {
      width: window.config.sideBarWidth + 'px'
    }
  },
  components: {
    'zhhdSideBars': zhhdSideBars
  }
}
</script>

<style scoped>
.content{
  overflow: auto;
  padding:0px 0px;
}
</style>
