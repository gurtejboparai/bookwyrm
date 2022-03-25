import "bootstrap/dist/css/bootstrap.min.css"
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import {createStore} from 'vuex'

const store = createStore({
  state () {
    return {
      username: ""
    }
  },
  mutations: {
    login (state, username) {
      state.username = username;
      localStorage.setItem("username", username)
    },
    logout (state) {
      state.username = "";
      localStorage.removeItem("username")
    }
  }
})

createApp(App).use(router).use(VueAxios, axios).use(store).mount('#app')
