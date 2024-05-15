import request from '@/utils/http'

export const loginAPI = (data)=>{
  return request({
    url:'/user/login',
    method:"POST",
    data:data
  })
}

export const addUserAPI = (data)=>{
  return request({
    url:'/user',
    method:"POST",
    data:data
  })
}

export const updateUserAPI = (data)=>{
  return request({
    url:'/user',
    method:"PUT",
    data:data
  })
}

export const getUserAPI = (userId)=>{
  return request({
    url:`/user/${userId}`,
    method:'GET'
  })
}