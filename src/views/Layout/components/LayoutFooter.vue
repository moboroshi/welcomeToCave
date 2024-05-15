<template>
  <footer class="app_footer">
    <!-- 联系我们 -->
    <div class="contact">
      <div class="container">
        <dl>
          <dt>客户服务</dt>
          <dd>有问题联系唯一指定邮箱:<br/>2991818165@qq.com</dd>
        </dl>
        <dl>
          <dt>关注我们</dt>
          <dd><i class="iconfont icon-weixin"></i> 公众号</dd>
          <dd><i class="iconfont icon-weibo"></i> 微博</dd>
        </dl>
      </div>
    </div>
  </footer>
</template>

<style scoped lang='scss'>
.app_footer {
  overflow: hidden;
  position: relative;
  box-shadow: 0 0 20px rgba($color: #000000, $alpha: 0.2);
  .contact {
    background: rgb(255,255,255,0.5);
    backdrop-filter: blur(5px);
    .container {
      padding: 20px 0 40px 25px;
      display: flex;
      justify-content: center;
    }

    dl {
      height: 120px;
      text-align: center;
      padding: 0 72px;
      border-right: 1px solid #f2f2f2;
      color: #999;
      &:first-child {
        padding-left: 0;
      }

      &:last-child {
        border-right: none;
        padding-right: 0;
      }
    }

    dt {
      line-height: 1;
      font-size: 18px;
    }

    dd {
      margin: 36px 12px 0 0;
      float: left;
      width: fit-content;
      height: 92px;
      padding-top: 10px;
      &:hover {
        .iconfont {
          color: $xtxColor;
        }
      }
    }
  }
}
</style>