<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import horizontal from '@/components/horizontal.vue';
import wordLearning from './component/wordLearning.vue';
import { onMounted, ref } from 'vue';
import { getSiteListAPI } from '@/apis/site';

const siteList = ref([]);
const getSiteList = async()=>{
  const result = await getSiteListAPI('language')
  siteList.value = result.data
  }
  onMounted(
    getSiteList
  )
</script>

<template>
  <div class="veticalBox">
    <horizontal :siteList="siteList"/>
    <div class="horizonBox">
      <wordLearning/>
      <wordLearning typeIndex="english"/>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.veticalBox{
  display: flex;
  flex-direction: column;
  .horizonBox{
    display: flex;
    width: 80%;
    margin: 10px auto;
  }
}
</style>