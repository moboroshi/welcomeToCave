<script setup>
import site from '@/components/site.vue'
import xiaohongshu from '@/assets/images/小红书.png'
import { onMounted, ref } from 'vue'
const mainSiteList = ref([]);
const getMainSiteList = ()=>{
  mainSiteList.value = [
    {
      logo:'https://www.bilibili.com/favicon.ico',
      site:'https://www.bilibili.com/',
      name:'bilibili'
    },
    {
      logo:'https://tieba.baidu.com/favicon.ico',
      site:'https://tieba.baidu.com/',
      name:'百度贴吧'
    },
    {
      logo:'https://www.agedm.org/favicon.ico',
      site:'https://www.agedm.org/',
      name:'Age动漫'
    },
    {
      logo:'https://twitter.com/favicon.ico',
      site:'https://twitter.com/home',
      name:'推特'
    },
    {
      logo:'https://www.reddit.com/favicon.ico',
      site:'https://www.reddit.com/',
      name:'Reddit'
    },
    {
      logo:'https://ani.gamer.com.tw/favicon.ico',
      site:'https://ani.gamer.com.tw/',
      name:'巴哈姆特動畫瘋'
    },
    {
      logo: xiaohongshu,
      site:'https://www.xiaohongshu.com/explore',
      name:'小红书'
    },
    {
      logo:'https://youtube.com/favicon.ico',
      site:'https://www.youtube.com/',
      name:'YouTube'
    },
    {
      logo:'https://github.com/favicon.ico',
      site:'https://github.com/',
      name:'Github'
    },
  ]
}

onMounted(
  getMainSiteList
)

</script>

<template>
  <header class='app-header'>
    <div class="container">
      <site class="site" v-for="site in mainSiteList" :key="site"  :site="site.site" :logo="site.logo" :name="site.name"></site>
    </div>
  </header>
</template>


<style scoped lang='scss'>
.app-header {
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(5px);
  box-shadow: 0 0 10px rgb(0,0,0,0.2);
  .container {
    display: flex;
    justify-content: space-around;
    width: 90%;
    height: fit-content;
    position: relative;
    z-index: 998;
    .site{
      margin: 10px auto;
    }
  }



}
</style>