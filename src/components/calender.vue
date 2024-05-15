<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import { computed, onMounted, ref, watch } from "vue";
import { getInformAPI,updateInformAPI,writeInformAPI } from "@/apis/calendar";
import { useUserStore } from "@/stores/user";

const userStore = useUserStore()
const date = new Date()
const currentYear = date.getFullYear();
const currentMonth = date.getMonth();
const today = date.getDate();
const year = ref(currentYear)
const month = ref(currentMonth)
const day = ref(today)
const days = ref([])
const noteText = ref('')
const show = ref(true)
const userId = ref(0)

const clickDate = computed(() => new Date(year.value, month.value, day.value));
const monthFit = computed(() => ('0' + (clickDate.value.getMonth() + 1)).slice(-2));
const dayFit = computed(() => ('0' + clickDate.value.getDate()).slice(-2));
const noteDate = computed(() => `${year.value}-${monthFit.value}-${dayFit.value}`);
const updateTime = ref(new Date().toISOString())

const defaultText = computed(()=>{
  return day.value == today?'今天要做些什么呢..': (month.value+1).toString() + '月' + day.value.toString() + (day.value < today?'日做了什么呢..':'日要做什么呢..')
})

const nextMonth = ()=>{
  if(month.value >= 11){
    month.value=0;
    ++year.value;
  }else{
    month.value++;
  }
  getDays();
}
const prevMonth = ()=>{
  if(month.value <= 0){
    month.value=11;
    --year.value;
  }else{
    month.value--;
  }
  getDays();
}
const nextYear = ()=>{
  year.value++;
  getDays();
}
const prevYear = ()=>{
  if(year.value <= 0){
    year.value=1
  }else{
    year.value--;
  }
  getDays();
}
const getDays = ()=>{
  const firstDayOfMonth = new Date(year.value,month.value,1)
  const weekday = firstDayOfMonth.getDay()
  const startDay = firstDayOfMonth - weekday*60*60*1000*24
  days.value = []
  for (let i =0; i<42; i++){
    days.value.push(new Date(startDay + i*60*60*1000*24))
  }
  getNote()
  console.log(noteDate.value,month.value,dayFit.value,clickDate.value,noteText.value);
}
const saveNote = async ()=>{
  updateTime.value = new Date().toISOString()
  updateTime.value = updateTime.value.substring(0, 19)
  const data = JSON.stringify({
    userId: userId.value,
    date: noteDate.value,
    text: noteText.value,
    updateTime: updateTime.value
  });
  const historyData = await getInformAPI(noteDate.value,userId.value).data
  if(historyData == undefined||historyData == null){
    await writeInformAPI(data)
  }else{
    await updateInformAPI(data)
    console.log(historyData)
  }
}

const getNote = async ()=>{
  console.log(noteDate.value)
  noteText.value = ''
  const res = await getInformAPI(noteDate.value,userId.value)
  console.log(noteText.value)
  noteText.value = res.data.text
}
const showChange= ()=>{
  show.value = !show.value;
}
const getUserID = ()=>{
  userId.value = userStore.userInfo.userId;
}


watch(() => day.value, (newVal, oldVal) => {
      console.log(`Message changed from "${oldVal}" to "${newVal}"`)
      console.log(`Message changed from "${oldVal}" to "${newVal}"`)
      if(show.value == false){
        setTimeout(()=>{
          showChange()
        },500)
      }
      }
    )
onMounted(()=>{
  getUserID()
  getDays()
}
)
</script>

<template>
  <div class="calenderAndNote">
    <div class="calender">
      <div class="month">      
        <ul>
          <li class="prev" @click="prevMonth">&#10094;</li>
          <li class="next" @click="nextMonth">&#10095;</li>
          <li>
            <div class="container">
              <span class="month">{{ new Date(year,month).toDateString().split(" ")[1] }}</span>
              <br>
              <div class="yearBar">
                <i @click="prevYear" class="prevY">&#10094;</i>
                <i class="blank">&#8194;</i>
                <span class="year">{{ year }}</span>
                <i @click="nextYear" class="nextY">&#10095;</i>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <ul class="weekdays">
        <li>Sun</li>
        <li>Mon</li>
        <li>Tue</li>
        <li>Wed</li>
        <li>Thu</li>
        <li>Fri</li>
        <li>Sat</li>
      </ul>

      <ul v-for="i in 6" :key="i" class="days">  
        <li :class="{
          active : new Date(days[(i -1) * 7 + (j -1)]).getDate() == today&&new Date(days[(i -1) * 7 + (j -1)]).getMonth() == currentMonth&& new Date(days[(i -1) * 7 + (j -1)]).getFullYear() == currentYear,
          grey : new Date(days[(i -1) * 7 + (j -1)]).getMonth() !== month,
          select: new Date(days[(i -1) * 7 + (j -1)]).getDate() == day&&new Date(days[(i -1) * 7 + (j -1)]).getMonth() == currentMonth&&new Date(days[(i -1) * 7 + (j -1)]).getDate() !=today
        }" 
        v-for="j in 7" :key="j"
        @click="()=>{
          showChange();
          day = new Date(days[(i -1) * 7 + (j -1)]).getDate();
          console.log(day);
          getNote();
          console.log(noteText)
        }">
          {{ new Date(days[(i -1) * 7 + (j -1)]).getDate()  }}
        </li>
      </ul>
    </div>
    <Transition name="slide-fade">
      <div v-if="show" class="note">
        <div class="bar">
          <p>{{ new Date(year,month,day).toDateString() }}</p> 
          <button @click="saveNote">保存</button>
        </div>
        <textarea v-model="noteText" :placeholder="defaultText" v-on:change="value"> </textarea>
      </div>
    </Transition>

  </div>

