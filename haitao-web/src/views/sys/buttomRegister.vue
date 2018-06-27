<template>
  <el-breadcrumb separator="/">
    <div class="main-content">
     <div class="main-content-top">
       <el-row type="flex" justify="space-between">
        <el-col :span="4" style="padding-left:30px;width: inherit;">
          <span class="el-breadcrumb__separator">|</span>
          <span class="el-breadcrumb__item__inner">按钮管理</span>
        </el-col>
        <!--工具条-->
        <el-col :span="20" class="toolbar key-search-form">
          <el-form :inline="true">
            <el-form-item label="按钮名称">
              <el-input v-model="moudelName" placeholder="按钮名称" ></el-input>
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
     </div>
     <div class="main-content-table">
      <div class="block">
        <el-table :data="moudelList" border stripe height="510" style="width: 100%;">
          <el-table-column type="index" label="序号" width="100"></el-table-column>
          <el-table-column prop="moudleName" label="按钮名称"></el-table-column>
          <el-table-column label="所属模块">
            <template slot-scope="scope">{{moudelParentName}}</template>
          </el-table-column>
          <el-table-column prop="sys" label="所属系统"></el-table-column>
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
          :total="moudelCount">
        </el-pagination>
      </div>
      </div>
    </div>
    <el-dialog class="common-dialog" :title="moudelHandle.title" :visible.sync="moudelHandle.dialogFormVisible">
        <el-form label-position="right" :inline="true">
          <el-row>
            <el-col :span="12">
              <el-form-item label="按钮名称" required>
                <el-input v-model="moudelHandle.moudel.moudleName" placeholder="按钮名称"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="按钮排序" required>
                <el-input v-model="moudelHandle.moudel.moudleSort" placeholder="12345.."></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="按钮链接">
                <el-input v-model="moudelHandle.moudel.moudleUrl" placeholder="按钮链接"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="按钮图标">
                <el-input v-model="moudelHandle.moudel.moudleIcon" placeholder="按钮图标"></el-input>
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
        <span>确定删除按钮：{{moudelDelName}}吗？</span>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="deleteRow()">确 定</el-button>
            <el-button type="primary" @click="dialogDelFormVisible = false">取 消</el-button>
        </div>
    </el-dialog>
  </el-breadcrumb>
    

</template>

<script>
  /* eslint-disable */
  export default {
    name: 'moudel',
    data () {
      return {
        // 查询
        moudelId:'',   // 当前所属菜单
        moudelName: '', // 模糊匹配
        moudelParentName: '',
        currentpage: 1, // 当前页数默认是1
        pagesizes: [10, 20, 30], // 条数列
        pagesize: 10, // 行数
        loading: false, // 加载状态
        moudelList: [],
        moudelCount: 0,

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
          },
          moudels: []
        },

        // 删除
        dialogDelFormVisible: false,
        moudelDelName: '',
        moudelDelmoudleId: ''
      }
    },
    created () {
      this.moudelId = this.$route.params.moudleId
      this.moudelParentName = this.$route.params.moudelName
      this.getData()
    },
    methods: {
      handleSizeChange(val){
        this.pagesize = val
        this.getData();
      },
      handleCurrentChange(val){
        this.currentpage = val
        this.getData();
      },
      getData(){
        return this.$store.dispatch('buttomRegisterList', {moudelId: this.moudelId, moudelName: this.moudelName, 
        pageIndex: this.currentpage, pageSize: this.pagesize}).then((reply) => {
          this.moudelList = reply.content
          for (var index in this.moudelList) {
            var sys = this.moudelList[index].moudleSign
            if (sys.indexOf('0-1-') > -1) {
              this.moudelList[index].sys = '基础设置'
            } else if (sys.indexOf('0-2-') > -1) {
              this.moudelList[index].sys = '综合安全管理'
            } else {
              this.moudelList[index].sys = '其他'
            }
          }
          this.moudelCount = reply.numberOfElements
        })
      },
      handleDelete(row) {
        this.dialogDelFormVisible = true
        this.moudelDelmoudleId = row.moudleId
        this.moudelDelName = row.moudleName
      },
      deleteRow () {
        this.dialogDelFormVisible = false
        return this.$store.dispatch('delButtomRegister', { moudleId: this.moudelDelmoudleId }).then((reply) => {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.getData()
        })
      },
      handleEdit (row) {
        this.moudelHandle.title = '编辑按钮'
        this.moudelHandle.moudel.moudleId = row.moudleId
        this.moudelHandle.dialogFormVisible = true
        this.getmoudel()
      },
      handleAdd(){
        this.clearProperties(this.moudelHandle.moudel)
        this.moudelHandle.title = '新增按钮'
        this.moudelHandle.dialogFormVisible = true
      },
      getmoudel () {
        return this.$store.dispatch('ButtomRegisterOne', {
          moudleId: this.moudelHandle.moudel.moudleId
        }).then((reply) => {
          this.moudelHandle.moudel = this.copyProperties(reply.data, ['id', 'moudleId', 'moudleName', 'moudleUrl', 'moudleIcon',
            'moudleSort', 'moudleParentId', 'moudleSign', 'moudleLevel', 'moudleType', 'remark', 'createUser', 'createTime'])
        })
      },
      savemoudel () {
        if(!this.moudelHandle.moudel.moudleName.trim() || !this.moudelHandle.moudel.moudleSort.trim().match(/^\d+$/)){
          this.$message("请输入正确的数据")
          return
        }
        if(this.moudelHandle.moudel.moudleId){
          return this.$store.dispatch('updataButtomRegister',  this.moudelHandle.moudel).then((reply) => {
            this.$message('操作成功')
            this.getData()
            this.moudelHandle.dialogFormVisible = false
          })
        } else {
          this.moudelHandle.moudel.moudleParentId = this.moudelId
          return this.$store.dispatch('addButtomRegister',  this.moudelHandle.moudel).then((reply) => {
            this.$message('操作成功')
            this.getData()
            this.moudelHandle.dialogFormVisible = false
          })
        }
      }
    }
  }
</script>
