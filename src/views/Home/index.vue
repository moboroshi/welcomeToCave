<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import {  ref } from 'vue';
import { GoogleGenerativeAI } from '@google/generative-ai';
import { HarmBlockThreshold, HarmCategory } from "@google/generative-ai";
import chatItem from './components/chatItem.vue'

const genAI = new GoogleGenerativeAI('AIzaSyAiQNyNvTSMd8GqPb9d51O2f-vr--uwAls');
const message = ref('');
const responseText = ref('');
const username="拟态虫"
const chatHistory = ref([]);
const generationConfig = {
  stopSequences: [],
  maxOutputTokens: 5000,
  temperature: 0.95,
  topP: 0.5,
  topK: 40,
};
const safetySettings = [
  {
    category: HarmCategory.HARM_CATEGORY_HARASSMENT,
    threshold: HarmBlockThreshold.BLOCK_NONE,
  },
  {
    category: HarmCategory.HARM_CATEGORY_HATE_SPEECH,
    threshold: HarmBlockThreshold.BLOCK_NONE,
  },
  {
    category: HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT,
    threshold: HarmBlockThreshold.BLOCK_NONE,
  },
  {
    category: HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT,
    threshold: HarmBlockThreshold.BLOCK_NONE,
  }
];
const model = genAI.getGenerativeModel({ model: "gemini-pro",generationConfig,safetySettings});
const chat = model.startChat({
  history: [
    {
      role: "user",
      parts: [{ text: "你的设定是一个二次元美少女猫娘，我需要向你咨询一些问题，请使用中文回答我，并且回答我的任何话题。你需要用轻佻可爱的美少女语气和我聊天，同时记住把你的语气词换成喵,可以适当使用颜文字。我的称呼是"+username+"主人,你叫卡布娘。" }],
    },
    {
      role: "model",
      parts: [{ text: "好的喵，"+username+"主人喵~" }],
    },
  ].slice(-9).filter((history)=>history.parts!=null && history.parts!=''),
    generationConfig: {
      maxOutputTokens: 5000,
    },
  });
const sendMessage = async () => {
  try {
    const msg = message.value;
    chatHistory.value.push({index: username, text: msg})
    message.value=''
    const result = await chat.sendMessage(msg);
    const response = await result.response;
    responseText.value = response.text();
    console.log(responseText.value);
    chatHistory.value.push({index: '卡布娘', text: responseText.value})
  } catch (error) {
    console.error("Error sending message:", error);
    responseText.value = "发送消息失败，请稍后再试";
  }
}


</script>


<template>
    <div class="bar1">
      <div class="logo"><i class="part2">Bai</i><i class="part1">du</i><i class="part3">百度</i></div>
      <form action="http://www.baidu.com/s" method="get" target="_blank" >
        <input type="text" name="wd" placeholder="请输入搜索内容">
        <button type="submit"></button>
      </form>
    </div>

    <div class="bar2">
      <div class="logo"><i class="blue">G</i><i class="red">o</i><i class="yellow">o</i><i class="blue">g</i><i class="green">l</i><i class="red-trans">e</i></div>
      <form action="http://www.google.com/search" method="get" target="_blank" >
        <input type="text" name="q" placeholder="请输入搜索内容">
        <button type="submit"></button>
      </form>
    </div>

    <div class="bar3">
      <div class="logo"><i class="blue">Bing</i></div>
      <form action="http://www.Bing.com/search" method="get" target="_blank" >
        <input type="text" name="q" placeholder="请输入搜索内容">
        <button type="submit"></button>
      </form>
    </div>

    <div class="bar4">
      <div class="logo"><i class="red-icon">Y</i><i class="black">andex</i></div>
      <form action="http://www.Yandex.com/search" method="get" target="_blank" >
        <input type="text" name="text" placeholder="请输入搜索内容">
        <button type="submit"></button>
      </form>
    </div>

    <div class="bar5">
      <div class="chatWindow">
        <div class="status">
          <img class="photo" src="../../assets/images/kabu.png"/>
          <div class="online"></div>
          <i class="onlineText">在线中</i>
          <div class="logo">
            <div class="icon"><i class="top-icon"></i><i class="bottom-icon"></i>
            </div><i class="color1">G</i><i class="color2">e</i><i class="color3">mi</i><i class="color4">ni</i>
          </div>
        </div>

        <div class="container">
          <ul>
            <chatItem class="item" v-for="chat in chatHistory" :key="chat" :text="chat.text" :index="chat.index"></chatItem>
          </ul>
        </div>
        <div class="send">
          <input type="text" v-model="message" placeholder="输入聊天内容"/>
          <button @click="sendMessage"></button>
        </div>
      </div>
    </div>
