import request from '@/utils/http'

export const getNihongoListAPI = (userId)=>{
  return request({
    url:`/japanese/${userId}`,
    method:'GET'
  })
}

export const updateNihongoUserdataAPI = (data)=>{
  return request({
    url:'/japanese',
    method:'POST',
    data:data,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}

export const getEnglishListAPI = (userId)=>{
  return request({
    url:`/english/${userId}`,
    method:'GET'
  })
}

export const updateEnglishUserdataAPI = (data)=>{
  return request({
    url:'/english',
    method:'POST',
    data:data,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}