<template>
  <div id="roleView" style="padding: 0px 20px 0px 20px;">
    <el-col :span="24" class="toolbar" style="padding: 20px 0px;text-align: left">
      <el-row type="flex">
        <el-col :span="2">
          <el-row>
            <span style="font-size: 18px; line-height: 36px">角色</span>
          </el-row>
        </el-col>
        <el-col :span="22">
          <el-row>
            <div style="float: right">
              <span>角色名称</span>
              <el-input v-model="pagination.roleName" placeholder="角色名称" style="width: 180px;margin-left: 5px"></el-input>
              <span>角色类型</span>
              <el-select style="width: 180px" v-model="pagination.roleType" clearable placeholder="请选择">
                <el-option v-for="item in roleTypes" :key="item.index" :label="item.name" :value="item.index">
                </el-option>
              </el-select>
              <el-button type="primary" @click="getData" :loading="loading" icon="search">查询</el-button>
              <el-button type="primary" @click="handleAdd()" icon="plus">新增</el-button>
            </div>
            <div class="clear"></div>
          </el-row>
        </el-col>
      </el-row>
      <div style="margin-top: 10px;">
        <el-table :data="units" border stripe :height="tableHeightDynamic">
          <el-table-column type="index" label="序号" width="100"></el-table-column>
          <el-table-column prop="roleName" label="角色名称"></el-table-column>
          <el-table-column prop="roleType" label="角色类型"></el-table-column>
          <el-table-column prop="userName" label="人员"></el-table-column>
          <el-table-column label="操作" width="350">
            <template slot-scope="scope">
              <el-button type="text" @click="roleManage(scope.row, true)">管理权限</el-button>
              <el-button type="text" @click="handleEdit(scope.row)">编辑</el-button>
              <el-button type="text" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="float: left">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentpage"
          :page-sizes="pagesizes"
          :page-size="pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagesCount">
        </el-pagination>
      </div>
    </el-col>

    <el-dialog id="myEd" :title="roleForm.title" :visible.sync="roleForm.dialogFormVisible">
      <el-form label-position="right" :inline="true" :model="roleForm.role" :rules="roleForm.rules" ref="roleForm">
        <el-row type="flex" justify="center">
          <el-col :span="12" :push="1">
            <el-form-item label="角色名称" prop="roleName">
              <el-input v-model="roleForm.role.roleName" placeholder="角色名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" :pull="1">
            <el-form-item label="角色类型">
              <el-select v-model="roleForm.role.roleType" clearable placeholder="请选择">
                <el-option v-for="item in roleTypes" :key="item.index" :label="item.name" :value="item.index">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" justify="start">
          <el-col :span="12" :push="1">
            <el-form-item label="备注信息">
              <el-input v-model="roleForm.role.remark" placeholder="备注信息"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" justify="center">
          <el-col :span="24">
          <template>
            <el-transfer
              ref="rtt"
              v-model="roleForm.userSelect"
              filterable
              :titles="['可分配人员','已分配人员']"
              :footer-format="{
                  noChecked: '已选:0/${total}',
                  hasChecked: '已选:${checked}/${total}'
                }"
              :data="roleForm.userList"></el-transfer>
          </template>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm('roleForm')">确 定</el-button>
        <el-button type="primary" @click="roleForm.dialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog size="tiny" title="删除" :visible.sync="dialogDelFormVisible" class="del-dialog">
      <span>确定删除角色：{{roleDelName}}吗？</span>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="delRole()">确 定</el-button>
        <el-button type="primary" @click="dialogDelFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  /*eslint-disable*/
  import {mapState} from 'vuex'

  export default {
    name: 'role',
    data () {
      return {
        tableHeightDynamic: document.body.clientHeight - config.topBarHeight - 170,
        msg: '角色',
        pagesizes: [20, 30, 50], // 条数列
        pagesize: 20, // 行数
        currentpage: 1,
        loading: false,
        roleName: '',
        roleType: '',
        roleTypes: null,
        roleForm: {
          title: '',
          textareaRows: 5,
          role: {
            roleId: '',
            roleName: '',
            roleType: '',
            userRoleList: [],
            remark: ''
          },
          rules: {
            roleName: [{required: true, message: '角色名称不能为空', trigger: 'blur'}]
          },
          userList: [],
          userSelect: [],
          dialogFormVisible: false
        },
        dialogDelFormVisible: false,
        roleDelName: '',
        roleDelId: ''
      }
    },
    created () {
      this.getData()
      this.initRoleTypes()
      this.initAllUser()
    },
    computed: {
      ...mapState({
        units: state => state.role.roleList,
        pagesCount: state => state.role.roleCount,
        pagination: state => state.role.rolePagination
      })
    },
    methods: {
      handleSizeChange (val) {
        this.pagesize = val
        this.pagination.pageSize = val
        this.getData()
      },
      handleCurrentChange (val) {
        this.currentpage = val
        this.pagination.pageIndex = val
        this.getData()
      },
      getData () {
        this.pagination.pageSize = this.pagesize
        this.pagination.pageIndex = this.currentpage
        return this.$store.dispatch('searchRoleList')
      },
      initRoleTypes () {
        return this.$store.dispatch('getEnumType', {enumName: 'RoleTypeEnum'}).then((res) => {
          this.roleTypes = res
        })
      },
      initRole (roleId) {
        return this.$store.dispatch('getRole', {roleId: roleId}).then((res) => {
          this.copyWithIngorePro(res, this.roleForm.role, 'userRoleList')
          this.roleForm.userSelect = []
          for (const user of res.userRoleList) {
            this.roleForm.userSelect.push(user.userId)
          }
        }).catch(_ => {
        })
      },
      initAllUser () {
        return this.$store.dispatch('listAllUser').then((res) => {
          for (const user of res.user) {
            let item = {}
            item.key = user.userId
            item.label = user.userName + '-' + user.deptName
            this.roleForm.userList.push(item)
          }
        }).catch(_ => {
        })
      },
      async handleEdit (row) {
        // this.$router.push({name: 'roleAdd', query: { roleId: row.roleId, id: row.id }})
        if (this.$refs['roleForm']) {
          this.$refs['roleForm'].resetFields()
        }
        this.roleForm.title = '编辑角色'
        await this.initRole(row.roleId)
        this.roleForm.role.roleId = row.roleId
        this.roleForm.dialogFormVisible = true
        setTimeout(() => {
          this.$refs.rtt.$children[0].query = ''
          this.$refs.rtt.$children[3].query = ''
          this.$refs.rtt.$children[0].checked.splice(0, this.$refs.rtt.$children[0].checked.length)
        }, 100)
      },
      handleAdd () {
        this.roleForm.title = '新增角色'
        this.clearProperties(this.roleForm.role)
        this.flush()
        this.roleForm.dialogFormVisible = true
        this.roleForm.userSelect = []
        if (this.$refs['roleForm']) {
          this.$refs['roleForm'].resetFields()
        }
        setTimeout(() => {
          this.$refs.rtt.$children[0].query = ''
          this.$refs.rtt.$children[3].query = ''
          this.$refs.rtt.$children[0].checked.splice(0, this.$refs.rtt.$children[0].checked.length)
        }, 100)
      },
      submitForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.saveRole()
          } else {
            this.$message({
              message: '输入数据不正确！',
              type: 'warning'
            })
            return false
          }
        })
      },
      flush() {
        this.roleForm.role = {
          roleId: '',
          roleName: '',
          roleType: '',
          userRoleList: [],
          remark: ''
        }
      },
      saveRole () {
        if (!this.roleForm.role.roleName.trim()) {
          this.$message({
            message: '角色名称不能只为空格',
            type: 'info'
          })
          this.roleForm.role.roleName = ''
          return
        }
        this.roleForm.role.userRoleList = []
        for (const item of this.roleForm.userSelect) {
          let temp = {}
          temp.userId = item
          this.roleForm.role.userRoleList.push(temp)
        }
        if (this.roleForm.role.roleId) {
          let temp = JSON.stringify(this.roleForm.role)
          let editRole = JSON.parse(temp)
          this.$refs['roleForm'].resetFields()
          return this.$store.dispatch('updateRole', editRole).then(() => {
            this.$message({
              message: '编辑角色成功',
              type: 'success'
            })
            this.$refs['roleForm'].resetFields()
            this.roleForm.dialogFormVisible = false
          }).catch(_ => {
          })
        } else {
          let temp = JSON.stringify(this.roleForm.role)
          let addRole = JSON.parse(temp)
          this.$refs['roleForm'].resetFields()
          delete addRole.roleId
          return this.$store.dispatch('addRole', addRole).then(() => {
            this.$message({
              message: '新增角色成功',
              type: 'success'
            })
            this.roleForm.dialogFormVisible = false
          }).catch(_ => {
          })
        }
      },
      handleDelete (row) {
        this.dialogDelFormVisible = true
        this.roleDelId = row.roleId
        this.roleDelName = row.roleName
      },
      delRole () {
        return this.$store.dispatch('delRole', {roleId: this.roleDelId}).then(() => {
          this.$message({
            message: '删除角色成功',
            type: 'success'
          })
          this.dialogDelFormVisible = false
          this.getData()
        })
      },
      roleManage (row, type) {
        this.$router.push({name: 'roleManage', params: {roleId: row.roleId, type: type}})
      }
    }
  }
</script>

<style>
  #roleView .el-input {
    width: 180px;
  }
  #roleView .el-input__inner {
    width: 180px;
  }
</style>
<style scoped>
  .clear {
    clear: both;
    height: 0;
    line-height: 0;
    font-size: 0
  }
</style>
