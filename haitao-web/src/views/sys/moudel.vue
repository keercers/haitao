<template>
  <el-breadcrumb separator="/">
    <div class="main-content">
     <div class="main-content-top">
       <el-row type="flex" justify="space-between">
        <el-col :span="4" style="padding-left:30px;width: inherit;">
          <span class="el-breadcrumb__separator">|</span>
          <span class="el-breadcrumb__item__inner">模块管理</span>
        </el-col>
        <!--工具条-->
        <el-col :span="20" class="toolbar key-search-form">
          <el-form :inline="true">
            <el-form-item label="模块名称">
              <el-input v-model="moudelName" placeholder="模块名称" ></el-input>
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
        <el-table :data="units" border stripe height="510" style="width: 100%;">
          <el-table-column type="index" label="序号" width="100"></el-table-column>
          <el-table-column prop="moudleName" label="模块名称"></el-table-column>
          <el-table-column prop="moudleParentName" label="上级模块"></el-table-column>
          <el-table-column prop="sys" label="所属系统"></el-table-column>
          <el-table-column label="图标">
            <template slot-scope="scope">
              <img :src="scope.row.moudleIcon" >
            </template>
          </el-table-column>
          <el-table-column label="操作" width="200">
            <template slot-scope="scope">
              <el-button class="table-btn" type="text" size="small" @click="buttonRegister(scope.row)">按钮控制</el-button>
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
      </div>
    </div>
    <el-dialog class="common-dialog" :title="moudelHandle.title" :visible.sync="moudelHandle.dialogFormVisible">
        <el-form label-position="right" :inline="true">
          <el-row>
            <el-col :span="12">
              <el-form-item label="模块名称" required>
                <el-input v-model="moudelHandle.moudel.moudleName" placeholder="模块名称"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="上级模块" required>
                <el-select v-model="moudelHandle.moudel.moudleParentId" placeholder="请选择">
                  <el-option v-for="item in moudelHandle.moudels" :key="item.moudleId" :label="item.moudleName" :value="item.moudleId"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="模块链接">
                <el-input v-model="moudelHandle.moudel.moudleUrl" placeholder="模块链接"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="模块图标">
                <el-input v-model="moudelHandle.moudel.moudleIcon" placeholder="模块图标"></el-input>
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
              <el-form-item label="模块排序" required>
                <el-input v-model="moudelHandle.moudel.moudleSort" placeholder="12345.."></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="savemoudel()">确 定</el-button>
            <el-button type="primary" @click="moudelHandle.dialogFormVisible = false">取 消</el-button>
        </div>
    </el-dialog>
    <el-dialog size="tiny" title="删除" :visible.sync="dialogDelFormVisible" class="del-dialog">
        <span>确定删除模块：{{moudelDelName}}吗？</span>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="deleteRow()">确 定</el-button>
            <el-button type="primary" @click="dialogDelFormVisible = false">取 消</el-button>
        </div>
    </el-dialog>
  </el-breadcrumb>
    

</template>

<script>
  import {mapState} from 'vuex'
  /* eslint-disable */
  export default {
    name: 'moudel',
    data () {
      return {
        // 查询
        moudelName: '', // 模糊匹配
        currentpage: 1, // 当前页数默认是1
        pagesizes: [10, 20, 30], // 条数列
        pagesize: 10, // 行数
        loading: false, // 加载状态

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
      this.getData()
    },
    computed:{
      ...mapState({
        units: state => state.menu.moudelList,
        pagesCount: state => state.menu.moudelCount,
      })
    }, 
    methods: {
      getData(){
        return this.$store.dispatch('menuList', {moudelName: this.moudelName, pageIndex: this.currentpage, pageSize: this.pagesize})
      },
      handleSizeChange(val){
        this.pagesize = val
        this.getData();
      },
      handleCurrentChange(val){
        this.currentpage = val
        this.getData();
      },
      handleDelete(row) {
        this.dialogDelFormVisible = true
        this.moudelDelmoudleId = row.moudleId
        this.moudelDelName = row.moudleName
      },
      deleteRow () {
        this.dialogDelFormVisible = false
        return this.$store.dispatch('menuDel', { moudleId: this.moudelDelmoudleId }).then((reply) => {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.getData()
        })
      },
      handleEdit (row) {
        this.getmoudels()
        this.moudelHandle.title = '编辑模块'
        this.moudelHandle.moudel.moudleId = row.moudleId
        this.moudelHandle.dialogFormVisible = true
        this.getmoudel()
      },
      handleAdd(){
        this.getmoudels()
        this.clearProperties(this.moudelHandle.moudel)
        this.moudelHandle.title = '新增模块'
        this.moudelHandle.dialogFormVisible = true
      },
      getmoudel () {
        return this.$store.dispatch('menuOne', {
          moudleId: this.moudelHandle.moudel.moudleId
        }).then((reply) => {
          this.moudelHandle.moudel = this.copyProperties(reply.data, ['id', 'moudleId', 'moudleName', 'moudleUrl', 'moudleIcon',
            'moudleSort', 'moudleParentId', 'moudleSign', 'moudleLevel', 'moudleType', 'remark', 'createUser', 'createTime'])
        })
      },
      getmoudels () {
        return this.$store.dispatch('menuAll').then((reply) => {
          this.moudelHandle.moudels = reply.data
        })
      },
      savemoudel () {
        if(!this.moudelHandle.moudel.moudleParentId || !this.moudelHandle.moudel.moudleName.trim() || !this.moudelHandle.moudel.moudleSort.trim().match(/^\d+$/)){
          this.$message("请输入正确的数据")
          return
        }
        if(this.moudelHandle.moudel.moudleId){
          return this.$store.dispatch('menuUpdate',  this.moudelHandle.moudel).then((reply) => {
            this.$message('操作成功')
            this.moudelHandle.dialogFormVisible = false
            this.getData()
          })
        } else {
          return this.$store.dispatch('menuAdd',  this.moudelHandle.moudel).then((reply) => {
            this.$message('操作成功')
            this.moudelHandle.dialogFormVisible = false
            this.getData()
          })
        }
      },
      buttonRegister (row) {
        this.$router.push({ name: 'buttomRegister', params: { moudleId: row.moudleId, moudelName: row.moudleName }})
      }
    }
  }
</script>
