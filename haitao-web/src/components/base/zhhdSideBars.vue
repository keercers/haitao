<template>
  <div :style="{height: height, width: width}" class="menu-side">
    <el-menu unique-opened :default-active="$route.path" router>
      <template v-for="(item,index) in top_sideTree">
        <el-submenu v-if="item.child.length > 0" :index="index+''" :key="'index-'+index">
          <template slot="title">{{item.name}}</template>
          <el-menu-item :index="itemC.path+''" :key="'indexC-'+indexC" v-for="(itemC,indexC) in item.child">{{itemC.name}}</el-menu-item>
        </el-submenu>
        <el-menu-item v-else :index="item.path+''" :key="'index-'+index">{{item.name}}</el-menu-item>
      </template>
    </el-menu>
  </div>
</template>
<script>
// import topBar from './zhhdTopBar'

export default {
  data () {
    const isie = /msie/i.test(navigator.userAgent) || (navigator.userAgent.toLowerCase().indexOf('trident') !== -1 && navigator.userAgent.indexOf('rv') !== -1)
    return {
      width: window.config.sideBarWidth + 'px',
      heightOri: window.config.topBarHeight,
      top_sideTree: [],
      isIE: isie
    }
  },
  created () {
    console.log('side bar created')
    const ink = setInterval(() => {
      console.log('wait top bar')
      if (this.getcookie('moudleMes')) {
        let moudleMes = JSON.parse(this.getcookie('moudleMes'))
        console.log('getcookie moudleMes ', moudleMes)
        this.getData('0-' + moudleMes.moudleId + '-')
        clearInterval(ink)
      }
    }, 40)
  },
  computed: {
    height () {
      // 直接获取顶栏高度，动态响应
      return document.body.clientHeight - this.heightOri + 'px'
      // 直接获取顶栏高度，动态响应
      // return document.body.clientHeight - topBar.data().height + 'px'
    }
  },
  methods: {
    getData (sign) {
      console.log('side bar -> getData invoked with ', sign)
      this.$store.dispatch('querySideTree', { moudleSign: sign }).then(res => {
        this.top_sideTree = res.data
      })
    }
  }
}
</script>
<style>
/* 主DIV */
.menu-side {
  background: linear-gradient(to bottom, #1d7fff, #33ccff);
  width: 180px;
  font-size: 14px;
}
/* 一级菜单主体 */
.menu-side>ul.el-menu {
  /* background: linear-gradient(to bottom, #1d7fff, #33ccff); */
  background-color: inherit;
}
/* 一级菜单内容 */
.menu-side>ul.el-menu>li.el-menu-item {
  color: #FFFFFF;
}
/* 一级菜单鼠标悬浮 */
.menu-side>ul.el-menu>li.el-menu-item:hover {
  background-color: #60B1FF;
  transform: translateY(4px);
}
/* 一级菜单选中 */
.menu-side>ul.el-menu>li.el-menu-item.is-active {
  background-color: #A0CDFF;
}
/* 二级菜单在一级菜单父项 */
.menu-side>ul.el-menu>li.el-submenu {
  border: 0px;
}
/* 二级菜单主体 */
.menu-side>ul.el-menu>li.el-submenu>ul.el-menu {
  background-color: inherit;
  box-shadow: 0px 0px 0px #d1d4d6 inset;
  border: 0px;
}
/* 二级菜单标题 */
.menu-side>ul.el-menu>li.el-submenu>div.el-submenu__title {
  color: #FFFFFF;
}
/* 二级菜单标题鼠标悬浮 */
.menu-side>ul.el-menu>li.el-submenu>div.el-submenu__title:hover {
  background-color: #60B1FF;
  transform: translateY(4px);
}
/* 二级菜单内容 */
.menu-side>ul.el-menu>li.el-submenu>ul.el-menu>li.el-menu-item {
  color: #FFFFFF;
}
/* 二级菜单内容鼠标悬浮 */
.menu-side>ul.el-menu>li.el-submenu>ul.el-menu>li.el-menu-item:hover {
  background-color: #60B1FF;
  transform: translateY(4px);
}
/* 二级菜单选中 */
.menu-side>ul.el-menu>li.el-submenu>ul.el-menu>li.el-menu-item.is-active {
  background-color: #A0CDFF;
}
</style>

