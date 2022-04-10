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
      username: "",
      genreList: [
        "Overall",
        "Adventure",
        "Action",
        "Bedtime",
        "Comedy/Humor",
        "Children's",
        "Drama",
        "Fantasy",
        "Gothic",
        "Horror",
        "Historical Fiction",
        "Alternate History",
        "Mystery",
        "Romance",
        "Sport",
        "Science-Fiction",
        "Thriller"
    ]
    }
  },
  getters: {
    getGenres(state){
      return state.genreList
    },
    getStrictGenres(state){
      return state.genreList.slice(1, state.genreList.length)
    }
  },
  mutations: {
    login (state, user) {
      if(user){
        state.username = user.username;
        localStorage.setItem("username", user.username);
        localStorage.setItem("authorFlag", user.authorFlag);
        if(user.authorFlag)
          localStorage.setItem("authorName", user.authorName);
        localStorage.setItem("journalistFlag", user.journalistFlag);
        if(user.journalistFlag)
          localStorage.setItem("journalistName", user.journalistName);
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
