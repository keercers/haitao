<template>
  <div class="login-bg">
    <img class="login-logo" src="./../assets/img/login/login-logo.png" style="margin-top: 9%; text-align: center; width: 840px; height:64px;">

    <div class="outter">
      <div class="inner">
        <div class="login" @keyup.enter="submitForm('userMes')">
          <el-form class="form-login" :label-position="labelPosition" :rules="rulesLogin" :model="userMes" ref="userMes">
            <el-form-item class="usr__name" prop="userName">
              <div class="line-p"></div>
              <el-input class="login-input" v-model="userMes.userName" auto-complete="off">
                <template slot="prepend">用户名</template>
              </el-input>
            </el-form-item>
            <el-form-item class="ps__word" prop="password">
              <div class="line-p"></div>
              <el-input class="el-input-password login-input" type="password" v-model="userMes.password" auto-complete="off">
                <template slot="prepend">密码</template>
              </el-input>
            </el-form-item>
            <el-form-item class="log__btn">
              <el-button class="login-button" type="primary" @click="submitForm('userMes')">登录</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>

    <div class="copyright">CopyRight ©2018 All rights reserved 上海思创华信信息技术有限公司版权所有</div>
  </div>
</template>

<script>
  export default {
    name: 'login',
    data () {
      return {
        msg: '登录页面',
        labelPosition: 'center',
        rulesLogin: {
          userName: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
          password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
        },
        userMes: {
          userName: '',
          password: '',
          code: ''
        },
        checkPwd: ''
      }
    },
    created () {
      this.getData()
    },
    methods: {
      getData () {
        // this.$store.dispatch('queryTopList', '1')
      },
      resetForm (formName) {
        this.$refs[formName].resetFields()
      },
      submitForm (formName) {
        this.savecookie('userName', '')
        this.savecookie('zhhdModel', '')
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // 密码加密功能
            // 数据请求
            this.login()
          } else {
            return false
          }
        })
      },
      login () {
        return this.$store.dispatch('loginSystem', {password: this.userMes.password, loginName: this.userMes.userName})
          .then((res) => {
            this.savecookie('userName', this.userMes.userName, 1)
            this.savecookie('基础设置', this.zhhdModel, 1)
            this.$router.push('/sys')
          })
          .catch(_ => {})
      }
    }
  }
</script>

<style>
.outter {
  width: 450px;
  height: 300px;
  margin: 116px auto auto auto;
  background-color: rgba(184, 232, 255, 0.5);
  border: 0px;
  border-radius: 10px;
  z-index: 0;
}
.inner {
  position: relative;
  width: 420px;
  height: 270px;
  top: 15px;
  left: 15px;
  background-color: rgba(184, 232, 255, 0.7);
  border: 0px;
  border-radius: 10px;
  z-index: 1;
}

.login {
  margin: 3% auto 0;
  width: 300px;
  top: 55px;
  position: relative;
}

.login .el-form-item {
  margin-bottom: 0px;
}

.login .el-form-item__error {
  color: #ff3333;
  font-size: 14px;
}
.login .el-input-group__prepend {
  width: 80px;
  text-align: center;
}
.login .el-input-group__prepend:after {
  content: '';
  /* border-right: 1px solid #5d697a; */
  position: absolute;
  height: 27px;
  right: 0;
  top: 13.5px;
}
.line-p {
  background-color: #5d697a;
  width: 1px;
  height: 20px;
  position: relative;
  left: 100px;
  top: 28px;
  z-index: 1999;
}
.login .el-input-group__prepend {
  font-size: 16px;
}
.login-input {
  width: 100%!important;
}
.login-input .el-input__inner {
  padding: 0px 0px 0px 12px;
  border-left: 0px;
  border-radius: 4px;
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
  font-size: 16px;
}

.form-login {
  position: relative;
  padding: 0px 0px 0px 0px;
  /* padding-bottom: 35px; */
  bottom: 11px;
}
.usr__name {
  height: 36px;
  padding: 0px 0px 20px 0px;
}
.usr__name>.el-form-item__content {
  height: 36px;
}
.login-input {
  font-size: 16px;
}
.ps__word {
  height: 36px;
  padding: 0px 0px 40px 0px;
}
.login .el-input-password {
  margin-top: 0px;
}
.login-button.el-button--primary {
  background-color: #1D7FFF;
  border-color: #1D7FFF;
  font-size: 18px;
  width: 300px;
  border-radius: 4px;
}
.login-button.el-button--primary:hover {
  background-color: #084FFF;
  border-color: #084FFF;
}

</style>
