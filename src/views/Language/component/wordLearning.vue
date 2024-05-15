<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import { getEnglishListAPI, getNihongoListAPI, updateEnglishUserdataAPI, updateNihongoUserdataAPI } from "@/apis/language";
import { useUserStore } from "@/stores/user";
import { computed, onMounted, ref } from "vue"

const props = defineProps({
  typeIndex:{
    type:String,
    default:'nihonngo'
  }
})

const userStore = useUserStore()
const userId = userStore.userInfo.userId
const index = ref(0)
const wordList = ref([])
const word = computed(()=>wordList.value[index.value])
const Mean = ref(false)
const getData = async()=>{
  if(props.typeIndex == 'nihonngo'){
    const result = await getNihongoListAPI(userId)
    wordList.value = result.data
  }
  if(props.typeIndex == 'english'){
    const result = await getEnglishListAPI(userId)
    wordList.value = result.data
  }
  console.log(wordList);
}
const showMeaning = ()=>{
  Mean.value = true
  console.log(word.value);
}
const next = ()=>{
  saveData()
  Mean.value = false
  setTimeout(() => {
    index.value++;
    if (index.value >= wordList.value.length - 1) {
      getData();
      index.value = 0;
    }
  }, 300);
  console.log(index.value);
}
const saveData = async()=>{
  const data = JSON.stringify({
    userId : userId,
    wordId : word.value.wordId,
    viewed : word.value.viewed+1,
    click: word.value.click
  })
  if(props.typeIndex == 'nihonngo'){
    await updateNihongoUserdataAPI(data)
  }else{
    await updateEnglishUserdataAPI(data)
  }
  
}
const collect = ()=>{
  console.log(word.value.click)
  if(word.value.click == true){
    word.value.click = false
  }else{
    word.value.click = true
  }
}


onMounted(()=>{
  getData()
})
</script>

<template>
  <div class="container">
    <div class="wordBox">
      <p class="word" v-if="props.typeIndex == 'nihonngo' && word"> {{ word.japanese }} </p>
      <p class="word" v-if="props.typeIndex == 'english' && word"> {{ word.english }} {{ word.pronunciation }}</p>
      <i v-if="word" @click="collect" class="star" :class="{
        unclick:word.click == false,
        click: word.click == true
      }">★</i>
    </div>
    
    <div class="meaning" >
      <transition name="meaning">
        <p v-show="Mean" v-if="word"> {{ word.chinese }} </p>
      </transition>
    </div>
    <div class="buttonBox">
      <button @click="showMeaning" class="showMeaning">释义</button>
      <button @click="next" class="next">下一个</button>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container{
  display: flex;
  flex-direction: column;
  width: 40%;
  .wordBox{
    width: 100%;
    border-radius: 10px;
    min-height: 80px;
    margin: 10px auto;
    background: #ddd;
    box-shadow: 0 0 5px rgb(0, 0, 0,0.2);
    border:  3px solid;
    border-color: #fff;
    display: flex;
    .word{
      width: 80%;
      font-family: '微软雅黑';
      line-height: 80px;
      text-align: center;
      font-weight: 600;
      font-size: 36px;
    }
    .star{
      font-size: 60px;
      height: 60px;
      line-height: 80px;
      font-weight: 900;
      transition: 0.4s;
      &::after{
        opacity: 0;
        transition: .3s;
      }
      &:active{
        box-shadow: 0 0 30px #ffef5b;
        background: rgba(255, 252, 75, 0.4);
        opacity: 0.4;
        transform: scale(1.3);
        transition: 0s;
      }
    }
    .unclick{
      color: #c1c1c1;
      &:hover{
        color: #fdffce;
      }
    }
    .click{
      font-size: 60px;
      line-height: 80px;
      font-weight: 700;
      color: #ffef5b;
    }
  }

  .meaning{
    border-radius: 20px;
    width: 100%;
    background: #ddd;
    box-shadow: 0 0 5px rgb(0, 0, 0,0.2);
    font-size: 20px;
    font-family: '微软雅黑';
    min-height: 400px;
    margin: 10px auto 10px auto;
    text-align: center;
    border:  3px solid;
    border-color: #fff;
    white-space: pre-line;
    word-wrap: break-word;
    padding: 20px 10px;
  }
  .buttonBox{
    display: flex;
    justify-content: space-around;
    .showMeaning{
      height: 60px;
      width: 40%;
      border-style: none;
      border-radius: 5px;
      box-shadow: 0 0 5px rgb(0, 0, 0,0.2);
      background: #b50000;
      color: #fff;
      font-size: 24px;
      font-weight: 550;
      transform: scale(1);
      transition: 0.2s;
    }
    .showMeaning:active {
        transform: scale(0.97);
      }
    .next{
      height: 60px;
      width: 40%;
      border-style: none;
      border-radius: 5px;
      box-shadow: 0 0 5px rgb(0, 0, 0,0.2);
      background: #269000;
      color: #fff;
      font-size: 24px;
      font-weight: 550;
      transition: 0.2s;
    }
    .next:active {
        transform: scale(0.97);
      }
  }
}
.meaning-enter-active {
  transition: all 0.5s ease-out;
}
.meaning-leave-active {
  transition: all 0.3s cubic-bezier(1, 0.5, 0.8, 1);
}
.meaning-enter-from{
  opacity: 0;
}
.meaning-leave-to {
  opacity: 0;
}
</style>