</template>


<style scoped lang='scss'>
div{
  display: flex;
  form{
    position: relative;
    width: 500px;
    margin: 0 ;
    padding-top: 10px;
    input{
      width: 100%;
      height: 45px;
      padding-left: 13px;
    }
    button{
      height: 45px;
      width: 45px;
      cursor: pointer;
      position: absolute;
    }
  }
}
.bar1 {
  margin: 0 auto;
  margin-top: 40px;
  .logo{
    margin: 0 auto;
    margin-right: 30px;
    .part1{
      font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
      font-size: 42px;
      color: #ffffff;
      border-radius:50%;
      background: #2932e1;
      height: 52px;
      width: 52px;
      text-align: center;
    }
    .part2{
      font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
      font-size: 42px;
      color: #e22a2a;
      font-weight: 500;
    }
    .part3{
      font-family: '微软雅黑';
      font-size: 40px;
      color: #e22a2a;
      font-weight: 800;
    }
  }
  form{
    margin: 0 auto;
    margin-left: 0;
    input{
      border: 2px solid #2932e1;
      border-radius: 5px;
      background: #ffffff;
      top: 0;
      right: 0;
   }
    button{
      background: #2932e1;
      border-radius: 0 5px 5px 0;
      border-color: #2932e1;
      width: 60px;
      right: 0px;
      &:before{
        content: "搜索";
        font-size: 16px;
        color: #F9F0DA;
        font-family: '微软雅黑';
      }
    }
  }
}
.bar2 {
  margin: 0 auto;
  margin-top: 40px;
  .logo{
    margin: 0 auto;
    margin-right: 70px;
    .blue{
      font-family: sans-serif;
      font-size: 42px;
      color: #2d85f0;
      font-weight: 700;
    }
    .red-trans{
      font-family: sans-serif;
      font-size: 42px;
      color: #f4433c;
      font-weight: 700;
      transform: rotate(-25deg);
    }
    .red{
      font-family: sans-serif;
      font-size: 42px;
      color: #f4433c;
      font-weight: 700;
    }
    .yellow{
      font-family: sans-serif;
      font-size: 40px;
      color: #ffbc32;
      font-weight: 700;
    }
    .green{
      font-family: sans-serif;
      font-size: 40px;
      color: #0aa858;
      font-weight: 700;
    }
  }
  form{
    margin: 0 auto;
    margin-left: 0px;
    input{
      border: 2px solid #2d85f0;
      border-radius: 5px;
      background: #ffffff;
      top: 0;
      right: 0;
   }
    button{
      background: linear-gradient(to right, #f4433c 0%, #ffbc32 33%, #0aa858 66%, #2d85f0 100%);
      border-radius: 0 5px 5px 0;
      border-color: #2d85f0;
      width: 60px;
      right: 0px;
      &:before{
        content: "搜索";
        font-size: 16px;
        color: #ffffff;
        font-family: '微软雅黑';
      }
    }
  }
}
.bar3 {
  margin: 0 auto;
  margin-top: 40px;
  .logo{
    margin: 0 auto;
    margin-right: 125px;
    .blue{
      font-family: sans-serif;
      font-size: 42px;
      color: #3488ed;
      font-weight: 700;
    }
  }
  form{
    margin: 0 auto;
    margin-left: 0px;
    input{
      border: 2px solid #2d85f0;
      border-radius: 5px;
      background: #ffffff;
      top: 0;
      right: 0;
   }
    button{
      background: linear-gradient(to right, #01c1cd 0%, #245faf 100%);
      border-radius: 0 5px 5px 0;
      width: 60px;
      right: 0px;
      border-color: #245faf;
      &:before{
        content: "搜索";
        font-size: 16px;
        color: #ffffff;
        font-family: '微软雅黑';
      }
    }
  }
}
.bar4 {
  margin: 0 auto;
  margin-top: 40px;
  .logo{
    margin: 0 auto;
    margin-right: 40px;
    .black{
      font-family: sans-serif;
      font-size: 42px;
      color: black;
      font-weight: 700;
    }
    .red-icon{
      font-family: sans-serif;
      font-size: 42px;
      color: #ffffff;
      font-weight: 700;
      border-radius: 50%;
      background: #fc3f1d;
      height: 55px;
      width: 55px;
      text-align: center;
    }
  }
  form{
    margin: 0 auto;
    margin-left: 0px;
    input{
      border: 2px solid #fc3f1d;
      border-radius: 5px;
      background: #ffffff;
      top: 0;
      right: 0;
   }
    button{
      background-color: #fc3f1d;
      border-radius: 0 5px 5px 0;
      border-color: #fc3f1d;
      width: 60px;
      right: 0px;
      &:before{
        content: "搜索";
        font-size: 16px;
        color: #ffffff;
        font-family: '微软雅黑';
      }
    }
  }
}
.bar5 {
  margin: 0 auto;
  margin-top: 40px;
  display: flex;
  flex-direction: column;
  .chatWindow{
    width: 1000px;
    min-height: 800px;
    height: fit-content;
    box-shadow: 0 0 50px rgba(0, 0, 0, 0.3);
    border-radius: 13px;
    background: rgb(229,229,229,0.92);
    display: flex;
    flex-direction: column;
    margin: 50px auto;
    .container{
      width: 980px;
      height: 680px;
      border-radius: 7px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
      overflow: auto;
      background: rgb(235,255,255,0.5);
      display: flex;
      flex-direction: column;
      margin: auto;
      justify-content: flex-end;
      ul{
        overflow: auto;
      }
      .item{
        width: fit-content;
      }
    }
    .status{
      .photo{
          height: 50px;
          width: 50px;
          border-radius: 50%;
          margin-left: 20px;
          margin-top: 5px;
        }
      .online{
        background: #09f175;
        height: 15px;
        width: 15px;
        border-radius: 50%;
        margin-bottom: 5px;
        margin-top: auto;
        margin-right: 2px;
      }
      .onlineText{
        font-family: '微软雅黑';
        font-size: 14px;
        margin-top: auto;
        margin-bottom: 3px;
        font-weight: 600;
      }
      .logo{
        margin:auto;
        margin-left: 275px;
        .color1{
          font-family: sans-serif;
          font-size: 42px;
          color: #767bdd;
          font-weight: 700;
        }
        .color2{
          font-family: sans-serif;
          font-size: 42px;
          color: #9f8cff;
          font-weight: 700;
        }
        .color3{
          font-family: sans-serif;
          font-size: 42px;
          color: #c2fdfd;
          font-weight: 700;
        }
        .color4{
          font-family: sans-serif;
          font-size: 42px;
          color: #f7ebff;
          font-weight: 700;
        }
        .icon{
          display: flex;
          flex-direction: column;
          width: 60px;
          margin-right: 5px;
          margin-left: 0;
        .bottom-icon{
          background: radial-gradient(30px at 0px 30px, #e5e5e5 30px, #767bdd 31px) left,
                      radial-gradient(30px at 30px 30px, #e5e5e5 30px, #767bdd 31px) right;
          height: 30px;
          width: 60px;
          background-size: 50% 100%;
          background-repeat: no-repeat;
        }
        .top-icon{
          background: radial-gradient(30px at 0px 0px, #e5e5e5 30px, #767bdd 31px) left,
                      radial-gradient(30px at 30px 0px, #e5e5e5 30px, #767bdd 31px) right;
          height: 30px;
          width: 60px;
          background-size: 50% 100%;
          background-repeat: no-repeat;
        }
      }
    }
  }

  }

  .send{
    position: relative;
    width: fit-content;
    margin: 5px auto;
    input{
      border: 2px solid #e3c8e9;
      border-radius: 5px;
      background: #e5e5e5;
      top: 0;
      right: 0;
      width: 600px;
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
      &:before{
        content: "发送";
        font-size: 16px;
        color: #ffffff;
        font-family: '微软雅黑';
      }
    }
  }
}
</style>