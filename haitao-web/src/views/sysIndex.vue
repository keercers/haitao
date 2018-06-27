<template>
  <div class="module-div">
    <sideBar style="float:left;"></sideBar>
    <router-view class="content"></router-view>
  </div>
</template>

<script>
  import sideBar from '../components/base/zhhdSideBars.vue'
  export default {
    components: {
      'sideBar': sideBar
    }
    // ,
    // watch: {
    //   '$route': 'savePathToCookie'
    // },
    // created () {
    //   let moudleMes = JSON.parse(this.getcookie('moudleMes'))
    //   let childPath = this.getcookie('childPath' + moudleMes.moudleId)
    //   if (childPath) {
    //     this.$router.push(childPath)
    //   }
    // },
    // methods: {
    //   savePathToCookie () {
    //     let childPath = this.$router.currentRoute.path
    //     let moudleMes = JSON.parse(this.getcookie('moudleMes'))
    //     this.savecookie('childPath' + moudleMes.moudleId, childPath, 10)
    //   }
    // }
  }
</script>

