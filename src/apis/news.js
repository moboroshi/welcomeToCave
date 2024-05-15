import request from '@/utils/http'

export const getBBCNewsAPI = ()=>{
  return request({
    url:'/news/bbc',
    method:'GET'
  })
}

export const getBHMTNewsAPI = ()=>{
  return request({
    url:'/news/bhmt',
    method:'GET'
  })
}

