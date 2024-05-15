<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus'
import 'element-plus/theme-chalk/el-message.css'
import { useRouter } from 'vue-router';
import { useUserStore } from '@/stores/user';
import LayoutFooter from '../Layout/components/LayoutFooter.vue';

const form = ref({
  username:'visitorAccount',
  password:'welcometocave',
  agree: true
})
const rules = {
  username:[
    { required:true, message:'账户名不能为空' },
    { pattern: /^[a-zA-Z0-9_.]+$/, message:'账号只能包含数字、字母和下划线' }
  ],
  password:[
    { required:true, message:'密码不能为空',  },
    { min:6, max:14, message:'密码长度在6-14之间',  },
    { pattern: /^[a-zA-Z0-9_.]+$/, message:'密码只能包含数字、字母和下划线' }
  ],
  agree:[{
    validator:(rule, value, callback) => {
      if(value){
        callback()
      }else{
        callback(new Error('请勾选协议'))
      }
    }
  }
  ]
}
const formRef = ref(null)
const router = useRouter()
const userStore = useUserStore()
const doLogin = ()=>{
  const { username,password } = form.value
  formRef.value.validate(async (valid)=>{
    if(valid){
      await userStore.getUserInfo({ username,password })
      ElMessage({type:'success', message:'登陆成功'})
      router.replace({path:'/'})
    }
  })
}
</script>


<template>
  <div class="mainContainer">
    <header class="login-header">
      <div class="container">
        
        <RouterLink class="entry" to="/">
          进入网站首页
        </RouterLink>
      </div>
      <div class="logo">
        <i class="cave">CA</i><div class="mid"><i class="from">from</i><i class="escape">escape</i></div><i class="cave">VE</i>
      </div>
    </header>
    <div class="mainContainer">
      <section class="login-section">
        <div class="wrapper">
          <nav>
            <a href="javascript:;">账户登录</a>
          </nav>
          <div class="account-box">
            <div class="form">
              <el-form ref="formRef" :model="form" :rules="rules" label-position="right" label-width="80px"
                status-icon>
                <el-form-item prop="username" label="账户">
                  <el-input v-model="form.username"/>
                </el-form-item>
                <el-form-item prop="password" label="密码">
                  <el-input v-model="form.password"/>
                </el-form-item>
                <el-form-item  prop="agree" label-width="22px">
                  <el-checkbox  size="large" v-model="form.agree">
                    我已同意隐私条款和服务条款
                  </el-checkbox>
                </el-form-item>
                <el-button @click="doLogin" size="large" class="subBtn">点击登录</el-button>
              </el-form>
            </div>
          </div>
        </div>
      </section>
    </div>
  <div class="footer">
    <layout-footer/>
  </div>
</div>
</template>

<style scoped lang='scss'>
.mainContainer{
  height: 100%; 
  width: 100%; 
  display: flex ; 
  flex-direction: column;
  background: url('https://img2.imgtp.com/2024/05/13/FdsxNYaO.jpg') no-repeat center / cover;
}

.login-header {
  height: 100px;
  background: rgb(255, 255, 255,0.6);
  border-bottom: 1px solid #e4e4e4;
  position: relative;
  .logo{
      position: absolute;
      top: 20px;
      left: 10px;
      height: fit-content;
      display: flex;
      margin-left: 3%;
      margin-right: auto;
      .mid{
        display: flex;
        flex-direction: column;
        text-align: center;
        margin-top: 10px;
        .from{
          font-size: 20px;
          font-family: 'Microsoft Yahei';
          color: white;
          line-height: 80%;
          font-weight: 550;
        }
        .escape{
          font-size: 25px;
          font-family: sans-serif;
          line-height: 80%;
          color: #52ff96;
          font-weight: 600;
        }
      }
      .cave{
        font-size: 45px;
        font-family: sans-serif;
        color: rgb(71, 71, 71);
        font-weight: 700;
      }
    }
  .container {
    height: 100%;
    margin: auto 50px auto auto;
    padding: 40px 0 0 0;
    width: fit-content;
    text-align: center;
    line-height: 100%;
  }


  .sub {
    flex: 1;
    font-size: 24px;
    font-weight: normal;
    margin-bottom: 38px;
    margin-left: 20px;
    color: #666;
  }

  .entry {
    width: 120px;
    margin-bottom: 38px;
    font-size: 22px;
    font-weight: 600;
    i {
      font-size: 28px;
      color: $xtxColor;
      letter-spacing: -5px;
    }
  }
}


.login-section {
  height: 900px;
  position: relative;
  .wrapper {
    width: 480px;
    height: fit-content;
    background: #fff;
    position: absolute;
    left: 22%;
    top: 220px;
    transform: translate3d(100px, 0, 0);
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);

    nav {
      font-size: 18px;
      height: 55px;
      margin-bottom: 20px;
      border-bottom: 1px solid #f5f5f5;
      display: flex;
      padding: 0 40px;
      text-align: right;
      align-items: center;

      a {
        flex: 1;
        line-height: 1;
        display: inline-block;
        font-size: 18px;
        position: relative;
        text-align: center;
      }
    }
  }
}



.account-box {
  .form {
    padding: 0 20px 20px 20px;

    &-item {
      margin-bottom: 28px;

      .input {
        position: relative;
        height: 46px;

        >i {
          width: 44px;
          height: 44px;
          background: #cfcdcd;
          color: #fff;
          position: absolute;
          left: 1px;
          top: 1px;
          text-align: center;
          line-height: 44px;
          font-size: 22px;
        }

        input {
          padding-left: 44px;
          border: 1px solid #cfcdcd;
          height: 46px;
          line-height: 46px;
          width: 100%;

          &.error {
            border-color: $priceColor;
          }

          &.active,
          &:focus {
            border-color: $xtxColor;
          }
        }

      }

      >.error {
        position: absolute;
        font-size: 12px;
        line-height: 28px;
        color: $priceColor;

        i {
          font-size: 14px;
          margin-right: 2px;
        }
      }
    }

    .agree {
      a {
        color: #069;
      }
    }

    .btn {
      display: block;
      width: 100%;
      height: 50px;
      color: #fff;
      text-align: center;
      line-height: 50px;
      background: $xtxColor;

      &.disabled {
        background: #cfcdcd;
      }
    }
  }


}

.subBtn {
  background: $xtxColor;
  width: 100%;
  color: #fff;
}

.footer{
  width: 100%;
}
</style>