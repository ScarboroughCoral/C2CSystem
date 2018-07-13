<template>
  <div class="login-container">
    <el-form class="login-form" autoComplete="on" :model="loginForm" :rules="loginRules" ref="loginForm" label-position="left">
      <h3 class="title">C2C短租平台</h3>
      <el-form-item prop="username">
        <span class="svg-container svg-container_login">
          <svg-icon icon-class="user" />
        </span>
        <el-input name="loginName" type="text" v-model="loginForm.loginName" autoComplete="on" placeholder="loginName" />
      </el-form-item>
      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password"></svg-icon>
        </span>
        <el-input name="password" :type="pwdType" @keyup.enter.native="handleLogin" v-model="loginForm.password" autoComplete="on"
          placeholder="password"></el-input>
          <span class="show-pwd" @click="showPwd"><svg-icon icon-class="eye" /></span>
      </el-form-item>
      <div style="display:flex;flex-direction:row-reverse">
        <el-button type="primary"  style="margin-left:20px" :loading="loading" @click.native.prevent="handleLogin">
          登录
        </el-button>
        <el-button type="success"  :loading="loading" @click="handleRegist">
          注册
        </el-button>
      </div>
      <div class="tips">
        <span style="margin-right:20px;">loginName: 用户名</span>
        <span> password: 密码</span>
      </div>
    </el-form>
    <el-dialog style="z-index:10" title="注册" :visible.sync="dialogFormVisible">
      <el-form label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="昵称">
          <el-input v-model="registForm.nickname"></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="registForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input name="password"  v-model="registForm.password" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button  type="primary" @click="register">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { isvalidUsername } from '@/utils/validate'
import { regist,login } from '@/api/login'

import Cookies from "js-cookie";

export default {
  name: 'login',
  data() {
    const validateUsername = (rule, value, callback) => {

      callback()
    }
    const validatePass = (rule, value, callback) => {
      if (value.length < 5) {
        callback(new Error('密码不能小于5位'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        loginName: '',
        password: ''
      },
      loginRules: {
        // username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        loginName: [{ required: true, trigger: 'blur' }],
        password: [{ required: true, trigger: 'blur', validator: validatePass }]
      },
      loading: false,
      pwdType: 'password',
      dialogFormVisible:false,
      registForm:{
        username:'',
        password:'',
        nickname:''
      }
    }
  },
  methods: {
    showPwd() {
      if (this.pwdType === 'password') {
        this.pwdType = ''
      } else {
        this.pwdType = 'password'
      }
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('Login', this.loginForm).then(() => {
            this.loading = false
            this.$router.push({ path: '/' })
          }).catch(()=>{
            this.loading = false
          })
        }
      })
    },
    handleRegist(){
      this.dialogFormVisible = true
    },
    register(){
      regist(this.registForm).then(response => {
        if (response.success) {
          this.$message.success(response.message)
          this.dialogFormVisible = false;
          this.loginForm.loginName = this.registForm.username;
          this.loginForm.password = this.registForm.password;
        }else{
          this.$message.error(response.message)
        }
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
$bg:#2d3a4b;
$light_gray:#eee;

/* reset element-ui css */
.login-container {
  .login-form{
    .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      &:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: #fff !important;
      }
    }
  }
  }
}

</style>

<style rel="stylesheet/scss" lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;
.login-container {
  height: 100%;
  width: 100%;
  background-color: $bg;
  .login-form {
    position: absolute;
    left: 0;
    right: 0;
    width: 520px;
    padding: 35px 35px 15px 35px;
    margin: 120px auto;

    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
  }
  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;
    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }
  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
    &_login {
      font-size: 20px;
    }
  }
  .title {
    font-size: 26px;
    font-weight: 400;
    color: $light_gray;
    margin: 0px auto 40px auto;
    text-align: center;
    font-weight: bold;
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
