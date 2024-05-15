<script setup>
defineProps({
  index:{
    type: String,
    default: ''
  },
  text:{
    type: String,
    default: '消息发送错误，请重试'
  }
})
</script>

<template>
  <div v-if="index == '卡布娘'" class="modItem">
    <div class="information">
      <img class="photo" src="../../../assets/images/kabu.png"/>
      <i class="name"> 卡布娘 </i>
    </div>
    <div class="mod"> {{ text }} </div>
  </div>
  <div v-else class="userItem">
    <div class="mod"> {{ text }} </div>
    <div class="information">
      <img class="photo" src="../../../assets/images/defaultPhoto.png"/>
      <i class="name"> {{ index }} </i>
    </div>
  </div>
</template>

<style scoped lang="scss">
.modItem{
  padding: 5px;
  margin-left: 10px;
  margin-right: auto;
  .information{
    display: flex;
    flex-direction: column;
    margin-right: 10px;
    .photo{
      width: 45px;
      height: 45px;
      border-radius: 50%;
      box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
    }
    .name{
      font-family: '微软雅黑';
      font-size: 14px;
      font-weight: 550;
      text-align: center;
      color: #7e7e7e;
    }
  }
  .mod {
    width: fit-content;
    max-width: 600px;
    height: fit-content;
    line-height: 90%;
    border: 1px solid #ccc;
    border-radius: 0 10px 10px 10px;
    padding: 10px;
    position: relative;
    text-align: center;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
    background: linear-gradient(to right, #b9f2f2 10%, #e4c7e9 100% );
    margin-top: 10px;
    font-family: '微软雅黑';
    font-size: 14px;
    line-height: 15px;
    white-space: pre-line;
  }
  .mod::before {
    content: "";
    position: absolute;
    top: 0px;
    left: 0px;
    transform: translateX(-100%);
    border-width: 10px 0px 10px 10px;
    border-style: solid;
    border-color: #b9f2f2 transparent transparent transparent;
  }
}


.userItem{
  padding: 5px;
  margin-right: 10px;
  margin-left: auto;
  .information{
    display: flex;
    flex-direction: column;
    margin-left: 10px;
    .photo{
      width: 45px;
      height: 45px;
      border-radius: 50%;
    }
    .name{
      font-family: '微软雅黑';
      font-size: 14px;
      font-weight: 600;
      text-align: center;
      color: #7e7e7e;
    }
  }
  .mod {
    width: fit-content;
    max-width: 600px;
    height: fit-content;
    border: 1px solid #ccc;
    border-radius: 10px 0px 10px 10px;
    padding: 10px;
    position: relative;
    text-align: center;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
    background: #e5e5e5;
    margin-top: 10px;
  }
  .mod::before {
    content: "";
    position: absolute;
    top: 0px;
    right: 0px;
    transform: translateX(50%);
    border-width: 10px 10px 10px 10px;
    border-style: solid;
    border-color: #e5e5e5 transparent transparent transparent;
  }
}
</style>
