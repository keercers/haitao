<template>
    <div id="userView" style="padding: 0px 20px 0px 20px;">
      <el-col :span="24" class="toolbar" style="padding: 20px 0px;text-align: left">
        <el-row type="flex">
          <el-col :span="2">
            <el-row>
              <span style="font-size: 18px; line-height: 36px">人员</span>
            </el-row>
          </el-col>
          <el-col :span="22">
            <el-form :inline="true" :model="formInfo" style="float: right">
              <el-form-item prop="loginNameTxt" label="姓名">
                <el-input v-model="formInfo.userNameTxt" class="font"></el-input>
              </el-form-item>
              <el-form-item prop="userNameTxt" label="用户名">
                <el-input v-model="formInfo.loginNameTxt" class="font"></el-input>
              </el-form-item>
              <el-form-item prop="posNameTxt" label="岗位">
                <el-input v-model="formInfo.posNameTxt" class="font"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" class="" @click="listUser()" >查询</el-button>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" class="" @click="addUser()" >新增</el-button>
              </el-form-item>
              <el-form-item>
                <el-upload
                  class="upload-demo"
                  :action="userImportUrl"
                  :file-list="fileList"
                  :on-success="userImportHandler"
                  :before-upload="beforeExcelUpload"
                  :show-file-list="false">
                  <el-button type="primary" class="">导入</el-button>
                </el-upload>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" class="" @click="exportExcel()" >导出</el-button>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
        <div class="clear"></div>
        <el-row>
          <hr style="color: #c7c5c5;margin-top: -15px"/>
        </el-row>
        <el-row>
          <router-view class="content"></router-view>
          <el-col :span="5">
            <div :style="{height: tableHeightDynamic - 2 + 'px'}" class="grid-content bg-purple tree-content" style="border-right: 0px; background-color: #FFFFFF; margin-top: 5px; overflow: hidden">
              <div class="tree-top">
                <span>组织结构</span>
              </div>
              <deptTree v-on:nodeClick="nodeTreeData" ></deptTree>
            </div>
          </el-col>
          <el-col :span="19">
            <div style="text-align: left; margin-top: 5px;">
              <div class="block">
                <el-table :data="userList" stripe border :height="tableHeightDynamic">
                  <el-table-column prop="userName" label="姓名"> </el-table-column>
                  <el-table-column prop="deptName" label="部门" > </el-table-column>
                  <el-table-column prop="posName" label="岗位" show-overflow-tooltip> </el-table-column>
                  <el-table-column prop="roleName" label="角色" width="70" show-overflow-tooltip> </el-table-column>
                  <el-table-column prop="loginName" label="用户名"> </el-table-column>
                  <el-table-column prop="phone" label="手机号码"> </el-table-column>
                  <el-table-column label="操作" width="200" >
                    <template slot-scope="scope">
                      <el-button size="small" type="text" @click="handleResetPassword(scope.row, 'resetPwd')">重置密码</el-button>
                      <el-button size="small" type="text" @click="handleView(scope.row)">查看</el-button>
                      <el-button size="small" type="text" @click="handleEdit(scope.row)">编辑</el-button>
                      <el-button size="small" type="text" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </div>
          </el-col>
        </el-row>
        <div class="block" style="float: left">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentpage"
            :page-sizes="pagesizes"
            :page-size="pagesize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="userCount">
          </el-pagination>
        </div>
      </el-col>
      <el-dialog class="user" :visible.sync="dialogVisible" size="small" :title="slot.title" :close-on-press-escape="false" :close-on-click-modal="false">
        <div>
          <el-row>
            <el-col :span="12">
                <el-form ref="user1" :inline="true" :rules="rulesValidateUser" :model="user">
                  <el-form-item label="用户名" prop="loginName"
                  :rules="[{required: true, trigger: 'blur,change', validator: loginNameCheck}]">
                    <el-input v-model="user.loginName" placeholder="用户名" class="font" :disabled="edit === false"></el-input>
                  </el-form-item>
                  <el-form-item label="姓名" prop="userName"
                  :rules="[{required: true, trigger: 'blur,change', validator: userNameCheck}]">
                    <el-input v-model="user.userName" placeholder="姓名" class="font" :disabled="edit === false"></el-input>
                  </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="12">
                <el-form ref="user2" :inline="true" :rules="rulesValidateUser" :model="user">
                  <el-form-item label="密码" prop="password">
                    <el-input v-model="user.password" placeholder="密码" type="password" class="font" :disabled="save === false"></el-input>
                  </el-form-item>
                  <el-form-item label="部门" prop="depId">
                    <deptSelectTree 
                    @nodeSelect="nodeClickData"
                    :select-id="user.depId"
                    v-model="user.depId"
                    :treeDisable="false" :inputReadOnly="true" v-if="edit === true"></deptSelectTree>
                    <deptSelectTree 
                    @nodeSelect="nodeClickData"
                    :select-id="user.depId"
                    v-model="user.depId"
                    :treeDisable="true" :inputReadOnly="true" v-else></deptSelectTree>
                  </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="12">
                <el-form ref="user3" :inline="true" :rules="rulesValidateUser" :model="user">
                  <el-form-item label="角色" prop="role">
                    <el-select v-model="defaultRole" multiple placeholder="请选择" :disabled="edit === false">
                      <el-option
                        v-for="item in totalUserRoleList"
                        :key="item.roleId"
                        :label="item.roleName"
                        :value="item.roleId">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="手机号码" prop="phone" :rules="[{required: false, trigger: 'change', validator: checkPhone}]">
                    <el-input v-model="user.phone" class="font" placeholder="手机号码" :disabled="edit === false"></el-input>
                  </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="12">
                <el-form ref="user4" :inline="true" :rules="rulesValidateUser" :model="user">
                  <el-form-item label="岗位">
                    <el-select placeholder='请选择' v-model='user.posId' :disabled="edit === false">
                      <el-option v-for="item in positions" 
                        :key="item.posId" 
                        :value="item.posId" 
                        :label="item.posName + item.posType"
                        :disabled="!item.enable">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="备注">
                    <el-input v-model="user.remark" class="font" :disabled="edit === false"></el-input>
                  </el-form-item>
                </el-form>
            </el-col>
          </el-row>
          <div slot="footer" class="dialog-footer user" style = "text-align: right;">
            <el-button type="primary" style="width: 98px;height: 40px" @click="submitForm('user1', 'user2', 'user3', 'user4')" v-if="edit === true">确认</el-button>
            <el-button type="primary" style="width: 98px;height: 40px" @click="dialogVisible = false">取消</el-button>
          </div>
        </div>
      </el-dialog>
      <el-dialog size="tiny" title="删除" :visible.sync="dialogDelFormVisible" class="del-dialog" :close-on-press-escape="false" :close-on-click-modal="false">
          <span>确定删除人员：{{deleteUserName}}吗？</span>
          <div slot="footer" class="dialog-footer">
              <el-button type="primary" @click="deleteUser()">确 定</el-button>
              <el-button type="primary" @click="dialogDelFormVisible = false">取 消</el-button>
          </div>
      </el-dialog>
      <el-dialog title="重置密码" :visible.sync="dialogResetPasswordVisible" class="common-dialog" :close-on-press-escape="false" :close-on-click-modal="false">
        <el-form :inline="true" ref="resetPwd" :rules="rulesResetPwdValidate" label-position="right" :model="resetPasswordForm">
          <el-form-item label="新密码" prop="newPwd">
            <el-input type="password" v-model="resetPasswordForm.newPwd"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="confirmPwd">
            <el-input type="password" v-model="resetPasswordForm.confirmPwd"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="resetPassword('resetPwd')">确 定</el-button>
          <el-button type="primary" @click="dialogResetPasswordVisible = false">取 消</el-button>
        </div>
      </el-dialog>
      <el-dialog size="tiny" title="错误信息提示" :visible.sync="errInfoForm.dialogVisible" :close-on-press-escape="false" :close-on-click-modal="false">
        <p>{{errInfoForm.errInfo.mesg + errInfoForm.errInfo.errIn}}</p>
        <p>{{errInfoForm.errInfo.message + errInfoForm.errInfo.errInfo}}</p>
        <p>{{errInfoForm.errInfo.msg + errInfoForm.errInfo.erIn}}</p>
        <p>{{errInfoForm.errInfo.mg + errInfoForm.errInfo.eInfo}}</p>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="errInfoForm.dialogVisible = false">关 闭</el-button>
        </div>
      </el-dialog>
    </div>