</template>

<style scoped lang="scss">
* {box-sizing: border-box;}
ul {list-style-type: none;}
body {font-family: Verdana, sans-serif;}
.calenderAndNote{
  display: flex;
  width: 100%;
  .calender{
    border-radius: 10px;
    width: 49%;
    height: fit-content;
    min-height: 500px;
    margin: 0 auto;
    box-shadow: 0 0 10px rgb(255, 255, 255,0.1);
    .month {
      padding: 40px 25px;
      width: 100%;
      background: #1abc9c;
      text-align: center;
      border-radius: 10px 10px 0 0;
      ul {
        margin: 0;
        padding: 0;
        li {
          color: white;
          font-size: 20px;
          text-transform: uppercase;
          letter-spacing: 3px;
        }
      }
      .prev {
        float: left;
        font-size: 40px;
      }
      .next {
        float: right;
        font-size: 40px;
      }
      .container{
        width: fit-content;
        text-indent: -45px;
        margin-left: 29%;
        .month{
          font-size: 21px;
          font-weight: 550;
          text-align: center;
        }
        .yearBar{
          width: fit-content;
          .prevY{
            font-size: 30px;
          }
          .nextY{
            font-size: 30px;
          }
          .blank{
            letter-spacing: 32px;
          }
          .year{
            font-size: 36px;
            font-weight: 700;
            letter-spacing: 45px;
            text-align: center;
            &::last-letter{
              letter-spacing: 0;
            }
          }
        }
      }
    }
  .weekdays {
    margin: 0;
    padding: 10px 0;
    background-color: #ddd;
    font-weight: 700;
    font-size: 18px;
  }

  .weekdays li {
    display: inline-block;
    width: 13.6%;
    color: #666;
    text-align: center;
  }

  .days {
    padding: 10px 0;
    background: #eee;
    margin: 0;
  }

  .days li {
    list-style-type: none;
    display: inline-block;
    width: 13.6%;
    text-align: center;
    margin-bottom: 5px;
    font-size:16px;
    font-weight: 550;
    color: #000000;
  }

  .days{
    .active {
      padding: 5px;
      background: #1abc9c;
      color: white !important
    }
    .grey{
      color: #999;
      font-weight: 450;
    }
    .select{
      background: #ccc;
    }
  } 

  /* 为小屏幕添加媒体查询 */
  @media screen and (max-width:720px) {
    .weekdays li, .days li {width: 13.1%;}
  }

  @media screen and (max-width: 420px) {
    .weekdays li, .days li {width: 12.5%;}
    .days li .active {padding: 2px;}
  }

  @media screen and (max-width: 290px) {
    .weekdays li, .days li {width: 12.2%;}
  }
  }

  .note{
    width: 49%;
    height: 500px;
    margin: 0 auto;
    text-align: center;
    background: #eee;
    border-radius: 15px;
    animation: fadeIn 1s;
    .bar{
      display: flex;
      justify-content: space-around;
      p{
        font-size: 36px;
        font-weight: 700;
        margin: 20px auto 20px 5%;
        color: #555;
      }
      button{
        margin: auto;
        height: 50px;
        width: 80px;
        margin: 20px 5% 20px auto;
        background: #1abc9c;
        border: #eee;
        border-radius: 8px;
        font-size: 22px;
        font-weight: 550;
        color: #fff;
      }
    }

    textarea{
      text-align: center;
      height: 78%;
      width: 96%;
      font-size: 20px;
      font-family: '微软雅黑';
      border-radius: 10px;
      outline: none;
    }
  }

}
.slide-fade-enter-active {
  transition: all 0.3s ease-out;
}

.slide-fade-leave-active {
  transition: all 0.4s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateX(20px);
  opacity: 0;
}

</style>
