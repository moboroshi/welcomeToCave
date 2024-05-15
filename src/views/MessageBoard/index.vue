<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import { onMounted, ref,computed } from 'vue';
import { getMessagesAPI,addMessageAPI } from '@/apis/message'
import message from './component/message.vue';
import { useUserStore } from '@/stores/user';

const userStore = useUserStore()
const comments = ref([])
const userInfo = userStore.userInfo
const userId = ref(0)
const page =ref(1)
const date = new Date()
const total = ref(0)
const year = date.getFullYear()
const month = date.getMonth()
const day = date.getDate()
const monthFit = computed(() => ('0' + (month + 1)).slice(-2));
const dayFit = computed(() => ('0' + day).slice(-2));
const nowDate = computed(() => `${year}-${monthFit.value}-${dayFit.value}`);
const updateTime = ref(new Date().toISOString())
updateTime.value = updateTime.value.substring(0, 19)
const getComment = async()=>{
  const result = await getMessagesAPI(page.value);
  comments.value = result.data.rows;
  total.value = result.data.total;
  console.log(comments.value)
}
const messa = ref('')
const messaData = ref({})
const sendMessage = async()=>{
  console.log(messa.value)
  messaData.value = {
    userId:userId.value,
    count: 0,
    date: nowDate.value,
    text:messa.value,
    commentTime: updateTime.value
  }
  messaData.value = JSON.stringify(messaData.value)
  await addMessageAPI(messaData.value);
  getComment()
  console.log(comments.value) 
}

const getUserId = ()=>{
  userId.value = userInfo.userId
}

const pageChange = async(currentPage)=>{
  console.log(currentPage)
  page.value = currentPage
  const result = await getMessagesAPI(page.value);
  comments.value = result.data.rows;
}



onMounted(()=>{
  getComment(),
  getUserId()
}
)
</script>

<template>
  <div class="messageBoard">
    <div class="notice">
      <i class="title">留言板块</i>
      <p class="discribe"> 这里是网站的留言板块，有什么想分享的，或是有什么建议，就在这里留言吧! </p>
    </div>
    <div class="commentBoard">
      <message v-for="comment in comments" :key="comment" :text="comment.text" :userId="comment.userId" :count="comment.count" :messageId="comment.messageId"/>
    </div>
    <div class = "pageHelper">
      <el-pagination
        background
        layout="prev, pager, next, jumper"
        :total="total"
        @current-change="pageChange"
      >
      </el-pagination>
    </div>
    <div class="messageSend">
      <input v-model="messa"/>
      <button @click="sendMessage">留言</button>
    </div>
  </div>
</template>

<style lang="scss" scoped>
  .messageBoard{
    width: 60%;
    height: 1000px;
    margin: 30px auto;
    background: rgba(255, 230, 230, 0.6);
    backdrop-filter: blur(1px);
    border-radius: 15px;
    box-shadow: 0 0 20px rgb(0, 0, 0,0.2);
    display: flex;
    flex-direction: column;

    .notice{
      text-align: center;
      .title{
        margin-top: 20px;
        color: #5f5f5f;
        font-size: 36px;
        font-weight: 700;
      }
      .discribe{
        color: #848484;
        font-weight: 550;
        font-size: 20px;
      }
    }
    .commentBoard{
      height: 80%;
      width: 98%;
      margin: auto;
      border-radius: 10px;
      background: rgba(255, 246, 246, 0.8);
      backdrop-filter: blur(5px);
      box-shadow: 0 0 10px rgb(0, 0, 0,0.2);
      overflow: auto;
      position: relative;
      
    }
    .pageHelper{
      position: relative;
      margin: 5px 20px 5px auto;
    }
    .messageSend{
      margin: 10px auto;
      width: 80%;
      height: 50px;
      position: relative;
      input{
        border: 2px solid #e3c8e9;
        border-radius: 5px;
        background: #eeeeee;
        top: 0;
        right: 0;
        width: 100%;
        min-height: 50px;
        height: fit-content;
        padding-left: 13px;
    }
      button{
        background-color: #e3c8e9;
        border-radius: 0 5px 5px 0;
        border-color: #e3c8e9;
        right: 0px;
        height: 50px;
        width: 65px;
        cursor: pointer;
        position: absolute;
        font-size: 16px;
        color: #ffffff;
        font-family: '微软雅黑';
      }
    }
  }
</style>