import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/detail/:bookId',
    name: 'Detail',
    component: () => import(/* webpackChunkName: "detail" */ '../views/BookDetail.vue')
  },
  {
    path: '/search/:searchTerm',
    name: 'Search',
    component: () => import(/* webpackChunkName: "search" */ '../views/BookSearch.vue'),
    props: true
  },
  {
    path: '/create',
    name: 'Create',
    component: () => import(/* webpackChunkName: "create" */ '../views/BookCreate.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "login" */ '../views/Login.vue')
  },
  {
    path: '/user',
    name: 'User',
    component: () => import(/* webpackChunkName: "login" */ '../views/UserDetails.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
