<template>
  <div id="ptView" style="padding: 0px 20px 0px 20px;">
    <el-col :span="24" class="toolbar" style="padding: 20px 0px;text-align: left">
      <el-row type="flex">
        <el-col :span="4">
          <el-row>
            <span style="font-size: 18px; line-height: 36px">岗位列表</span>
          </el-row>
        </el-col>
        <el-col :span="20">
          <el-row>
            <div style="float: right">
              <span>岗位名称</span>
              <el-input v-model="pagination.posName" placeholder="岗位名称" style="width: 180px;margin-left: 5px"></el-input>
              <span>岗位类型</span>
              <el-select v-model="pagination.posType" clearable placeholder="全部" style="width: 180px;margin-left: 5px">
                  <el-option v-for="item in positionTypes" :key="item.index" :label="item.name" :value="item.index">
                  </el-option>
              </el-select>
              <el-button type="primary" @click="getData" :loading="loading" icon="search">查询</el-button>
              <el-button type="primary" @click="handleAdd('positionForm')" icon="plus">新增</el-button>
            </div>
            <div class="clear"></div>
          </el-row>
        </el-col>
      </el-row>
      <div class="block" style="margin-top: 10px;">
        <el-table :data="tableData" border stripe :height="tableHeightDynamic">
          <el-table-column type="index" label="序号" width="100"></el-table-column>
          <el-table-column prop="posSort" label="排序号"></el-table-column>
          <el-table-column prop="posName" label="岗位名称"></el-table-column>
          <el-table-column prop="posType" label="岗位类型"></el-table-column>
          <el-table-column prop="deptName" label="部门"></el-table-column>
          <el-table-column prop="posLevel" label="职级"></el-table-column>
          <el-table-column label="操作" width="180">
              <template slot-scope="scope">
                  <el-button class="table-btn" type="text" size="small" @click="handleEdit(scope.row)">编辑</el-button>
                  <el-button class="table-btn" type="text" size="small" @click="handleDelete(scope.row)">删除</el-button>
              </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="block">
          <el-pagination
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :page-sizes="pageSizes"
                  :page-size="pageSize"
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="pagesCount">
          </el-pagination>
      </div>
    </el-col>
    <el-dialog id="myEd" :title="positionForm.title" :visible.sync="positionForm.dialogFormVisible">
      <el-form label-position="right" :inline="true" :model="positionForm.position" :rules="positionForm.rules" ref="positionForm">
        <el-row style="margin-bottom: 10px;">
          <el-col :span="12">
            <el-form-item label="岗位名称" prop="posName"
            :rules="[{required: true, trigger: 'blur,change', validator: checkRequiredAndSpace}]">
                <el-input v-model="positionForm.position.posName" placeholder="岗位名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="排序号" prop="posSort"
            :rules="[{required: false, trigger: 'blur,change', validator: checkPositiveInteger}]">
                <el-input v-model="positionForm.position.posSort" placeholder="排序号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="margin-bottom: 10px;">
          <el-col :span="12">
            <el-form-item label="职级" prop="posLevel"
            :rules="[{required: false, trigger: 'blur,change', validator: checkPositiveInteger}]">
                <el-input v-model="positionForm.position.posLevel" placeholder="职级"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="岗位类型" prop="posType">
                <el-select v-model="positionForm.position.posType" placeholder="请选择">
                    <el-option v-for="item in positionTypes" :key="item.index" :label="item.name" :value="item.index">
                    </el-option>
                </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-show="positionForm.position.posType === '1'" style="margin-bottom: 10px;">
          <el-col :span="12">
            <el-form-item label="部门" prop="depId" class="is-required">
                <deptSelectTree v-on:nodeSelect="nodeClickData" :select-id="positionForm.position.depId" v-model="positionForm.position.depId"></deptSelectTree>
            </el-form-item>
          </el-col>
          <el-col :span="12">
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm('positionForm')">确 定</el-button>
          <el-button type="primary" @click="handleCancel('positionForm')">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog size="tiny" title="删除" :visible.sync="dialogDelFormVisible" class="del-dialog">
      <span>确定删除岗位：{{positionDelName}}吗？</span>
      <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="delPosition()">确 定</el-button>
          <el-button type="primary" @click="dialogDelFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  import deptSelectTree from 'src/components/deptSelectTree'

  export default {
    name: 'position',
    data () {
      const validateDepId = (rule, value, callback) => {
        if (value === '' && this.positionForm.position.posType === '1') {
          callback(new Error('请选择部门'))
        } else {
          callback()
        }
      }
      return {
        tableHeightDynamic: document.body.clientHeight - window.config.topBarHeight - 170,
        msg: '岗位',
        pageSizes: [20, 30, 50],
        pageSize: 20,
        loading: false,
        positionForm: {
          title: '',
          position: {
            posId: '',
            posName: '',
            posSort: '',
            posType: '',
            posLevel: '',
            depId: '',
            deptName: '',
            enable: ''
          },
          rules: {
            posType: [ { required: true, message: '请选择岗位类型', trigger: 'change' } ],
            depId: [ { validator: validateDepId } ]
          },
          dialogFormVisible: false
        },
        dialogDelFormVisible: false,
        positionDelName: '',
        positionDelComId: ''
      }
    },
    components: {
      deptSelectTree
    },
    created () {
      this.getData()
      this.initPositionTypes()
    },
    computed: {
      ...mapState({
        tableData: state => state.position.positionPage.positionList,
        pagesCount: state => state.position.positionPage.positionsCount,
        pagination: state => state.position.positionPagination,
        positionTypes: state => state.position.positionTypes
      })
    },
    methods: {
      checkRequiredAndSpace (rules, value, callback) {
        this.$store.dispatch('checkRequiredAndSpace', {rules, value, callback})
      },
      checkPositiveIntegerAndSpace (rules, value, callback) {
        this.$store.dispatch('checkPositiveIntegerAndSpace', {rules, value, callback})
      },
      checkPositiveInteger (rules, value, callback) {
        this.$store.dispatch('checkPositiveInteger', {rules, value, callback})
      },
      handleSizeChange (value) {
        this.pagination.pageSize = value
        this.getData()
      },
      handleCurrentChange (value) {
        this.currentpage = value
        this.pagination.pageIndex = value
        this.getData()
      },
      getData () {
        return this.$store.dispatch('searchPositionList')
      },
      nodeClickData (data) {
        this.positionForm.position.depId = data.id
      },
      initPosition (posId) {
        return this.$store.dispatch('getPosition', {posId: posId}).then((res) => {
          this.copyWithIngorePro(res, this.positionForm.position)
        }).catch(_ => {})
      },
      initPositionTypes () {
        return this.$store.dispatch('getPositionTypes').catch(_ => {})
      },
      async handleEdit (row) {
        this.positionForm.dialogFormVisible = true
        this.positionForm.title = '编辑岗位'
        await this.$store.dispatch('getDepartmentTree')
        this.initPosition(row.posId)
      },
      handleAdd (formName) {
        this.positionForm.dialogFormVisible = true
        this.positionForm.title = '新增岗位'
        this.clearProperties(this.positionForm.position)
        if (this.$refs['positionForm']) {
          this.$refs['positionForm'].resetFields()
        }
        this.positionForm.position.enable = 1
      },
      submitForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.savePosition(formName)
          } else {
            this.$message({
              message: '输入数据不正确！',
              type: 'warning'
            })
            return false
          }
        })
      },
      savePosition (formName) {
        if (this.positionForm.position.posType !== '1') {
          this.positionForm.position.depId = null
        }
        if (this.positionForm.position.posId) {
          return this.$store.dispatch('updatePosition', this.positionForm.position).then(() => {
            this.positionForm.dialogFormVisible = false
            this.$refs[formName].resetFields()
            this.$message({
              message: '编辑岗位成功',
              type: 'success'
            })
          }).catch(_ => {})
        } else {
          return this.$store.dispatch('addPosition', this.positionForm.position).then(() => {
            this.positionForm.dialogFormVisible = false
            this.$refs[formName].resetFields()
            this.$message({
              message: '新增岗位成功',
              type: 'success'
            })
          }).catch(_ => {})
        }
      },
      handleCancel (formName) {
        this.$refs[formName].resetFields()
        this.positionForm.dialogFormVisible = false
      },
      handleDelete (row) {
        this.dialogDelFormVisible = true
        this.positionDelComId = row.posId
        this.positionDelName = row.posName
      },
      delPosition () {
        return this.$store.dispatch('delPosition', {posId: this.positionDelComId}).then(() => {
          this.$message({
            message: '删除岗位成功',
            type: 'success'
          })
          this.dialogDelFormVisible = false
          this.getData()
        }).catch(_ => {
        })
      }
    }
  }
</script>

<style>
  #ptView .el-input {
    width: 180px;
  }
  #ptView .el-input__inner {
    width: 180px;
  }
</style>
