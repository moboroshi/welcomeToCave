import request from '@/utils/http'

export const getMessagesAPI = (page)=>{
  return request({
    url:`/message?page=${page}`,
    method:'GET'
  })
}

export const addMessageAPI = (data)=>{
  return request({
    url:`/message`,
    method:'POST',
    data:data,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}

export const updateMessageAPI = (data)=>{
  return request({
    url:`/message`,
    method:'PUT',
    data:data,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}

export const deleteMessageAPI = (messageId)=>{
  return request({
    url:`/message/${messageId}`,
    method:'DELETE'
  })
}
