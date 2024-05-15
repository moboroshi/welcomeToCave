<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import horizontal from '@/components/horizontal.vue'
import { onBeforeUnmount, onMounted, ref, shallowRef } from 'vue';
import { getSiteListAPI } from '@/apis/site';
import '@wangeditor/editor/dist/css/style.css';
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'


const siteList = ref([]);
const editorRef = shallowRef()
const mode = ref('default')

// 内容 HTML
const valueHtml = ref('<p>hello</p>')


const toolbarConfig = {}
const editorConfig = { placeholder: '请输入内容...' }

// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})

const handleCreated = (editor) => {
  editorRef.value = editor // 记录 editor 实例，重要！
}
const getSiteList = async()=>{
  const result = await getSiteListAPI('CSPro')
  siteList.value = result.data
}
onMounted(()=>{
    getSiteList()
    setTimeout(() => {
      valueHtml.value = '<p>请输入....</p>'
    }, 1500)
}
)
</script>

<template>
  <div>
    <horizontal :site-list="siteList.slice(0,6)"/>
    <horizontal :site-list="siteList.slice(6,12)"/>
  </div>
  <div class="container">
      <Toolbar
        style="border-bottom: 1px solid #ccc"
        :editor="editorRef"
        :defaultConfig="toolbarConfig"
        :mode="mode"
      />
      <Editor
        style="height: 500px; overflow-y: hidden;"
        v-model="valueHtml"
        :defaultConfig="editorConfig"
        :mode="mode"
        @onCreated="handleCreated"
      />
    </div>
</template>

<style scoped lang="scss">
.container {
  width: 90%;
  height: fit-content; 
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
  #editor—wrapper {
    height: 900px;
  }
}
</style>