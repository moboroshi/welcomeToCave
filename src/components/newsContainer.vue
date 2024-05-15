<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import { getBBCNewsAPI, getBHMTNewsAPI } from "@/apis/news";
import { onMounted, ref } from "vue";


const props = defineProps({
  index:{
    type:String,
    default:'BBC'
  }
})

const newsList = ref([])
const Barname = ref('')

const getNewsList = async()=>{
  const result = ref([])
  if(props.index == 'BBC'){
    result.value = await getBBCNewsAPI()
    Barname.value = 'BBC News'
  }
  if(props.index == 'BHMT'){
    result.value = await getBHMTNewsAPI()
    Barname.value = '巴哈姆特電玩資訊站'
  }
  newsList.value = result.value.data
  console.log(newsList.value)
}

onMounted(()=>{
  getNewsList()
})

</script>

<template>
  <div class="Bar">{{ Barname }}</div>
  <div v-if="newsList.length!=0" class="container">
    <div class="Banner">
      <el-carousel height="400px">
        <el-carousel-item v-for="item in newsList" :key="item">
          <a :href="item.link" target="_blank">
            <img referrerpolicy="no-referrer" :src="item.image" alt="">
          </a>
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="list">
      <ul v-for="news in newsList" :key="news">
        <li><a :href="news.link" target="_blank">{{ news.title }}</a></li>
      </ul>
    </div>
    
  </div>
</template>

<style lang="scss" scoped>
.Bar{
  width: 80%;
  border: 10px solid rgb(200, 200, 200, 0.7);
  background: rgb(255,255,255,0.8);
  text-align: center;
  font-size: 48px;
  font-weight: 700;
  padding: 10px 0;
  margin: 20px auto;
}
.container{
  display: flex;
  width: 70%;
  height: fit-content;
  padding: 8px;
  background: rgb(200, 200, 200, 0.7);
  backdrop-filter: blur(2px);
  border-radius: 10px;
  border: 3px solid rgb(255,255,255,0.9);
  box-shadow: 0 0 20px rgb(0,0,0,0.3);
  margin: 20px auto 80px auto;
  .Banner {
    width: 70%;
    img {
      width: 100%; 
      height: 100%; 
      object-fit: cover;
    }
  }
  .list{
    width: 45%;
    background: rgba(240, 240, 240, 0.9);
    border-radius: 0px 8px 8px 0px;
    padding: 10px 4px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    li{
      background: white;
      padding: 3px;
      height: 50px;
      line-height: 100%;
      border-radius: 3px;
      font-family: '微软雅黑';
      font-size: 22px;
      font-weight: 600;
      align-items: center;
      a{
        height: 100%;
        line-height: 50px;
        display: block;
        text-align: center;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        color: #2893ff;
        text-decoration-line: underline;
      }
    }
  }
}
</style>