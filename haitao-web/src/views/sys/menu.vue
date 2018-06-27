<template>
  <div id="menuView" style="padding: 0px 20px 0px 20px;">
    <el-col :span="24" class="toolbar" style="padding: 20px 0px;text-align: left">
      <el-row type="flex">
        <el-col :span="4">
          <el-row>
            <span style="font-size: 18px; line-height: 36px">模块管理</span>
          </el-row>
        </el-col>
        <!--工具条-->
        <el-col :span="20">
          <el-row>
            <div style="float: right">
              <span>模块名称</span>
              <el-input v-model="moudelName" placeholder="模块名称" style="width: 200px;margin-left: 5px"></el-input>
              <el-button type="primary" @click="getData" :loading="loading" icon="search">查询</el-button>
              <el-button type="primary" @click="handleAdd()" icon="plus">新增</el-button>
            </div>
            <div class="clear"></div>
          </el-row>
        </el-col>
      </el-row>
      <div class="block" style="margin-top: 10px;">
        <el-table :data="units" border stripe :height="tableHeightDynamic">
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
              <!--<el-button class="table-btn" type="text" size="small" @click="buttonRegister(scope.row)">按钮控制</el-button>-->
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
            <el-form-item label="模块名称" required>
              <el-input v-model="moudelHandle.moudel.moudleName" placeholder="模块名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="上级模块" required>
              <el-select v-model="moudelHandle.moudel.moudleParentId" placeholder="请选择" filterable>
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
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  
  /* eslint-disable */
  export default {
    name: 'moudel',
    data () {
      return {
        tableHeightDynamic: document.body.clientHeight - config.topBarHeight - 170,
        // 查询
        moudelName: '', // 模糊匹配
        currentpage: 1, // 当前页数默认是1
        pagesizes: [20, 30, 50], // 条数列
        pagesize: 20, // 行数
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
      savemoudel: _.debounce(function () {
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
      }, 600),
      buttonRegister (row) {
        this.$router.push({ name: 'buttomRegister', params: { moudleId: row.moudleId, moudelName: row.moudleName }})
      }
    }
  }
</script>

<style>
  #menuView .el-input {
    width: 180px;
  }
  #menuView .el-input__inner {
    width: 180px;
  }
  #menuView .el-textarea {
    width: 180px;
  }
</style>
