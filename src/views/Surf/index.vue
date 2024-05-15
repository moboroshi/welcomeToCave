<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import horizontal from '@/components/horizontal.vue'
import { onMounted, ref } from 'vue';
import { getSiteListAPI } from '@/apis/site';
import newsContainer from '@/components/newsContainer.vue';

const siteList = ref([]);
const getSiteList = async()=>{
  const result = await getSiteListAPI('Surf')
  siteList.value = result.data
  }
  onMounted(
    getSiteList
  )
</script>

<template>
  <horizontal :site-list="siteList.slice(0,6)"/>
  <horizontal :site-list="siteList.slice(6,12)"/>
  <newsContainer index="BBC"/>
</template>

<style lang="scss" scoped>

</style>