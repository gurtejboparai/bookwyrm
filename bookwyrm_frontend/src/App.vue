<template>
  <div>
    <div class="span">
      <SearchBarComponent/>
    </div>

    <router-view/>
  </div>
</template>


<script>
import SearchBarComponent from '@/components/SearchBarComponent.vue'


export default {
  name: 'Home',
  components: {
      SearchBarComponent
  },
  created(){
    if(localStorage.getItem("username"))
      this.$store.commit('login',
      {
        username: localStorage.getItem("username"),
        authorFlag: localStorage.getItem("authorFlag"),
        authorName: localStorage.getItem("authorName"),
        journalistFlag: localStorage.getItem("journalistFlag"),
        journalistName: localStorage.getItem("journalistName")
      });
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: rgb(203, 241, 250);
  font-size: 1.5em;
  background-color: #2f3b3e;
}

html {
  background-color: rgb(47,59,62);
}

a {
  font-weight: bold;
  color: rgb(136,193,208);
  text-decoration: none;
}

a:hover {
  color: rgb(170, 216, 229);
}
.bubble {
  border-radius: 5%;
  border-width: 10px;
  border-style: double;
  width: fit-content;
  margin:auto;
  border-color:#d8ddde;
  text-align: center;
}
.foreground{
  background-color: rgb(54,74,78);
  color: rgb(203, 241, 250);
  border-width: 0px;
  box-shadow: 1px 2px rgba(54, 74, 78, 0.535);
  border-radius: 3px;
}
.foreground-light{
  background-color: rgb(50,78,86);  
  box-shadow: 1px 5px rgba(50, 78, 86, 0.45);
  border-radius: 3px;
}
.tail{
  box-shadow: 1px 5px rgba(50, 78, 86, 0.45);  
  border-radius: 3px;
  border-top-left-radius: 0px;
  border-top-right-radius: 0px;
}
.head{
  border-radius: 3px;
  border-bottom-left-radius: 0px;
  border-bottom-right-radius: 0px;
}
.bookCarousel{
background: linear-gradient(90deg, rgb(136,193,208) 12%, rgb(54,74,78) 12%, rgb(54,74,78) 88%, rgb(136,193,208) 88%);
}

::-webkit-scrollbar {
    width: 12px;
}
body{
  overflow-x:hidden
}
::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 1px rgba(136,193,208, 0.2); 
    /* border-radius: 10px; */
}

::-webkit-scrollbar-thumb {
    /* border-radius: 10px; */
    -webkit-box-shadow: inset 0 0 3px rgba(136,193,208, 0.2);
    border-radius:2px; 
    background-color: rgba(136,193,208, 0.2);
}
.highlight:hover {
  background-color: rgb(50,78,86);
  box-shadow: 1px 3px rgba(50, 78, 86, 0.45);
  color:inherit
}

.btn-bw {
  background-color: rgb(136,193,208);
  color: rgb(54,74,78);
  font-weight: bold;
}
.btn-bw:hover {
  background-color: rgb(153, 211, 226);
  color: rgb(255, 255, 255);
  font-weight: bold;
}
.btn-bw:focus {
  border-color: #bcb2a4e4;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px #bcb2a4e4;
  outline: 0 none;
}
.highlightedReview {
  border-top-color: rgb(153, 211, 226);
  border-top-style: groove;
  border-top-width: 10px;
}
.professionalJournalistHeader {
  border-color: rgb(153, 211, 226);
  border-style: groove;
  border-width: 10px;
}
.btn-bw-light {
  background-color: rgb(208, 116, 59);
  color: rgb(54,74,78);
  font-weight: bold;
}
.btn-bw-light:hover {
  background-color: rgb(208, 116, 59);
  color: rgb(255, 255, 255);
  font-weight: bold;
}
.btn-bw-light:focus {
  border-color: rgb(208, 116, 59);
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgb(208, 116, 59);
  outline: 0 none;
}
#logo {
  height: 15vh;
  border-radius: 30%;
}
textarea:focus,
input[type="text"]:focus,
input[type="password"]:focus,
input[type="datetime"]:focus,
input[type="datetime-local"]:focus,
input[type="date"]:focus,
input[type="month"]:focus,
input[type="time"]:focus,
input[type="week"]:focus,
input[type="number"]:focus,
input[type="email"]:focus,
input[type="url"]:focus,
input[type="search"]:focus,
input[type="tel"]:focus,
input[type="color"]:focus,
.uneditable-input:focus {   
  border-color: rgb(153, 211, 226);
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgb(153, 211, 226);
  outline: 0 none;
}
</style>
