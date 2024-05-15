<script setup>
import { useScroll } from '@vueuse/core'
import { useUserStore } from "@/stores/user";
import { ref } from "vue";

const userInfo = ref({})
const userStore = useUserStore()
userInfo.value = userStore.userInfo;

const confirm = ()=>{
  userStore.clearUserInfo()
  console.log(userInfo)
}
const { y } = useScroll(window)
</script>

<template>
  <div class="app-header-sticky" :class="{show: y>78}">
    <nav class="app-topnav">
    <div>
      <ul>
        <div class="logo">
          <i class="cave">CA</i><div class="mid"><i class="from">from</i><i class="escape">escape</i></div><i class="cave">VE</i>
        </div>
        <template v-if="true">
          <li><a href="javascript:;"><i></i>{{ userInfo.nickname }}</a></li>
          <li>
            <el-popconfirm title="确认退出吗?" confirm-button-text="确认" cancel-button-text="取消" @confirm="confirm">
              <template #reference>
                <a href="javascript:;">退出登录</a>
              </template>
            </el-popconfirm>
          </li>
          <li><a href="javascript:;">个人中心</a></li>
        </template>
        <template v-else>
          <li><a href="javascript:;">登录</a></li>
          <li><a href="javascript:;">帮助中心</a></li>
          <li><a href="javascript:;">关于我们</a></li>
        </template>
      </ul>
    </div>
  </nav>
  </div>
</template>


<style scoped lang='scss'>
.app-header-sticky {
  width: 100%;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 999;
  background-color: #333;
  border-bottom: 1px solid #333;
  // 此处为关键样式!!!
  // 状态一：往上平移自身高度 + 完全透明
  transform: translateY(-100%);
  opacity: 0;

  // 状态二：移除平移 + 完全不透明
  &.show {
    transition: all 0.3s linear;
    transform: none;
    opacity: 1;
  }

  .app-topnav {
  background: #333;
    .logo{
      display: flex;
      margin-left: 3%;
      margin-right: auto;
      .mid{
        display: flex;
        flex-direction: column;
        text-align: center;
        margin-top: 10px;
        .from{
          font-size: 16px;
          font-family: 'Microsoft Yahei';
          color: white;
          line-height: 80%;
          font-weight: 550;
        }
        .escape{
          font-size: 20px;
          font-family: sans-serif;
          line-height: 80%;
          color: #52ff96;
          font-weight: 600;
        }
      }
      .cave{
        font-size: 36px;
        font-family: sans-serif;
        color: rgb(219, 213, 213);
        font-weight: 700;
      }
    }
    ul {
      display: flex;
      height: 53px;
      justify-content: flex-end;
      align-items: center;
      li {
        a {
          padding: 0 15px;
          color: #cdcdcd;
          line-height: 1;
          display: inline-block;

          i {
            font-size: 14px;
            margin-right: 2px;
          }

          &:hover {
            color: $xtxColor;
          }
        }

        ~li {
          a {
            border-left: 2px solid #666;
          }
        }
      }
    }
  }

}

</style>