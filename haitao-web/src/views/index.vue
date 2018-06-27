<template>
  <div>
    <el-popover ref="popover1" placement="bottom" width="110" trigger="click">
      <el-button type="primary" size="mini" @click="openUpdPwd()">修改密码</el-button>
      <el-button type="primary" size="mini" @click="openLogout()">退出登录</el-button>
    </el-popover>
    <!-- <div class="index__conner" :style="{height: height + 'px', lineHeight: height + 'px'}">
      Bonjour, {{userName}}
    </div> -->
    <div class="index__conner" :style="{height: height + 'px', lineHeight: height + 'px'}">
      <el-button class="user__img" size="small" v-popover:popover1>
        <img src="../assets/img/zhhd/user.png">
      </el-button>
    </div>
    <el-dialog size="small" title="修改密码" :visible.sync="dialogUpdPwd">
      <el-form :model="user" ref="user" :rules="rulesPwd">
        <el-form-item label="原密码" prop='oldPwd' :label-width="formLabelWidth">
          <el-input v-model="user.oldPwd" auto-complete="off" class="dialog-input-wid"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop='newPwd' :label-width="formLabelWidth">
          <el-input type="password" v-model="user.newPwd" auto-complete="off" class="dialog-input-wid"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop='confirmPwd' :label-width="formLabelWidth">
          <el-input type="password" v-model="user.confirmPwd" auto-complete="off" class="dialog-input-wid"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogUpdPwd = false">取 消</el-button>
        <el-button type="primary" :disabled="updatePwdBtn" @click="updatePwd('user')">确 定</el-button>
      </div>
    </el-dialog>
    <zhhdTopBar></zhhdTopBar>
    <router-view></router-view>
  </div>
</template>
<script>
/* eslint-disable */
import zhhdTopBar from '../components/base/zhhdTopBar.vue'
import { mapState } from 'vuex'

export default {
  created() {
    let params = this.$route.params
    this.userName = params.userName || this.getcookie('userName')
  },
  computed: {
    ...mapState({
      userInfo: state => state.login.user,
      top_topList1: state => state.moudle.topList1
    })
  },
  data() {
    const validateConfirmPwd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.user.newPwd) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      msg: '主体页面',
      userName: '',
      tabId: '',
      dialogUpdPwd: false,
      updatePwdBtn: false,
      user: {
        oldPwd: '',
        newPwd: '',
        confirmPwd: ''
      },
      rulesPwd: {
        oldPwd: [
          { required: true, message: '请输入原密码', trigger: 'blur' }
        ],
        newPwd: [
          { required: true, message: '请输入新密码', trigger: 'blur' }
        ],
        confirmPwd: [
          { validator: validateConfirmPwd, trigger: 'blur' }
        ]
      },
      formLabelWidth: '120px',
      height: config.topBarHeight
    }
  },
  methods: {
    openUpdPwd() {
      this.dialogUpdPwd = true
      this.user = {
        oldPwd: '',
        newPwd: '',
        confirmPwd: ''
      }
    },
    updatePwd(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.changePass()
        } else {
          return false
        }
      })
    },
    openLogout() {
      this.$confirm('确定退出登录?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.logout()
      }).catch(() => {
      })
    },
    changePass() {
      return this.$store.dispatch('updateLogin', { userId: this.userInfo.userId, oldPassword: this.user.oldPwd, newPassword: this.user.newPwd })
        .then((res) => {
          this.savecookie('userName', '')
          this.$router.push('/')
          this.dialogUpdPwd = false
        })
        .catch((err) => {
          this.$message({
            message: '更新密码失败:' + err,
            type: 'error'
          })
        })
    },
    logout() {
      this.$store.dispatch('logoutSystem')
        .then((res) => {
          this.savecookie('userName', '')
          this.savecookie('zhhdModel', '')
          this.$router.push('/')
        })
    }
  },
  components: {
    'zhhdTopBar': zhhdTopBar
  }
}
</script>

<style scoped>
.index__conner {
  position: relative;
  z-index: 99;
  float: right;
  color: white;
  margin-right: 5px;
  overflow: hidden;
}
</style>

<style>
.user__img.el-button {
  margin: 5px 25px 0px 0px;
  background: rgba(255, 255, 255, 0);
  border: 0px;
}
</style>