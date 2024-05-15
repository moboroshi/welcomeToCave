import request from '@/utils/http'

export const getInformAPI = (date,userId)=>{
  return request({
    url:`/calendar/${date}/${userId}`,
    method:'GET',
  })
}
export const writeInformAPI = (data)=>{
  return request({
    url:"/calendar",
    method:"POST",
    data:data,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}
export const updateInformAPI = (data)=>{
  return request({
    url:"/calendar",
    method:"PUT",
    data:data,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}