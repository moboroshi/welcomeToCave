<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import LayoutNav from './components/LayoutNav.vue'
import LayoutHeader from './components/LayoutHeader.vue'
import LayoutFooter from './components/LayoutFooter.vue'
import LayoutFooterBar from './components/LayoutFooterBar.vue'
import LayoutFixed from './components/LayoutFixed.vue';
import LayoutMenu from './components/LayoutMenu.vue';
import('@/utils/live2d')
import('@/utils/LAppDefine')

</script>



<template>
  <LayoutFixed/>
  <LayoutNav/>
  <div class="container">
    <div class="main">
      <div class="menu">
        <LayoutMenu/>
      </div>
      <div class="outerCon">
        <div class="content">
          <LayoutHeader />
          <div class="router">
            <RouterView/>
          </div>
        </div>
        <LayoutFooter class="footer"/>
      </div>
      
    </div>
    <LayoutFooterBar class="footerBar"/>
  </div>



</template>

<style scoped lang="scss">
.container{
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  height: max-content;
    .main{
      display: flex;
      position: relative;
      .menu{
        width: 13%;
        background: #545c64;
      }
      .outerCon{
        display: flex;
        flex-direction: column;
        width: 87%;
        min-height: 1000px;
        height: fit-content;
        position: relative;
        background: url('@/assets/images/background4.jpg') no-repeat;
        background-size: cover;
        .content{
          .router{
            min-height: 900px;
            height: fit-content;
          }
          .footer{
            position: absolute;
            width: 100%;
            bottom: 0;
          }
        }
      }
      
    }
    .footerBar{
      position: relative;
    }
}



</style>