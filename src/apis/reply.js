import request from '@/utils/http'

export const getRepliesAPI = (messageId,page)=>{
  return request({
    url:`/reply/${messageId}?page=${page}`,
    method:'GET'
  })
}

export const addReplyAPI = (data)=>{
  return request({
    url:`/reply`,
    method:'POST',
    data:data,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}

export const updateReplyAPI = (data)=>{
  return request({
    url:`/reply`,
    method:'PUT',
    data:data,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}

export const deleteReplyAPI = (replyId)=>{
  return request({
    url:`/reply/${replyId}`,
    method:'DELETE'
  })
}
