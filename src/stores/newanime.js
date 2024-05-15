import { getAnimeListAPI,getDayAnimeListAPI } from "@/apis/anime";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useAnimeStore = defineStore('anime', () =>{
  const animeList = ref([])
  const animeListByDay = ref([])
  const getAnimeList = async (season)=>{
    const res = await getAnimeListAPI(season)
    animeList.value = res.data
  }
  const getDayAnimeList = async(season,day)=>{
    const res = await getDayAnimeListAPI(season,day)
    animeListByDay.value = res.data
    console.log(animeListByDay.value)
  }
  return{
    animeList,
    animeListByDay,
    getAnimeList,
    getDayAnimeList
  }
})