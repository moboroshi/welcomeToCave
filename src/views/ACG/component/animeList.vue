<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import {  onMounted, ref } from "vue";
import animeBox from "./animeBox.vue";
//import { useAnimeStore } from "@/stores/newanime"
import { getDayAnimeListAPI } from "@/apis/anime";


//const animeStore = useAnimeStore()
const props = defineProps({
  day:{
    type:Number,
    default:0
  }
})

const animeList = ref([])
const season = ref(202404)
const getData = async(day)=>{
  const result = await getDayAnimeListAPI(season.value,day)
  animeList.value = result.data
}
onMounted(()=>{
  getData(props.day)
})


</script>

<template>
  <div class="animeList"> 
    <animeBox v-for="anime in animeList" :key="anime" :title="anime.title" :sub-title="anime.subTitle" :poster="anime.poster" :director="anime.director"
    :studio="anime.studio" :type-of="anime.typeOf" :label="anime.label" :date="anime.date" :time="anime.time"/>
  </div>
</template>

<style lang="scss" scoped>
  .animeList{
    margin: 0 auto;
    width: 50%;
    max-height: 85%;
    min-height: 1200px;
    height: fit-content;
    .day{
      background: #000;
      color: #fff;
      font-size: 20px;
      font-weight: 550;
    }
  }
</style>