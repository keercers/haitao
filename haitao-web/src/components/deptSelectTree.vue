<template>
  <div>
    <el-input
      placeholder="所属部门"
      @focus="treeCtrl()"
      v-model="selectName">
    </el-input>
    <el-tree
      class="deptTree" 
      :data="treeModel"
      highlight-current
      node-key="id"
      :default-expand-all="true"
      :expand-on-click-node='false'
      :check-strictly='true'
      v-show="treeShow"
      @node-click="nodeClick"
      ref="deptSelTreeRef" style="width:100%;padding:0"></el-tree>
  </div>
</template>

<script>

  import {mapState} from 'vuex'
  export default {
    name: 'deptSelectTree',
    data () {
      return {
        msg: '部门',
        treeFlag: false,
        treeShow: false,
        selectName: ''
      }
    },
    created () {
      this.getTreeData()
      document.addEventListener('click', (e) => {
        if (!this.$el.contains(e.target)) {
          this.treeShow = false
        }
      })
    },
    props: ['selectId'],
    computed: {
      ...mapState({
        treeModel: state => state.department.departmentTreeData
      })
    },
    watch: {
      selectId: function (val) {
        if (val === '-999' || !val) {
          this.selectName = ''
        } else {
          this.$refs.deptSelTreeRef.setCheckedKeys([val])
          const dept = this.$refs.deptSelTreeRef.getCheckedNodes(false)
          this.selectName = dept[0].label
        }
      }
    },
    methods: {
      // 加载部门树
      getTreeData () {
        this.$store.dispatch('getDepartmentTree')
      },
      treeCtrl () {
        this.treeShow = true
      },
      nodeClick (data) {
        this.treeShow = false
        this.selectName = data.label
        this.$emit('nodeSelect', data)
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.el-tree-node__expand-icon {
  border: 8px solid transparent;
  border-left-width: 9px;
  border-right-width: 0;
  border-left-color: #97a8be;
}
.el-tree-node__content {
  cursor: default
}
.deptTree {
  position: absolute;
  z-index: 100;
  height: 200px;
  overflow-x: hidden;
  overflow-y: auto;
  border: 1px solid #ccc;
}
</style>
