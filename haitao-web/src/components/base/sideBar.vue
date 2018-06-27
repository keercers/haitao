<template>
  <div class="side-bar" :class="collapsed?'menu-collapsed':'menu-expanded'">
        <el-menu unique-opened :default-active="$route.path" router v-show="!collapsed" @open="changeIcon">
          <template v-for="(item,index) in top_sideTree">
						<el-submenu v-if="item.child.length > 0" :index="index+''" :key="'index-'+index">
							<template slot="title"><img :src="item.icon" >{{item.name}}</template>
              <el-menu-item :index="itemC.path" :key="'indexC-'+indexC" v-for="(itemC,indexC) in item.child">{{itemC.name}}</el-menu-item>
						</el-submenu>
						<el-menu-item  v-else :index="item.path+''" :key="'index-'+index">
              <img :src="item.icon" >{{item.name}}
            </el-menu-item>
					</template>
        </el-menu>

          <!--导航菜单-折叠后-->
				<ul class="el-menu el-menu-vertical-demo collapsed" v-show="collapsed" ref="menuCollapsed">
					<li v-for="(item,index) in top_sideTree"  :key="'index-'+index" class="el-submenu item">
						<template v-if="item.child.length > 0">
							<div class="el-submenu__title" style="padding-left: 20px;" @click="showMenu(index)"><img :src="item.icon" ></div>
							<!--<ul class="el-menu submenu" :class="'submenu-hook-'+index" @mouseover="showMenu(index,true)" @mouseout="showMenu(index,false)"> -->
							<!--<div id="demo11" :class="'submenu-hook-'+index"  @mouseover="showMenu(index,true)" @mouseout="showMenu(index,false)"></div>-->
              <ul class="el-menu submenu" :class="'submenu-hook-'+index" > 
								<li :index="itemC.path" :key="'indexC-'+indexC" v-for="(itemC,indexC) in item.child" class="el-menu-item" style="padding-left: 40px;"
                 :class="$route.path==itemC.path?'is-active':''" 
                 @click="hideMenu(index,itemC.path)">{{itemC.name}}</li>
							</ul>
						</template>
						<template v-else>
					<ul>
							<li class="el-submenu">
								<div class="el-submenu__title el-menu-item" style="padding-left: 20px;height: 56px;line-height: 56px;padding: 0 20px;" 
                :class="$route.path==item.path?'is-active':''"
                 @click="$router.push(item.path)">
                 <img :src="item.icon" >
                </div>
							</li>
					</ul>
					</template>
					</li>
				</ul>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  export default {
    created () {
      let moudleMes = JSON.parse(this.getcookie('moudleMes'))
      this.getData('1-' + moudleMes.moudleId + '-')
    },
    computed: {
      ...mapState({
        top_sideTree: state => state.moudle.sideTree,
        collapsed: state => state.login.sideBarCollapsed
      })
    },
    methods: {
      getData (sign) {
        return this.$store.dispatch('querySideTree', {moudleSign: sign})
      },
      showMenu (i) {
        for (let n = 0; n < this.top_sideTree.length; n++) {
          this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-' + n)[0].style.display = 'none'
        }
        this.changeIcon(i)
        this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-' + i)[0].style.display = 'block'
      },
      changeIcon (i) {
        for (let n = 0; n < this.top_sideTree.length; n++) {
          this.top_sideTree[n].icon = this.top_sideTree[n].icon.split('.')[0] + '.' + this.top_sideTree[n].icon.split('.')[1]
        }
        this.top_sideTree[i].icon += '.png'
      },
      hideMenu (i, path) {
        this.$router.push(path)
        this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-' + i)[0].style.display = 'none'
      }
    }
  }
</script>

