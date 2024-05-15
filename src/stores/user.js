import { loginAPI } from "@/apis/user";
import { defineStore } from "pinia";
import { ref } from "vue";
import router from '@/router'


export const useUserStore = defineStore('user', ()=>{
  const userInfo = ref({})
  const token = ref("")

  const getUserInfo = async({username,password})=>{
  const result = await loginAPI({username:username,password:password})
    userInfo.value = result.data.userInfo
    token.value = result.data.token
  }

  const clearUserInfo = ()=>{
    userInfo.value = {}
    token.value = ""
    router.push({path:'/login'})
  }

  return{
    userInfo,
    token,
    getUserInfo,
    clearUserInfo
  }

},{
  persist:true
})