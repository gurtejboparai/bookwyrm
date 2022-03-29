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
    login (state, user) {
      if(user){
        state.username = user.username;
        localStorage.setItem("username", user.username);
        localStorage.setItem("authorFlag", user.authorFlag);
        localStorage.setItem("authorName", user.authorName);
        localStorage.setItem("journalistFlag", user.profJournalistFlag);
        localStorage.setItem("journalistName", user.profJournalistName);
      }
    },
    logout (state) {
      state.username = "";
      localStorage.removeItem("username");
      localStorage.removeItem("authorFlag");
      localStorage.removeItem("authorName");
      localStorage.removeItem("journalistFlag");
      localStorage.removeItem("journalistName");
    }
  }
})

createApp(App).use(router).use(VueAxios, axios).use(store).mount('#app')
