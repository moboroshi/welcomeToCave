<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import horizontal from '@/components/horizontal.vue'
import { onMounted, ref } from 'vue';
import animeList from './component/animeList.vue';
import { getSiteListAPI } from '@/apis/site';
import { useAnimeStore } from '@/stores/newanime';

const siteList = ref([]);
const animeStore = useAnimeStore()
const getSiteList = async()=>{
  const result = await getSiteListAPI('anime')
  siteList.value = result.data
  }
  const season = 202404
  const prevWeekday = ref('')
  const weekday = ref('')
  const nextWeekday = ref('')
  const day = ref(0)
  const index = ref('left')
  const getDay = ()=>{
    const date = new Date();
    day.value = date.getDay();
    console.log(day.value);
  }

  const getWeekday = ()=>{
    animeStore.getDayAnimeList(season,day.value)
    switch(Number(day.value)){
      case 0:
        weekday.value =  '日耀';
        prevWeekday.value = '土曜'
        nextWeekday.value = '月曜'
        break;
      case 1:
        weekday.value = '月耀';
        prevWeekday.value = '日曜'
        nextWeekday.value = '火曜'
        break;
      case 2:
        weekday.value = '火曜';
        prevWeekday.value = '月曜'
        nextWeekday.value = '水曜'
        break;
      case 3:
        weekday.value = '水曜';
        prevWeekday.value = '火曜'
        nextWeekday.value = '木曜'
        break;
      case 4:
        weekday.value = '木曜';
        prevWeekday.value = '水曜'
        nextWeekday.value = '金曜'
        break;
      case 5:
        weekday.value = '金曜';
        prevWeekday.value = '木曜'
        nextWeekday.value = '土曜'
        break;
      case 6:
        weekday.value =  '土曜';
        prevWeekday.value = '金曜'
        nextWeekday.value = '日曜'
        break;
    }
  }
  const prevDay = ()=>{
    if (day.value <=0){
      day.value = 6
    }else{
      day.value--
    }
    getWeekday()
    index.value = 'left'
  }
  const nextDay = ()=>{
    if(day.value >=6){
      day.value = 0;
    }else{
      day.value++
    }
    getWeekday()
    index.value = 'right'
  }
  onMounted(()=>{
    getSiteList(),
    getDay(),
    getWeekday(),
    animeStore.getDayAnimeList(season,day.value)
  }

  )
</script>

<template>
  <horizontal :site-list="siteList.slice(0,8)"/>
  <horizontal :site-list="siteList.slice(8,16)"/>
  <div class="changeBar">
    <i class="prev" @click="prevDay">&#10094;</i>
    <transition mode="out-in" :name="index">
      <i :key="weekday" class="weekday">{{ weekday }}</i>
    </transition>
    <i class="next" @click="nextDay">&#10095;</i>
  </div>
  <transition :name="index" mode="out-in">
    <div :key="day" class="anime">
      <animeList :day="day"/>
    </div>
  </transition>
</template>

<style lang="scss" scoped>
.changeBar{
  background: #000;
  display: flex;
  position: relative;
  justify-content: center;
  .prev{
    font-size: 36px;
    color: #fff;
    position: absolute;
    left: 35%;
  }

  .weekday{
    font-size: 36px;
    color: #fff;
    margin: auto 30px;
  } 

  .next{
    font-size: 36px;
    color: #fff;
    margin-left: 50px;
    position: absolute;
    right: 35%;
  }
}
.anime{
  width: 100%;
  height: fit-content;
  display: flex;
  flex-direction: row;
}

.left-enter-active {
  transition: all 0.7s ease-out;
}
.left-leave-active {
  transition: all 0.3s cubic-bezier(1, 0.5, 0.8, 1);
}
.left-enter-from{
  transform: rotateY(160deg);
  opacity: 0.7;
}
.left-leave-to {
  transform: rotate3d(0,1,0,180deg);
  opacity: 0.1;
}

.right-enter-active {
  transition: all 0.7s ease-out;
}
.right-leave-active {
  transition: all 0.3s cubic-bezier(1, 0.5, 0.8, 1);
}
.right-enter-from{
  transform: rotateY(-90deg);
  opacity: 0.8;
}
.right-leave-to {
  transform: rotateY(-90deg);
  opacity: 0.7;
}

</style>