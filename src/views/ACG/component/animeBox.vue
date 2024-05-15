<!-- eslint-disable vue/multi-word-component-names -->
<script setup>


defineProps({
  title:{
    type:String,
    default:''
  },
  subTitle:{
    type:String,
    default:''
  },
  poster:{
    type:String,
    default:''
  },
  director:{
    type:String,
    default:''
  },
  studio:{
    type:String,
    default:''
  },
  typeOf:{
    type:String,
    default:''
  },
  label:{
    type:String,
    default:''
  },
  date:{
    type:String,
    default:''
  },
  time:{
    type:String,
    default:''
  }
})



</script>

<template>
  <div class="animeBox">
    <div class="container">
      <div class="info">
        <i class="title"> {{ title }} </i>
        <i class="subtitle">{{ subTitle }}</i>
        <div class="moreInfo">
          <i>导演：{{ director }}</i>
          <i>制作公司：{{ studio }}</i>
          <i>类型：{{ typeOf }}</i>
          <i>标签：{{ label }}</i>
        </div>
          <i class="date"> {{ date }} </i>
          <i class="time">{{ time }}</i>
      </div>
      <div class="posterContainer"> <img class="poster" :src="poster" referrerpolicy="no-referrer"/> </div>
      <div class="block">&#8194;</div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.animeBox{
  background: #000;
  width: 99%;
  height: fit-content;
  display: flex;
  flex-direction: column;
  margin-bottom: 1px;
  .container{
    width: 100%;
    height: 100%;
    display: flex;
    position: relative;
    .posterContainer{
      width: 25%;
      display: flex;
      justify-content: center;
      margin: 5px 0;
      .poster{
        width: 95%;
      }
    }
    .info{
      display: flex;
      width: 70%;
      height: fit-content;
      flex-direction: column;
      margin: 5px 0 5px 5px;
      .title{
        font-size: 24px;
        font-weight: 700;
        font-family: '微软雅黑';
        color: #fff;
      }
      .subtitle{
        font-size: 18px;
        font-weight: 500;
        font-family: '微软雅黑';
        color: #fff;
      }
      .moreInfo{
        background: #777;
        color: #fff;
        font-size: 16px;
        display: flex;
        flex-direction: column;
      }
      .date{
        text-align: center;
        font-size: 24px;
        color: #fff;
      }
      .time{
        text-align: center;
        font-size: 24px;
        color: #fff;
      }
    }
    .block{
      width: 5%;
      background: #ff941a;
    }
  }
}
</style>