</template>
<script>
  import sideBar from '../../components/base/zhhdSideBars.vue'
  import deptTree from '@/components/deptTree'
  import deptSelectTree from '@/components/deptSelectTree'
  import { mapState } from 'vuex'
  import axios from 'axios'

  export default {
    name: 'user',
    data () {
      const validateConfirmPwd = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'))
        } else if (value !== this.resetPasswordForm.newPwd) {
          callback(new Error('两次输入密码不一致!'))
        } else {
          callback()
        }
      }
      const password = (rule, value, callback) => {
        let temp = value.toString()
        if (temp === '' || temp === null) {
          callback('不能为空')
        } else if (temp.indexOf(' ') >= 0) {
          callback('输入字符中不能有空格')
        } else {
          callback()
        }
      }
      return {
        tableHeightDynamic: document.body.clientHeight - window.config.topBarHeight - 170,
        bitrhday: {
          disabledDate (time) {
            return time.getTime() > Date.now()
          }
        },
        msg: '人员管理',
        pagesizes: [20, 30, 50],
        pagesize: 20,
        currentpage: 1,
        slot: { title: '' },
        dialogVisible: false,
        dialogDelFormVisible: false,
        dialogResetPasswordVisible: false,
        deleteUserName: '',
        deleteUserId: '',
        resetPwdUserId: '',
        userImageUploadUrl: axios.defaults.fileUploadUrl + 'file/upload',
        userImportUrl: axios.defaults.baseURL + 'sys/user/importUser',
        fileList: [],
        imageUrl: '',
        false: false,
        rulesValidateUser: {
          loginName: [{ required: true, message: '用户名', trigger: 'blur' }],
          userName: [{ required: true, message: '姓名', trigger: 'blur' }],
          password: [{ required: true, validator: password, trigger: 'blur' }],
          depId: [{ required: true, message: '部门', trigger: 'change' }]
        },
        rulesResetPwdValidate: {
          newPwd: [{ required: true, message: '新密码', trigger: 'blur' }],
          confirmPwd: [{ required: true, validator: validateConfirmPwd, trigger: 'blur' }]
        },
        resetPasswordForm: {
          newPwd: '',
          confirmPwd: ''
        },
        formInfo: {
          deptIdTxt: '',
          loginNameTxt: '',
          userNameTxt: '',
          posNameTxt: ''
        },
        errInfoForm: {
          errInfo: {
            msg: '',
            mesg: '',
            message: '',
            errIn: '',
            errInfo: '',
            erIn: '',
            eInfo: ''
          },
          dialogVisible: false
        },
        status: [{
          label: '启用',
          value: 1
        }, {
          label: '禁用',
          value: 0
        }],
        defaultBirthday: new Date(),
        positions: [],
        defaultStatus: {label: '启用', value: 1},
        defaultRole: [],
        roles: [],
        save: true,
        edit: true,
        user: {
          id: '',
          userId: '',
          posId: '',
          roleId: '',
          depId: '',
          loginName: '',
          password: '',
          userName: '',
          education: '',
          sex: '',
          politicalStatus: '',
          tel: '',
          phone: '',
          certificateType: '',
          address: '',
          certificateNum: '',
          email: '',
          birthday: '',
          homeTel: '',
          nation: '',
          school: '',
          userImages: '',
          posName: '',
          deptName: '',
          interphone: '',
          policeNum: '',
          remark: '',
          roleName: '',
          enable: ''
        }
      }
    },
    components: {
      sideBar, deptTree, deptSelectTree
    },
    created () {
      this.listUser()
    },
    computed: {
      ...mapState({
        userList: state => state.user.userList,
        userCount: state => state.user.userCount,
        positionsList: state => state.position.positionPage.positionList,
        totalUserRoleList: state => state.role.roleList,
        politicalStatuss: state => state.user.politicalTypes,
        certificateTypes: state => state.user.certificateTypes,
        educationLevel: state => state.user.eduTypes,
        sexs: state => state.user.sexTypes
      })
    },
    methods: {
      loginNameCheck (rules, value, callback) {
        this.$store.dispatch('checkRequiredAndSpace', {rules, value, callback})
      },
      userNameCheck (rules, value, callback) {
        this.$store.dispatch('checkRequiredAndSpace', {rules, value, callback})
      },
      checkPhone (rules, value, callback) {
        let temp = null
        if (value !== null) {
          temp = value
        }
        if (temp === '' || temp === null) {
          callback()
        }
        let reg = /^1[0-9]{10}$/
        if (reg.test(temp)) {
          callback()
        } else {
          callback(new Error('手机格式不正确'))
        }
      },
      submitForm (form1, form2, form3, form4) {
        let save = true
        this.$refs[form1].validate((valid) => {
          if (!valid) {
            save = false
          }
        })
        this.$refs[form2].validate((valid) => {
          if (!valid) {
            save = false
          }
        })
        this.$refs[form3].validate((valid) => {
          if (!valid) {
            save = false
          }
        })
        this.$refs[form4].validate((valid) => {
          if (!valid) {
            save = false
          }
        })
        if (save) {
          this.saveUser()
        } else {
          this.$message({
            message: '输入数据不正确！',
            type: 'warning'
          })
          return false
        }
      },
      resetForm (form1, form2, form3) {
        this.$refs[form1].resetFields()
        this.$refs[form2].resetFields()
        this.$refs[form3].resetFields()
        this.imageUrl = ''
      },
      handleResetPassword (row, form) {
        this.dialogResetPasswordVisible = true
        this.resetPwdUserId = row.userId
        if (this.$refs['resetPwd']) {
          this.$refs['resetPwd'].resetFields()
        }
      },
      resetPassword (form) {
        this.$refs[form].validate((valid) => {
          if (valid) {
            return this.$store.dispatch('resetPass', {
              userId: this.resetPwdUserId,
              newPwd: this.resetPasswordForm.newPwd
            }).then((reply) => {
              this.$message({message: '重置密码成功', type: 'success'})
              this.dialogResetPasswordVisible = false
            })
          }
        })
      },
      beforeExcelUpload (file) {
        // xls 或 xlsx 类型判断
        let isExcel = file.type === 'application/vnd.ms-excel' || file.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
        if (!isExcel) {
          this.$message.error('上传文件只能是xls或xlsx格式!')
        }
        return isExcel
      },
      userImportHandler (response, file, fileList) {
        let resTemp = response.split('，')
        let deptError = ''
        let emptyError = ''
        let loginError = ''
        let posError = ''
        for (let i = 0; i < resTemp.length; i++) {
          if (resTemp[i].indexOf('e') === 0) {
            emptyError = resTemp[i].split('[')[1].split(']')[0]
          }
          if (resTemp[i].indexOf('l') === 0) {
            loginError = resTemp[i].split('[')[1].split(']')[0]
          }
          if (resTemp[i].indexOf('d') === 0) {
            deptError = resTemp[i].split('[')[1].split(']')[0]
          }
          if (resTemp[i].indexOf('p') === 0) {
            posError = resTemp[i].split('[')[1].split(']')[0]
          }
        }
        if (resTemp[0] === 200 || resTemp[0] === '200') {
          this.$message({message: '导入人员成功', type: 'success'})
        } else if (resTemp[0] === 504 || resTemp[0] === '504') {
          this.errInfoForm.errInfo.msg = ''
          this.errInfoForm.errInfo.mesg = ''
          this.errInfoForm.errInfo.message = ''
          this.errInfoForm.errInfo.mg = ''
          this.errInfoForm.dialogVisible = true
          this.errInfoForm.errInfo.errIn = '，导入人员失败！'
          this.errInfoForm.errInfo.errInfo = '，导入人员失败！'
          this.errInfoForm.errInfo.erIn = '，导入人员失败！'
          this.errInfoForm.errInfo.eInfo = '，导入人员失败！'
          if (emptyError !== '') {
            emptyError = '导入数据中第' + emptyError + '行数据存在用户名、姓名或部门为空 '
            this.errInfoForm.errInfo.mesg = emptyError + this.errInfoForm.errInfo.mesg
          }
          if (loginError !== '') {
            loginError = '导入数据中第' + loginError + '行数据用户名已存在 '
            this.errInfoForm.errInfo.message = loginError + this.errInfoForm.errInfo.message
          }
          if (deptError !== '') {
            deptError = '导入数据中第' + deptError + '行数据部门不存在 '
            this.errInfoForm.errInfo.msg = deptError + this.errInfoForm.errInfo.msg
          }
          if (posError !== '') {
            posError = '导入数据中第' + posError + '行数据岗位不存在 '
            this.errInfoForm.errInfo.mg = posError + this.errInfoForm.errInfo.mg
          }
          if (this.errInfoForm.errInfo.mesg === '') {
            this.errInfoForm.errInfo.errIn = ''
          }
          if (this.errInfoForm.errInfo.message === '') {
            this.errInfoForm.errInfo.errInfo = ''
          }
          if (this.errInfoForm.errInfo.msg === '') {
            this.errInfoForm.errInfo.erIn = ''
          }
          if (this.errInfoForm.errInfo.mg === '') {
            this.errInfoForm.errInfo.eInfo = ''
          }
        } else {
          this.$message({message: '导入人员失败', type: 'error'})
        }
        this.listUser()
      },
      handleAvatarSuccess (response, file, fileList) {
        this.user.userImages = response
        this.imageUrl = axios.defaults.fileLoadUrl + response
      },
      beforeUserImageUpload (file) {
        let isImage = file.type === 'image/jpeg' || file.type === 'image/png'
        if (!isImage) {
          this.$message.error('上传头像图片只能是png或jpg格式!')
        }
        return isImage
      },
      handleSizeChange (val) {
        this.pagesize = val
        this.listUser()
      },
      handleCurrentChange (val) {
        this.currentpage = val
        this.listUser()
      },
      nodeTreeData (data) {
        this.formInfo.deptIdTxt = data.id
        this.$emit('nodeTreeData', data)
        this.listUser()
      },
      listUser () {
        return this.$store.dispatch('listUserByParams', {
          pageIndex: this.currentpage,
          pageSize: this.pagesize,
          deptId: this.formInfo.deptIdTxt,
          loginName: this.formInfo.loginNameTxt,
          userName: this.formInfo.userNameTxt,
          posName: this.formInfo.posNameTxt
        })
      },
      addUser () {
        this.clearProperties(this.user)
        this.user.education = '0'
        this.user.sex = '0'
        this.user.politicalStatus = '0'
        this.user.certificateType = '0'
        this.save = true
        this.edit = true
        this.slot.title = '新增人员'
        this.dialogVisible = true
        this.initUserInfo({userId: '', id: ''})
        if (this.$refs['user1']) {
          this.$refs['user1'].resetFields()
        }
        if (this.$refs['user2']) {
          this.$refs['user2'].resetFields()
        }
        if (this.$refs['user3']) {
          this.$refs['user3'].resetFields()
        }
        if (this.$refs['user4']) {
          this.$refs['user4'].resetFields()
        }
      },
      exportExcel () {
        window.location = axios.defaults.baseURL + 'sys/user/exportUser?pageSize=' + this.pagesize + '&pageIndex=' + this.currentpage + '&userName=' + encodeURI(this.formInfo.userNameTxt) + '&loginName=' + encodeURI(this.formInfo.loginNameTxt) + '&posName=' + encodeURI(this.formInfo.posNameTxt)
      },
      handleView (row) {
        this.dialogVisible = true
        this.slot.title = '查看人员'
        if (this.$refs['user1']) {
          this.$refs['user1'].resetFields()
        }
        if (this.$refs['user2']) {
          this.$refs['user2'].resetFields()
        }
        if (this.$refs['user4']) {
          this.$refs['user4'].resetFields()
        }
        this.initUserInfo({ userId: row.userId, id: row.id, operate: 'view' })
      },
      handleEdit (row) {
        this.dialogVisible = true
        this.slot.title = '编辑人员'
        if (this.$refs['user1']) {
          this.$refs['user1'].resetFields()
        }
        if (this.$refs['user2']) {
          this.$refs['user2'].resetFields()
        }
        if (this.$refs['user4']) {
          this.$refs['user4'].resetFields()
        }
        this.initUserInfo({ userId: row.userId, id: row.id, operate: 'edit' })
      },
      deleteUser () {
        return this.$store.dispatch('delUser', { userId: this.deleteUserId }).then((reply) => {
          this.$message({message: '删除成功', type: 'success'})
          this.dialogDelFormVisible = false
          this.listUser()
        })
      },
      handleDelete (row) {
        this.dialogDelFormVisible = true
        this.deleteUserId = row.userId
        this.deleteUserName = row.userName
      },
      nodeClickData (data) {
        this.user.depId = data.id
      },
      async initUserInfo (param) {
        this.roles = []
        this.defaultRole = []
        this.listRole()
        this.initEnumTypes()
        if (param.userId && param.id) {
          this.msg = '编辑人员'
          this.user.userId = param.userId
          this.user.id = param.id
          this.save = false
          if (param.operate === 'edit') {
            this.edit = true
          } else {
            this.edit = false
          }
          this.initUser()
        }
        await this.listPosition()
        await this.initPosition()
      },
      listPosition () {
        return this.$store.dispatch('queryAllPosition')
      },
      initPosition () {
        this.positionsList.forEach(position => {
          if (position.posType === '0') {
            position.posType = '(全局)'
          } else if (position.posType === '1') {
            position.posType = '(部门)'
          } else if (position.posType === '2') {
            position.posType = '(个人)'
          } else if (position.posType === '9') {
            position.posType = '(其他)'
          }
          this.pushPosition(position, this.positions)
        })
      },
      pushPosition (src, dest) {
        let exist = false
        for (let i = 0; i < dest.length; i++) {
          if (src.posId === dest[i].posId) {
            exist = true
          }
        }
        if (!exist) {
          dest.push(src)
        }
      },
      async listRole () {
        this.$store.dispatch('searchAllRole')
      },
      initEnumTypes () {
        this.initPoliticalTypes()
        this.initCertificateTypes()
        this.initEduTypes()
        this.initSexTypes()
      },
      async initPoliticalTypes () {
        await this.$store.dispatch('getPoliticalTypes')
      },
      async initCertificateTypes () {
        await this.$store.dispatch('getCertificateTypes')
      },
      async initEduTypes () {
        await this.$store.dispatch('getEduTypes')
      },
      async initSexTypes () {
        await this.$store.dispatch('getSexTypes')
      },
      initUser () {
        return this.$store.dispatch('getUser', {
          userId: this.user.userId
        }).then((reply) => {
          this.user = reply
          this.defaultBirthday = reply.birthday
          this.defaultStatus = this.user.enable === 0 ? {label: '禁用', value: 0} : {label: '启用', value: 1}
          this.imageUrl = (reply.userImages === axios.defaults.fileLoadUrl + 'null' || reply.userImages === axios.defaults.fileLoadUrl) ? '' : reply.userImages
          let roles = reply.userRoleList
          roles.forEach(role => {
            for (let j = 0; j < this.totalUserRoleList.length; j++) {
              if (role.roleId === this.totalUserRoleList[j].roleId) {
                this.defaultRole.push(this.totalUserRoleList[j].roleId)
              }
            }
          })
        })
      },
      saveUser () {
        this.dialogVisible = false
        let roles = []
        this.defaultRole.forEach(index => {
          this.totalUserRoleList.forEach(userRole => {
            if (userRole.roleId === index) {
              roles.push(userRole)
            }
          })
        })
        this.user.birthday = this.defaultBirthday
        this.user.userRoleList = roles
        this.user.enable = this.defaultStatus.value
        if (this.user.userId !== '' && this.user.userId !== undefined && this.user.userId !== null) {
          return this.$store.dispatch('updateUser', this.user).then((reply) => {
            this.dialogVisible = false
            this.$message({message: '更新成功', type: 'success'})
            this.listUser()
          })
        } else {
          let params = this.user
          params.birthday = this.defaultBirthday
          params.enable = this.defaultStatus.value
          params.userRoleList = roles
          delete params.userId
          return this.$store.dispatch('addUser', params).then((reply) => {
            this.$message({message: '保存成功', type: 'success'})
            this.dialogVisible = false
            this.listUser()
          })
        }
      }
    }
  }
</script>

<style>
  .userView .el-input {
    width: 180px;
  }
  .userView .el-input__inner {
    width: 180px;
  }
</style>
<style scoped>
  .user .el-button {
    font-size: 15px;
  }
  .user .el-button+.el-button {
    margin-left: 10px;
    margin-right: 34px;
  }
</style>

