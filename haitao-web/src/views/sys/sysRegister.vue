<template>
  <div id="menuView" style="padding: 0px 15px 0px 15px;">
    <el-col :span="24" class="toolbar" style="padding: 20px 0px;text-align: left">
      <el-row type="flex" style="padding-bottom: 15px">
        <el-col :span="4">
          <el-row>
            <span style="font-size: 18px; line-height: 36px">系统管理</span>
          </el-row>
        </el-col>
        <!--工具条-->
        <el-col :span="20">
          <el-form :inline="true" style="float: right">
            <el-form-item label="系统名称">
              <el-input v-model="moudelName" placeholder="系统名称" ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" class="" @click="getData" :loading="loading">查询</el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handleAdd()">新增</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <div class="clear"></div>
      <el-row>
        <hr style="color: #c7c5c5;margin-top: -10px"/>
      </el-row>
      <div class="block">
        <el-table :data="moudels" border stripe style="width: 100%;">
          <el-table-column type="index" label="序号" width="100"></el-table-column>
          <el-table-column prop="moudleName" label="系统名称"></el-table-column>
          <el-table-column prop="moudleUrl" label="URL"></el-table-column>
          <el-table-column prop="" label="接入方式"></el-table-column>
          <el-table-column prop="" label="系统权限"></el-table-column>
          <el-table-column prop="" label="单点登录"></el-table-column>
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
    <el-dialog id="myEd" :title="moudelHandle.title" :visible.sync="moudelHandle.dialogFormVisible">
      <el-form label-position="right" :inline="true">
        <el-row>
          <el-col :span="12">
            <el-form-item label="系统名称" required>
              <el-input v-model="moudelHandle.moudel.moudleName" placeholder="系统名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="系统排序" required>
              <el-input v-model="moudelHandle.moudel.moudleSort" placeholder="12345.."></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="系统链接">
              <el-input v-model="moudelHandle.moudel.moudleUrl" placeholder="系统链接"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="系统图标">
              <el-input v-model="moudelHandle.moudel.moudleIcon" placeholder="系统图标"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="备注信息">
              <el-input type="textarea" :rows="moudelHandle.textareaRows" v-model="moudelHandle.moudel.remark" placeholder="备注信息"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="savemoudel()">确 定</el-button>
          <el-button type="primary" @click="moudelHandle.dialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog size="tiny" title="删除" :visible.sync="dialogDelFormVisible" class="del-dialog">
      <span>确定删除系统：{{moudelDelName}}吗？</span>
      <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="deleteRow()">确 定</el-button>
          <el-button type="primary" @click="dialogDelFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
    

</template>

<script>
  export default {
    name: 'sysRegister',
    data () {
      return {
        // 查询
        moudelName: '', // 模糊匹配
        currentpage: 1, // 当前页数默认是1
        pagesizes: [10, 20, 30], // 条数列
        pagesize: 10, // 行数
        loading: false, // 加载状态
        moudels: [],
        pagesCount: 0,

        // 增加或编辑
        moudelHandle: {
          title: '',
          dialogFormVisible: false,
          textareaRows: 5,
          moudel: {
            id: '',
            moudleId: '',
            moudleName: '',
            moudleParentId: '',
            moudleUrl: '',
            moudleIcon: '',
            moudleSort: '',
            moudleSign: '',
            moudleLevel: '',
            moudleType: '',
            remark: '',
            createUser: '',
            createTime: ''
          }
        },

        // 删除
        dialogDelFormVisible: false,
        moudelDelName: '',
        moudelDelmoudleId: ''
      }
    },
    created () {
      this.getData()
    },
    methods: {
      getData () {
        return this.$store.dispatch('sysRegisterList', {moudelName: this.moudelName, pageIndex: this.currentpage, pageSize: this.pagesize}).then((reply) => {
          this.moudels = reply.content
          this.pagesCount = reply.totalElements
        })
      },
      handleSizeChange (val) {
        this.pagesize = val
        this.getData()
      },
      handleCurrentChange (val) {
        this.currentpage = val
        this.getData()
      },
      handleDelete (row) {
        this.dialogDelFormVisible = true
        this.moudelDelmoudleId = row.moudleId
        this.moudelDelName = row.moudleName
      },
      deleteRow () {
        this.dialogDelFormVisible = false
        return this.$store.dispatch('delSysRegister', { moudleId: this.moudelDelmoudleId }).then((reply) => {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.getData()
        })
      },
      handleEdit (row) {
        this.moudelHandle.title = '编辑系统'
        this.moudelHandle.moudel.moudleId = row.moudleId
        this.moudelHandle.dialogFormVisible = true
        this.getmoudel()
      },
      handleAdd () {
        this.clearProperties(this.moudelHandle.moudel)
        this.moudelHandle.title = '新增系统'
        this.moudelHandle.dialogFormVisible = true
      },
      getmoudel () {
        return this.$store.dispatch('sysRegisterOne', {
          moudleId: this.moudelHandle.moudel.moudleId
        }).then((reply) => {
          this.moudelHandle.moudel = this.copyProperties(reply.data, ['id', 'moudleId', 'moudleName', 'moudleUrl', 'moudleIcon',
            'moudleSort', 'moudleParentId', 'moudleSign', 'moudleLevel', 'moudleType', 'remark', 'createUser', 'createTime'])
        })
      },
      savemoudel () {
        if (!this.moudelHandle.moudel.moudleName.trim() || !this.moudelHandle.moudel.moudleSort.trim().match(/^\d+$/)) {
          this.$message('请输入正确的数据')
          return
        }
        if (this.moudelHandle.moudel.moudleId) {
          return this.$store.dispatch('updataSysRegister', this.moudelHandle.moudel).then((reply) => {
            this.$message({
              message: '操作成功',
              type: 'success'
            })
            this.getData()
            this.moudelHandle.dialogFormVisible = false
          })
        } else {
          this.moudelHandle.moudel.moudleParentId = '0'
          return this.$store.dispatch('addSysRegister', this.moudelHandle.moudel).then((reply) => {
            this.$message({
              message: '操作成功',
              type: 'success'
            })
            this.getData()
            this.moudelHandle.dialogFormVisible = false
          })
        }
      }
    }
  }
</script>

<style>
  #myEd .el-input {
    width: 180px;
  }
  #myEd .el-input__inner {
    width: 180px;
  }
  #myEd .el-textarea {
    width: 180px;
  }
</style>
