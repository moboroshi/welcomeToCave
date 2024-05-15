
import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/views/Login/index.vue'
import Layout from '@/views/Layout/index.vue'
import Home from '@/views/Home/index.vue'
import Surf from '@/views/Surf/index.vue'
import ACG from '@/views/ACG/index.vue'
import Game from '@/views/Game/index.vue'
import CSPro from '@/views/CSPro/index.vue'
import Language from '@/views/Language/index.vue'
import NetTool from '@/views/NetTool/index.vue'
import MessageBoard from '@/views/MessageBoard/index.vue'
import Other from '@/views/Other/index.vue'




const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: Layout,
      children: [
        {
          path: '',
          name:'Home',
          component: Home
        },
        {
          path:'surf',
          name:'Surf',
          component:Surf
        },
        {
          path:'acg',
          name:'ACG',
          component:ACG
        },
        {
          path:'language',
          name:'Language',
          component:Language
        },
        {
          path:'cs-pro',
          name:'CSPro',
          component:CSPro
        },
        {
          path:'game',
          name:'Game',
          component:Game
        },
        {
          path:'net-tool',
          name:'NetTool',
          component:NetTool
        },
        {
          path:'message-board',
          name:'MessageBoaard',
          component:MessageBoard
        },
        {
          path:'other',
          name:'Other',
          component:Other
        },
      ]
    },
    {
      path: '/login',
      component: Login
    }
  ]
})

export default router
