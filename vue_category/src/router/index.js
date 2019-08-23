import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

// 导入刚才编写的组件
import AppIndex from '@/components/home/AppIndex'
import Login from '@/components/Login'
import Home from '@/components/Home'
import CategoryIndex from '@/components/category/CategoryIndex'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      // home页面不需要被访问
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex,
          // 需要拦截的路由加入元数据
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/category',
          name: 'Category',
          component: CategoryIndex,
          // 需要拦截的路由加入元数据
          meta: {
            requireAuth: true
          }
        }
      ]
    }
  ]
})
