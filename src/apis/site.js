import request from '@/utils/http'

export const getSiteListAPI = (belongTo)=>{
  return request({
    url:`/site/${belongTo}`,
    method:'GET',
  })
}