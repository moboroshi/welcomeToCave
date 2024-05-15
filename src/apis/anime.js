import request from '@/utils/http'

export const getAnimeListAPI = (season)=>{
  return request({
    url:`/anime/${season}`,
    method:'GET',
  })
}
export const getDayAnimeListAPI = (season,day)=>{
  console.log(day);
  return request({
    url:`/anime/${season}/${day}`,
    method:'GET',
  })
}