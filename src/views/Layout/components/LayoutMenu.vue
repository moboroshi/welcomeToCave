<script setup>
import { onBeforeRouteUpdate } from "vue-router"


const handleOpen=(key,keyPath)=>{
  console.log("打开:",key,keyPath)
}
const handleSelect=(key,keyPath)=>{
  console.log("选择:",key,keyPath)
}
const handleClose=(key,keyPath)=>{
  console.log("关闭:",key,keyPath)
}
onBeforeRouteUpdate(()=>{
  console.log("路由变化")
})
</script>

<template>
  <div style="height: 100%;">
    <el-menu background-color="#545c64" text-color="#ffffff" active-text-color="#52ff96" :default-active="this.$route.path" class="menu"
    @close="handleClose" @open="handleOpen" @select="handleSelect" router>
      <el-menu-item index="/" class="item"><i class="text">首页</i></el-menu-item>
      <el-menu-item index="/surf" class="item"><i class="text">🏄‍♀️冲浪板块</i></el-menu-item>
      <el-menu-item index="/acg" class="item"><i class="text">二次元租界</i></el-menu-item>
      <el-menu-item index="/language" class="item"><i class="text">语言学习</i></el-menu-item>
      <el-menu-item index="/cs-pro" class="item"><i class="text">专业相关</i></el-menu-item>
      <el-menu-item index="/game" class="item"><i class="text">游戏区</i></el-menu-item>
      <el-menu-item index="/net-tool" class="item"><i class="text">网络工具</i></el-menu-item>
      <el-menu-item index="/message-board" class="item"><i class="text">留言板块</i></el-menu-item>
      <el-menu-item index="/other" class="item"><i class="text">备忘日历</i></el-menu-item>
    </el-menu>
  </div>
</template>

<style scoped lang="scss">
.menu{
  height: 100%;
  width: 13%;
  border: 0;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  position: fixed;
  .item{
    display: block;
    position: relative;
    text-align: center;
    background-color: #383636;
    margin: 4% 7px 4% 5px;
    box-shadow: 0 0 5px rgba(119, 119, 119, 0.8);;
    border: 4px solid #505050;
    border-radius: 10px;
    .text{
      font-family: '微软雅黑';
      font-size: 16px;
      font-weight: 600;
    }
  }
}

</style>