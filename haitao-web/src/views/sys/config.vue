<template>
  <div id="configView" style="padding: 0px 20px 0px 20px;">
    <el-col :span="24" class="toolbar" style="padding: 20px 0px;text-align: left">
      <el-row type="flex">
        <el-col :span="2">
          <el-row>
            <span style="font-size: 18px; line-height: 36px">参数</span>
          </el-row>
        </el-col>
        <el-col :span="22">
          <el-row>
            <div style="float: right">
              <span>参数组</span>
              <el-select style="width: 180px;margin-left: 5px" v-model="pagination.confType" placeholder="请选择">
                <el-option
                  v-for="item in confTypes"
                  :key="item.index"
                  :label="item.name"
                  :value="item.index">
                </el-option>
              </el-select>
              <el-button type="primary" @click="getData" :loading="loading" icon="search">搜索</el-button>
              <el-button type="primary" @click="handleAdd('config')" icon="plus">新增</el-button>
            </div>
            <div class="clear"></div>
          </el-row>
        </el-col>
      </el-row>
      <el-row>
        <hr style="color: #c7c5c5;"/>
      </el-row>
      <el-dialog :title="slot.title" :visible.sync="dialogFormVisible" size="tiny">
        <el-form :inline="true" :rules="rules" :model="config" ref="config">
          <el-form-item label="参数组" prop="confType">
            <el-select v-model="config.confType" clearable placeholder="请选择">
              <el-option
                v-for="item in confTypes"
                :key="item.index"
                :label="item.name"
                :value="item.index"
                :disabled="item.index === '10'">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="参数名称" prop="confKey"
          :rules="[{required: true, trigger: 'blur,change', validator: confKeyCheck}]">
            <el-input v-model="config.confKey" placeholder="参数名称" class="font"></el-input>
          </el-form-item>
          <el-form-item label="参数值">
            <el-input v-model="config.confValue" placeholder="参数值" class="font"></el-input>
          </el-form-item>
          <el-form-item label="参数说明">
            <el-input v-model="config.confDesc" placeholder="参数说明" class="font"></el-input>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="config.remark" placeholder="备注" class="font"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm('config')" style="">确认</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">取消</el-button>
        </div>
      </el-dialog>
      <el-dialog size="tiny" title="删除" :visible.sync="dialogDelFormVisible" class="del-dialog">
        <span>确定删除参数：{{configDelName}}吗？</span>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="delConfig()">确 定</el-button>
          <el-button type="primary" @click="dialogDelFormVisible = false">取 消</el-button>
        </div>
      </el-dialog>
      <div>
        <div class="block" style="margin-top: 10px;">
          <el-table :data="tableData" stripe border :height="tableHeightDynamic">
            <el-table-column type="index" label="序号" width="100"></el-table-column>
            <el-table-column prop="confKey" label="参数名称"></el-table-column>
            <el-table-column prop="confValue" label="参数值"></el-table-column>
            <el-table-column prop="confDesc" label="参数说明"></el-table-column>
            <el-table-column label="操作" width="180">
              <template slot-scope="scope">
                <a class="table-btn" @click="handleEdit(scope.row)">编辑</a>
                <a class="table-btn" @click="handleDelete(scope.row)">删除</a>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="block">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :page-sizes="pagesizes"
            :page-size="pagesize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagesCount">
          </el-pagination>
        </div>
      </div>
    </el-col>
  </div>
</template>

<script>
  import { mapState } from 'vuex'

  export default {
    name: 'config',
    data () {
      return {
        tableHeightDynamic: document.body.clientHeight - window.config.topBarHeight - 170,
        msg: '参数',
        pagesizes: [20, 30, 50], // 条数列
        pagesize: 20, // 行数
        loading: false,
        slot: { title: '' },
        dialogFormVisible: false,
        dialogDelFormVisible: false,
        configDelName: '',
        configDelId: '',
        rules: {
          confKey: [ { required: true, message: '参数名称不能为空', trigger: 'blur' } ],
          confType: [ { required: true, message: '请选择参数组', trigger: 'change' } ]
        }
      }
    },
    created () {
      this.getData()
      this.initConfTypes()
    },
    computed: {
      ...mapState({
        tableData: state => state.config.configPage.configList,
        pagesCount: state => state.config.configPage.configsCount,
        pagination: state => state.config.configPagination,
        confTypes: state => state.config.confTypes,
        config: state => state.config.config
      })
    },
    methods: {
      confKeyCheck (rules, value, callback) {
        this.$store.dispatch('checkRequiredAndSpace', {rules, value, callback})
      },
      handleSizeChange (value) {
        this.pagination.pageSize = value
        this.getData()
      },
      handleCurrentChange (value) {
        this.pagination.pageIndex = value
        this.getData()
      },
      handleAdd (formName) {
        this.dialogFormVisible = true
        this.clearConfig()
        this.config.enable = 1
        this.slot.title = '新增参数'
        if (this.$refs['config']) {
          this.$refs['config'].resetFields()
        }
      },
      handleEdit (row) {
        this.dialogFormVisible = true
        this.initConfig(row)
        this.slot.title = '编辑参数'
      },
      handleDelete (row) {
        this.dialogDelFormVisible = true
        this.configDelName = row.confKey
        this.configDelId = row.confId
      },
      getData () {
        return this.$store.dispatch('findConfigList')
      },
      resetForm (formName) {
        this.$refs[formName].resetFields()
      },
      submitForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.saveSysParam()
          } else {
            this.$message({
              message: '带*为必填项',
              type: 'warning'
            })
          }
        })
      },
      initConfTypes () {
        return this.$store.dispatch('getConfTypes')
      },
      initConfig (row) {
        return this.$store.dispatch('getConfig', {confId: row.confId}).then((res) => {
          this.config = res
        }).catch(() => {
          this.$message({
            message: '初始化参数失败',
            type: 'error'
          })
        })
      },
      saveSysParam () {
        if (this.config.id !== undefined && this.config.id !== '') {
          delete this.config.createTime
          return this.$store.dispatch('updateConfig', this.config).then(() => {
            this.$message({
              message: '编辑参数成功',
              type: 'success'
            })
            this.dialogFormVisible = false
            this.getData()
          })
        } else {
          delete this.config.confId
          return this.$store.dispatch('addConfig', this.config).then(() => {
            this.$message({
              message: '保存参数成功',
              type: 'success'
            })
            this.dialogFormVisible = false
            this.getData()
          })
        }
      },
      delConfig () {
        return this.$store.dispatch('delConfig', {confId: this.configDelId}).then(() => {
          this.$message({
            message: '删除参数成功',
            type: 'success'
          })
          this.dialogDelFormVisible = false
        })
      },
      clearConfig () {
        this.clearProperties(this.config)
      }
    }
  }
</script>

<style>
  #configView .el-input {
    width: 180px;
  }
  #configView .el-input__inner {
    width: 180px;
  }
</style>
