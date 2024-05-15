import { fileURLToPath, URL } from'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import ElementPlus from 'unplugin-element-plus/vite'


// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver({ importStyle: "sass" })],
    }),
    ElementPlus({
      useSource: true
    })
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  css: {
    preprocessorOptions: {
      scss: {
        // 自动导入定制化样式文件进行样式覆盖
        additionalData: `
          @use "@/styles/element/index.scss" as *;
          @use "@/styles/var.scss" as *;
        `,
      }
    }
  },
  devServer: {                //记住，别写错了devServer//设置本地默认端口  选填
    port: 5173,
    proxy: {                 //设置代理，必须填
    '/api': {              //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定
    target: 'http://localhost:8080',     //代理的目标地址
    changeOrigin: true,              //是否设置同源，输入是的
    pathRewrite: {                   //路径重写
    '/api': '' //选择忽略拦截器里面的单词
                   }
               }
           }
       }

})
