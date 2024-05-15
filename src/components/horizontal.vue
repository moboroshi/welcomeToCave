<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import xiaohongshu from '@/assets/images/小红书.png'
import defaultIcon from '@/assets/images/defaultIcon.png'
import site from './site.vue'
import { ref } from 'vue'

const defaultSiteIcon = ref(defaultIcon)
defineProps({
  siteList:{
    type:Array,
    default:()=>{
      return [
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
      logo:'https://abs.twimg.com/responsive-web/client-web/icon-ios.77d25eba.png',
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
  }
})

</script>

<template>
  <div class="bar">
    <site class="site" v-for="site in siteList" :key="site" :logo="site.logo||defaultSiteIcon" :site="site.site" :name="site.name"></site>
  </div>
</template>

<style lang="scss" scoped>
.bar{
  display: flex;
  width: 80%;
  height: fit-content;
  border-radius: 15px;
  border: 5px solid rgb(255,255,255,0.9);
  background: rgb(200, 200, 200, 0.48);
  backdrop-filter: blur(2px);
  margin: 80px auto;
  box-shadow: 0 0 20px rgb(0,0,0,0.3);
  .site{
    position: relative;;
  }
}
</style>