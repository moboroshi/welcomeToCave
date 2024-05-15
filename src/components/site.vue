<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import defaultIcon from '@/assets/images/defaultIcon.png'

defineProps({
  logo:{
    type:String,
    default:defaultIcon
  },
  site:{
    type:String,
    default:''
  },
  name:{
    type:String,
    default:''
  }
})
</script>

<template>
  <a :href="site" target="_blank">
    <div class="container">
      <img referrerpolicy="no-referrer" class = "logo" :src="logo" :on-error="defaultIcon"/>
      <div class="site" >
        <a :href="site" target="_blank">{{ name }}</a>
      </div>
    </div>
  </a>
  
</template>

<style scoped lang="scss">
.container{
  min-height: 80px;
  height: fit-content;
  min-width: 80px;
  background: white;
  box-shadow: 0 0 5px rgb(0,0,0,0.2);
  display: flex;
  flex-direction: column;
  border-radius: 10px;
  .logo{
    margin: 5px auto;
    height: 40px;
    width: 40px;
    border-radius: 5px;
  }
  .site{
    font-family: '微软雅黑';
    font-size: 14px;
    font-weight: 550;

    margin: 3px auto;
    display: flex;
    max-width: 70px;
    a{
      text-overflow: ellipsis;
      overflow: hidden;
      white-space: nowrap;
      &:hover {
          color: #4336ff;
          border-bottom: 1px solid #4336ff;
          
        }
    }
  }
  &:hover {
          background: #e5e5e5;
        }
}
</style>