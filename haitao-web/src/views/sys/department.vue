<template>
  <div id="dmView" style="padding: 0px 20px 0px 20px;">
    <el-col :span="24" class="toolbar" style="padding: 20px 0px;text-align: left">
      <el-row type="flex">
        <el-col :span="4">
          <span style="font-size: 18px; line-height: 36px">部门</span>
        </el-col>
      </el-row>
      <el-row>
        <hr style="color: #c7c5c5;"/>
      </el-row>
      <el-row style="margin-top: 10px;">
        <el-col :span="6">
          <div :style="{height: tableHeightDynamic + 'px'}" class="grid-content bg-purple tree-content" style="background-color: #FFFFFF; overflow: hidden">
            <div class="tree-top">
              <span>组织结构</span><i @click="clearDept()" class="el-icon-plus"></i>
            </div>
            <deptTree v-on:nodeClick="nodeTreeData"></deptTree>
          </div>
        </el-col>
        <el-col :span="18">
          <!--<el-button type="primary" @click="clearDept()">新增</el-button>
          <el-button type="danger"  @click="delDept()">删除</el-button>-->
          <div :style="{height: tableHeightDynamic + 'px'}" class="grid-content bg-purple-light tree-right-content" style="background-color: #FFFFFF;">
            <div class="tree-top">
              <span>组织部门操作</span>
            </div>
            <p class="right-content-title">{{title}}</p>
            <el-form label-width="180px" class="tree-form" :rules="rules" :model="department" ref="department">
              <el-form-item label="部门名称" prop="depName">
                <el-input v-model="department.depName"></el-input>
                <div class="el-form-item__error" v-show="isDuplicateName">
                  部门名称重复！
                </div>
              </el-form-item>
              <el-form-item label="部门排序号" prop="depSort">
                <el-input v-model.number="department.depSort"></el-input>
              </el-form-item>
              <el-form-item label="部门负责人">
                <el-input v-model="department.depLeader"></el-input>
              </el-form-item>
              <el-form-item label="所属单位" prop="comId">
                <el-select placeholder='请选择' v-model='department.comId'>
                  <el-option v-for="item in companys" :key="item.comId" :value="item.comId" :label="item.comName">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="上级部门" prop="depParentId">
                <deptSelectTree 
                v-model="department.depParentId" 
                v-on:nodeSelect="nodeClickData" 
                :select-id="deptId"
                :self-id="selfId"
                :inputReadOnly="true" 
                :allDeptShow="false">
                </deptSelectTree>
              </el-form-item>
              <el-form-item label="备注">
                <el-input type="textarea" v-model="department.remark"></el-input>
              </el-form-item>
              <div style="float:right;">
                <el-button type="primary" @click="delDeptClick()">删除</el-button>
                <el-button type="primary" @click="submitForm('department')">确认</el-button>
                <el-button type="primary" @click="clearDept">取消</el-button>
              </div>
            </el-form>
          </div>
        </el-col>
      </el-row>
    </el-col>
    <div class="clear"></div>
    <el-dialog size="tiny" title="删除" :visible.sync="dialogDelFormVisible" class="del-dialog">
        <span>确定删除部门：{{departmentDelName}}吗？</span>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="delDept()">确 定</el-button>
            <el-button type="primary" @click="dialogDelFormVisible = false">取 消</el-button>
        </div>
    </el-dialog>
  </div>
</template>

<script>
  import deptTree from 'src/components/deptTree'
  import deptSelectTree from 'src/components/deptSelectTree'
  
  export default {
    name: 'department',
    computed: {
    },
    data () {
      return {
        tableHeightDynamic: document.body.clientHeight - window.config.topBarHeight - 120,
        msg: '部门',
        treeShowCtrl: false,
        isDuplicateName: false,
        department: {depName: '', depSort: '', depParentId: '', remark: '', depLeader: '', comId: ''},
        companys: [],
        deptId: '',
        selfId: '',
        title: '新增部门信息：',
        dialogDelFormVisible: false,
        departmentDelName: '',
        rules: {
          depName: [ { required: true, message: '部门名称不能为空', trigger: 'blur,change' } ],
          depSort: [ { required: true, message: '部门排序号不能为空' }, { type: 'number', message: '部门排序号必须为数字' } ],
          depParentId: [{ required: true, message: '上级部门不能为空', trigger: 'change' }],
          comId: [{ required: true, message: '所属单位不能为空', trigger: 'change' }]
        }
      }
    },
    components: {
      deptTree,
      deptSelectTree
    },
    created () {
      this.initAllCompany()
    },
    methods: {
      checkName () {
        if (!this.department.depId && this.department.depName) {
          return this.$store.dispatch('checkDeptName', {deptName: this.department.depName}).then((res) => {
            if (res) {
              this.isDuplicateName = true
            } else {
              this.isDuplicateName = false
            }
          })
        } else {
          this.isDuplicateName = false
        }
      },
      nodeClickData (data) {
        this.department.depParentId = data.id
        this.deptId = data.id
      },
      nodeTreeData (data) {
        this.title = '编辑部门信息：'
        return this.$store.dispatch('initDepartment', {deptId: data.id}).then((res) => {
          this.department = res
          this.deptId = res.depParentId
          this.selfId = res.depId
        })
      },
      initAllCompany () {
        return this.$store.dispatch('findAllCompany').then((res) => {
          this.companys = res
        })
      },
      clearDept () {
        this.department = {depName: '', depSort: '', depParentId: '', remark: '', depLeader: '', comId: ''}
        this.$refs['department'].resetFields()
        this.deptId = ''
        this.selfId = ''
        this.title = '新增部门信息：'
      },
      submitForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.deptSave()
          } else {
            this.$message({
              message: '输入数据不正确！',
              type: 'warning'
            })
            return false
          }
        })
      },
      deptSave () {
        let retProm = null
        if (this.isDuplicateName) {
          return
        }
        if (!this.department.depName.trim()) {
          this.$message({
            message: '部门名称不能只为空格',
            type: 'info'
          })
          this.department.depName = ''
          return
        }
        if (this.department.depId) {
          retProm = this.$store.dispatch('updateDepartment', this.department).then(() => {
            this.$message({
              message: '编辑部门成功',
              type: 'success'
            })
            this.clearDept()
          }).catch((_) => {
          })
        } else {
          retProm = this.$store.dispatch('addDepartment', this.department).then(() => {
            this.$message({
              message: '新增部门成功',
              type: 'success'
            })
            this.clearDept()
          }).catch((_) => {
          })
        }
        return retProm
      },
      delDeptClick () {
        if (this.department.depId) {
          this.dialogDelFormVisible = true
          this.departmentDelName = this.department.depName
        } else {
          this.$message({
            message: '请先选中部门树',
            type: 'info'
          })
        }
      },
      delDept () {
        return this.$store.dispatch('deleteDepartment', {deptId: this.department.depId}).then((res) => {
          this.$message({
            message: '删除部门成功！',
            type: 'success'
          })
          this.dialogDelFormVisible = false
          this.clearDept()
          this.$refs['department'].resetFields()
        }).catch(_ => {
        })
      }
    }
  }
</script>

<style>
#dmView .el-input {
  width: 100%;
  float: left;
}
#dmView .el-input__inner {
  width: 100%;
  float: left;
}
#dmView .el-textarea {
  width: 100%;
  float: left;
}
</style>
