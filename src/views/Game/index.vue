<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import horizontal from '@/components/horizontal.vue'
import { onMounted, ref } from 'vue';
import { getSiteListAPI } from '@/apis/site';

const siteList = ref([]);
const getSiteList = async()=>{
  const result = await getSiteListAPI('Game')
  siteList.value = result.data
  }
  onMounted(
    getSiteList
  )
</script>

<template>
  <horizontal :site-list="siteList.slice(0,6)"/>
  <news-container index = "BHMT"/>
</template>

<style lang="scss" scoped>

</style>