<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import { onMounted, ref,computed } from "vue"
import { getRepliesAPI,addReplyAPI } from "@/apis/reply";
import { updateMessageAPI } from "@/apis/message";
import { useUserStore } from "@/stores/user";
import { getUserAPI } from "@/apis/user";
 const props = defineProps({
  text:{
    type:String,
    default:''
  },
  userId:{
    type:Number,
    default:1
  },
  count:{
    type:Number,
    default:0
  },
  messageId:{
    type:Number,
  }
 })

const page = ref(1) 
const pageSize = ref(5)
const total = ref(0)
const userStore = useUserStore()
const userInfo = ref({})
userInfo.value = userStore.userInfo
const reply = ref('')
const replies = ref([])
const show = ref(false)
const likeCount = ref(0)
likeCount.value = props.count
const username = ref('')
const userImg = ref('')
const replyUserId = ref(0)

const date = new Date()
const year = date.getFullYear()
const month = date.getMonth()
const day = date.getDate()
const monthFit = computed(() => ('0' + (month + 1)).slice(-2));
const dayFit = computed(() => ('0' + day).slice(-2));
const nowDate = computed(() => `${year}-${monthFit.value}-${dayFit.value}`);
const updateTime = ref(new Date().toISOString())
updateTime.value = updateTime.value.substring(0, 19)

 const replyEvent = async()=>{
  console.log(reply.value)
  await addReplyAPI(JSON.stringify({
    userId: replyUserId.value,
    date: nowDate.value,
    text: reply.value,
    replyTime: updateTime.value,
    messageId: props.messageId
  }))
  reply.value = '';
  show.value = false;
  getReplies()
 }
 const getReplies = async()=>{
  const result = await getRepliesAPI(props.messageId,page.value)
  replies.value = result.data.rows
  total.value = result.data.total
  console.log(result);
 }
 const saveCount = async()=>{
  await updateMessageAPI(JSON.stringify({
    messageId: props.messageId,
    userId : props.userId,
    count: likeCount.value,
    date : nowDate.value,
    text: props.text,
    commentTime: updateTime.value
  }))
  console.log(props.messageId)
 }
const clickLike =()=>{
        likeCount.value++;
        saveCount();
      }
const getUserInfo = async()=>{
  const result = await getUserAPI(props.userId);
  const messageUserInfo = result.data
  username.value = messageUserInfo.nickname;
  userImg.value = messageUserInfo.userImg;
}
const getReplyUserId = ()=>{
  replyUserId.value = userInfo.value.userId
}

const pageChange = async(currentPage)=>{
  console.log(currentPage)
  page.value = currentPage
  const result = await getRepliesAPI(props.messageId,page.value);
  replies.value = result.data.rows;
}

 onMounted(()=>{
  getUserInfo()
  getReplies()
  getReplyUserId()
 }
 
 )
</script>

<template>
  <div class="container">
    <div class="comment">
      <img :src="userImg" referrerpolicy="no-referrer"/>
      <p class="text">{{ text }}</p>
      <i class="messageId">#{{ props.messageId }}</i>
      <i class="username">{{ username }}</i>
    </div>

    <div class="react">
      <div @click="clickLike" class="likeBox">
        <i class="like">❤</i>
        <transition name="slide-fade">
          <i class="likeCount" :key="likeCount"> {{ likeCount }}</i>
        </transition>
      </div>
      <i @click="()=>{
        show = true
      }" class="replyButton">回复</i>
    </div>
      <li class="reply" v-for="reply in replies" :key="reply"> {{ reply.text }} </li>
      <transition name="reply-show">
      <div class="replyBar" v-if="show">
        <input v-model="reply" placeholder="请友善发言">
        <button @click="replyEvent" class="submit">回复</button>
      </div>
    </transition >
    <div class = "pageHelper">
      <el-pagination
        background
        layout="prev, pager, next"
        v-model:default-page-size="pageSize"
        :total="total"
        @current-change="pageChange"
        hide-on-single-page
        small
      >
      </el-pagination>
    </div>
  </div>
</template>

<style lang="scss" scoped>
  .container{
    margin: 8px auto;
    width: 96%;
    min-height: 70px;
    height: fit-content;
    display: flex;
    flex-direction: column;
    border-radius: 5px;
    background: rgb(240,240, 240,0.3);
    box-shadow: 0 0 8px rgb(0, 0, 0,0.2);
    .comment{
      display: flex;
      position: relative;
      img{
        margin-left: 3%;
        margin-top: 5px;
        height: 40px;
        width: 40px;
        border-radius: 50%;
      }
      .messageId{
        position: absolute;
        top: 2px;
        right: 5px;
        color: #888;
      }
      .username{
        position: absolute;
        top: 48px;
        left: 3%;
        color: #363636;
      }
      .text{
        font-family: '微软雅黑';
        font-size: 20px;
        margin-left: 2%;
        margin-right: auto;
        line-height: 40px;
      }
    }

    .react{
      display: flex;
      justify-content: flex-end;
      text-align: center;
      .likeBox{
        width: 40px;
        height: 40px;
        position: relative;
        .like{
          height: 64px;
          width: 45px;
          top: -10px;
          left: 0px;
          font-size: 36px;
          color: #ff4f4f;
          position: absolute;
        }
        .likeCount{
          height: 64px;
          width: 45px;
          line-height: 56px;
          top: -12px;
          left: 0px;
          font-size: 16px;
          font-weight: 700;
          color: #fff;
          position: absolute;
          text-align: center;
        }
      }
      .replyButton{
        margin-left: 5%;
        margin-right: 15px;
        margin-top: 6px;
        text-decoration: underline;
        color: rgb(0, 140, 255);
      }
    }
    .replyBar{
      margin: 0 auto;
      width: 60%;
      input{
        width: 80%;
      }
      .submit{
        margin-left: 2%;
      }
    }
    .reply{
      width: 70%;
      font-family: '微软雅黑';
      font-size: 15px;
      margin-left: 10%;
      box-shadow: 0 0 5px rgb(0, 0, 0,0.1);
    }
  }
  .slide-fade-enter-active {
    transition: all 0.1s ease-out;
  }

  .slide-fade-leave-active {
    transition: all 0.2s cubic-bezier(1, 0.5, 0.8, 1);
  }

  .slide-fade-enter-from,
  .slide-fade-leave-to {
    transform: translateY(-30px);
    opacity: 0;
  }
  .reply-show-enter-active{
    transition: opacity 0.7s ease;
  }
  .reply-show-leave-active {
    transition: opacity 0.5s ease;
  }

  .reply-show-enter-from,
  .reply-show-leave-to {
    opacity: 0;
  }
  .pageHelper{
    position: relative;
    margin: 2px 20px 2px auto;
  }
</style>