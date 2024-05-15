<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import horizontal from '@/components/horizontal.vue'
import { onMounted, ref } from 'vue';
import { getSiteListAPI } from '@/apis/site';

const siteList = ref([]);
const getSiteList = async()=>{
  const result = await getSiteListAPI('Tool')
  siteList.value = result.data
  }
  onMounted(
    getSiteList
  )
</script>

<template>
  <horizontal :site-list="siteList"/>
</template>

<style lang="scss" scoped>

</style>