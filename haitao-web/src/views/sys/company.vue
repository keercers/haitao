<template>
  <div id="cpView" style="padding: 0px 20px 0px 20px;">
    <el-col :span="24" class="toolbar" style="padding: 20px 0px;text-align: left">
      <el-row type="flex">
        <el-col :span="2">
          <el-row>
            <span style="font-size: 18px; line-height: 36px">单位</span>
          </el-row>
        </el-col>
        <!--工具条-->
        <el-col :span="22">
          <el-row>
            <div style="float: right">
              <span>单位名称</span>
              <el-input v-model="pagination.comName" placeholder="单位名称" style="width: 180px;margin-left: 5px"></el-input>
              <span>地址</span>
              <el-input v-model="pagination.comAddr" placeholder="地址" style="width: 180px;margin-left: 5px"></el-input>
              <el-button type="primary" @click="getData" icon="search">查询</el-button>
              <el-button type="primary" @click="handleAdd()" icon="plus">新增</el-button>
            </div>
            <div class="clear"></div>
          </el-row>
        </el-col>
      </el-row>
      <div class="block" style="margin-top: 10px;">
        <el-table :data="units" border stripe :height="tableHeightDynamic">
          <el-table-column type="index" label="序号" width="100"></el-table-column>
          <el-table-column prop="comName" label="名称"></el-table-column>
          <el-table-column prop="comParentName" label="上级单位"></el-table-column>
          <el-table-column prop="comAddr" label="地址"></el-table-column>
          <el-table-column prop="comContacts" label="联系人"></el-table-column>
          <el-table-column prop="comTel" label="电话"></el-table-column>
          <el-table-column label="操作">
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
          :current-page="currentpage"
          :page-sizes="pagesizes"
          :page-size="pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagesCount">
        </el-pagination>
      </div>
    </el-col>
    <el-dialog id="myEd" :title="companyForm.title" :visible.sync="companyForm.dialogFormVisible">
      <el-form label-position="right" :rules="companyForm.rules" :model="companyForm.company" ref="companyForm" :inline="true">
        <el-row type="flex" justify="space-between">
          <el-col :span="12">
            <el-form-item label="单位名称" prop="comName">
              <el-input v-model="companyForm.company.comName" placeholder="单位名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="上级单位">
              <el-select placeholder='请选择' v-model='companyForm.company.comParentId'>
                <el-option v-for="item in companys" :key="item.comId" :value="item.comId" :label="item.comName">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="单位地址" prop="comAddr">
              <el-input v-model="companyForm.company.comAddr" placeholder="单位地址"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单位电话">
              <el-input v-model="companyForm.company.comTel" placeholder="单位电话"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="单位主页">
              <el-input v-model="companyForm.company.comHomepage" placeholder="单位主页"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单位联系人">
              <el-input v-model="companyForm.company.comContacts" placeholder="单位联系人"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注信息" style="width:100%">
              <el-input type="textarea" :rows="companyForm.textareaRows" v-model="companyForm.company.remark" placeholder="备注信息"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm('companyForm')">确 定</el-button>
          <el-button type="primary" @click="companyForm.dialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog size="tiny" title="删除" :visible.sync="dialogDelFormVisible" class="del-dialog">
      <span>确定删除单位：{{companyDelName}}吗？</span>
      <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="deleteRow()">确 定</el-button>
          <el-button type="primary" @click="dialogDelFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  
  /* eslint-disable */
  export default {
    name: 'company',
    data () {
      return {
        tableHeightDynamic: document.body.clientHeight - config.topBarHeight - 170,
        msg: '单位',
        keyWord: '',
        address: '',
        // 页眉设置参数
        currentpage: 1, // 当前页数默认是1
        pagesizes: [20, 30, 50], // 条数列
        pagesize: 20, // 行数
        activeNum: 0, // 默认起始行数
        endNum: 20, // 默认结束行数
        tableData: [], // 分页组件传回的分页后数据
        // 参数设置
        url: '/base/store/findStoreTypes', // 分页查询请求路径
        delurl: '/base/store/delStoreType', // 删除请求路径
        loading: false,
        companyForm: {
          title: '',
          dialogFormVisible: false,
          textareaRows: 5,
          company: {
            id: '',
            comId: '',
            comName: '',
            comParentId: '',
            comAddr: '',
            comTel: '',
            comContacts: '',
            comHomepage: '',
            remark: ''
          },
          rules: {
            comName: [ { required: true, message: '单位名称不能为空', trigger: 'blur' } ],
            comAddr: [ { required: true, message: '单位地址不能为空', trigger: 'blur' } ]
          },
        },
        dialogDelFormVisible: false,
        companyDelName: '',
        companyDelComId: '',
        companys:[]
      }
    },
    created () {
      this.getData()
      this.initAllCompany()
    },
    computed:{
      ...mapState({
        units: state => state.company.companyList,
        pagesCount: state => state.company.companyCount,
        pagination: state => state.company.companyPagination,
      })
    }, 
    methods: {
      getData(){
        return this.$store.dispatch('findCompanyList')
      },
      initAllCompany () {
        return this.$store.dispatch('findAllCompany').then((res) => {
          this.companys = res
        })
      },
      handleSizeChange(val){
        this.pagination.pageSize = val
        this.getData();
      },
      handleCurrentChange(val){
        this.pagination.pageIndex = val
        this.getData();
      },
      handleDelete(row) {
        this.dialogDelFormVisible = true
        this.companyDelName = row.comName
        this.companyDelComId = row.comId
      },
      deleteRow () {
        return this.$store.dispatch('delCompany', { comId: this.companyDelComId }).then((reply) => {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.dialogDelFormVisible = false
          this.getData()
        }).catch(() => {
        })
      },
      handleEdit (row) {
        this.companyForm.title = '编辑单位'
        if (this.$refs['companyForm']) {
          this.$refs['companyForm'].resetFields()
        }
        this.companyForm.company.comId = row.comId
        this.companyForm.company.id = row.id
        this.companyForm.dialogFormVisible = true
        this.getCompany()
      },
      handleAdd(){
        this.clearProperties(this.companyForm.company)
        this.companyForm.title = '新增单位'
        this.companyForm.dialogFormVisible = true
        if (this.$refs['companyForm']) {
          this.$refs['companyForm'].resetFields()
        }
      },
      getCompany () {
        this.$store.dispatch('findCompany', {
          comId: this.companyForm.company.comId
        }).then((reply) => {
          this.companyForm.company = this.copyProperties(reply, ['id','comId','comName','comParentId','comAddr','comTel','comContacts','comHomepage','remark'])
        })
      },
      submitForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.saveCompany()
          } else {
            this.$message({
              message: '输入数据不正确！',
              type: 'warning'
            })
            return false
          }
        })
      },
      saveCompany () {
        if (!this.companyForm.company.comName.trim()) {
          this.$message({
            message: '单位名称不能只为空格',
            type: 'info'
          })
          this.companyForm.company.comName = ''
          return
        }
        if (this.companyForm.company.id !== undefined && this.companyForm.company.id !== '') {
          return this.$store.dispatch('updateCompany', this.companyForm.company).then(() => {
            this.$message({
              message: '编辑单位成功',
              type: 'success'
            })
            this.companyForm.dialogFormVisible = false
            this.$refs['companyForm'].resetFields()
            this.getData()
          }).catch(() => {
          })
        } else {
          return this.$store.dispatch('addCompany', this.companyForm.company).then(() => {
            this.$message({
              message: '保存单位成功',
              type: 'success'
            })
            this.companyForm.dialogFormVisible = false
            this.$refs['companyForm'].resetFields()
            this.getData()
          }).catch(() => {
          })
        }
      }
    }
  }
</script>

<style>
.campany .el-dialog--small {
  width: 791.5px!important;
}
#cpView .el-input {
  width: 180px;
}
#cpView .el-input__inner {
  width: 180px;
}
#cpView .el-textarea {
  width: 180px;
}
</style>
