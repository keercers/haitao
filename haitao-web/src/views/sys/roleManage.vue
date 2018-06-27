<template>
  <div id="rmView" style="padding: 0px 20px 0px 20px;">
    <el-col :span="24" class="toolbar" style="padding: 20px 0px;text-align: left">
      <el-row type="flex">
        <el-col :span="4">
          <el-row>
            <span style="font-size: 18px; line-height: 36px">管理权限</span>
          </el-row>
        </el-col>
        <el-col :span="22">
          <el-row>
            <div style="float: right">
              <el-button type="primary" v-show=type @click="checkeAll">全选</el-button>
              <el-button type="primary" v-show=type @click="resetChecked">清空</el-button>
              <el-button type="primary" v-show=type @click="save">保存</el-button>
              <el-button type="primary" @click="goback">返回</el-button>
            </div>
            <div class="clear"></div>
          </el-row>
        </el-col>
      </el-row>
      <el-row>
        <hr style="color: #c7c5c5;"/>
      </el-row>
      <div :style="{height: tableHeightDynamic + 'px'}" style="background-color: #FFFFFF; margin-top: 10px;">
        <div class="block">
          <el-row>
            <el-col :span="12">
              <div class="grid-content bg-purple tree-content" style="height: 550px">
                <el-tree :data="data2" :show-checkbox='type' node-key="id" ref="tree" highlight-current :props="defaultProps" style="margin:40px 0px; border:none; width:45%; height: 500px"> </el-tree>
          
              </div>
            </el-col>
          </el-row>
        </div>
      </div>
    </el-col>
  </div>
</template>
<script>
  import { mapGetters } from 'vuex'
  
  export default {
    data () {
      return {
        tableHeightDynamic: document.body.clientHeight - window.config.topBarHeight - 160,
        load: true,
        roleId: '',
        type: false,
        defaultProps: {
          children: 'child',
          label: 'name'
        }
      }
    },
    created () {
      this.initRoleMoudle()
    },
    computed: mapGetters({
      data2: 'getRolemoudleTree',
      choices: 'getMoudelIdList'
    }),
    watch: {
      '$route' (to, from) {
        if (to.name === 'roleManage') {
          this.initRoleMoudle()
        }
      },
      'choices': 'CheckChioces'
    },
    methods: {
      initRoleMoudle () {
        this.roleId = this.$route.params.roleId
        this.type = this.$route.params.type
        this.getRoleMoudleTree()
        this.getMoudleIdList()
      },
      checkeAll () {
        let temp = []
        for (let i = 0; i < this.data2.length; i++) {
          temp.push(this.data2[i].id)
        }
        this.$refs.tree.setCheckedKeys(temp)
      },
      resetChecked () {
        this.$refs.tree.setCheckedKeys([])
      },
      CheckChioces () {
        this.$refs.tree.setCheckedKeys(this.choices)
      },
      goback () {
        this.$router.push('/role')
      },
      save () {
        if (this.$refs.tree.getCheckedKeys().length === 0) {
          return this.$store.dispatch('saveRoleMoudel', {roleId: this.roleId, choices: [0]}).then((reply) => {
            this.goback()
            this.$message('保存成功')
          }).catch((err) => {
            this.$message({
              message: '保存失败' + err,
              type: 'error'
            })
          })
        } else {
          return this.$store.dispatch('saveRoleMoudel', {roleId: this.roleId, choices: this.$refs.tree.getCheckedKeys()}).then((reply) => {
            this.goback()
            this.$message({
              message: '保存成功',
              type: 'success'
            })
          }).catch((err) => {
            this.goback()
            this.$message({
              message: '保存失败' + err,
              type: 'error'
            })
          })
        }
      },
      getRoleMoudleTree () {
        this.$store.dispatch('getRoleMoudleTree', {moudleSign: '0-'})
      },
      getMoudleIdList () {
        if (this.roleId) {
          return this.$store.dispatch('findMoudleIdList', {roleId: this.roleId})
        } else {
          this.goback()
        }
      }
    }
  }
</script>

<style>
  #rmView .el-input {
    width: 180px;
  }
  #rmView .el-input__inner {
    width: 180px;
  }
</style>
