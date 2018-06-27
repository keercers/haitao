<template>
  <div :style="{height: height+'px'}" class="head">
    <span :style="{lineHeight: height+'px'}" class="title">海淘</span>
    <el-radio-group class="zhhd-top" v-model="zhhdModel" @change="routezhhdModel">
      <el-radio-button class="zhhd-top-but" v-for="item in topList1" :key="item.moudleId" :label="item.moudleName">
      </el-radio-button>
    </el-radio-group>
    <time-bar></time-bar>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import _ from 'lodash'
import timeBar from '../../components/timeBar.vue'

export default {
  data () {
    // update on 2018/1/25
    // 1466 => 1555
    const sw = 1555
    // update on 2018/1/25
    // 1421 => 1225
    const hw = 1225
    const a = -1 / (sw - hw)
    const b = sw * a * (-1)
    return {
      zhhdModel: '基础设置',
      height: window.config.topBarHeight,
      width: 1920,
      startResizeWidth: sw,
      startHideTitleWidth: hw,
      linearTransform: x => a * x + b
    }
  },
  async created () {
    // this.thisHref = window.location.href
    await this.getData()
    await this.routezhhdModel(this.zhhdModel)
    if (this.zhhdModel === '基础设置') {
      this.$router.push('/sys')
    }
  },
  computed: {
    ...mapState({
      topList1: state => state.moudle.topList1
    })
  },
  methods: {
    routezhhdModel (moudleName) {
      console.log('routezhhdModel invoke with ', moudleName)
      console.log('topList1', this.topList1)
      const element = _.find(this.topList1, ['moudleName', moudleName])
      if (element) {
        console.log('routezhhdModel finded element ', element)
        this.savecookie('moudleMes', JSON.stringify({
          moudleId: element.moudleId,
          moudleUrl: element.moudleUrl,
          moudleName: element.moudleName
        }), 1)
        this.savecookie('sysModelId', element.moudleId, 1)
        this.$router.push(element.moudleUrl)
      }
    },
    async getData () {
      return await this.$store.dispatch('queryTopList', '1')
    }
  },
  components: {
    // 时间条
    'timeBar': timeBar
  }
}
</script>

<style>
/* 组件DIV */
.head {
  background: linear-gradient(to right, #1d7fff, #33ccff);
}
/* 全局标题 */
.head>.title {
  font-size: 24px;
  margin-left: 10px;
  color: #ffffff;
}
/* 按钮组DIV */
.zhhd-top {
  margin: -7px 0px 3px 85px;
  overflow: hidden;
}
/* 按钮 */
.zhhd-top-but>.el-radio-button__inner {
  padding: 13px 17px;
}
/* 按钮鼠标悬浮 */
.zhhd-top-but>.el-radio-button__inner:hover {
  transform: translateX(1px) translateY(1px);
}
/* 首个按钮 */
.zhhd-top-but.el-radio-button:first-child .el-radio-button__inner {
  border-left: 0px;
}
/* 被选择顶栏 */
.zhhd-top-but>.el-radio-button__orig-radio:checked+span.el-radio-button__inner {
  /* background-color: #157FF1; */
  background-color: rgba(29, 118, 199, 0.5);
  /* border-color: #157FF1; */
  border-color: rgba(29, 118, 199, 0.5);
  color: #FFFFFF;
  transform: translateX(2px) translateY(2px);
}
/* 未选择顶栏 */
.zhhd-top-but>span.el-radio-button__inner {
  background-color: rgba(255, 255, 255, 0);
  border-color: rgba(255, 255, 255, 0);
  color: #FFFFFF;
}
</style>
