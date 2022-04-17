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
  color: #8c271e;
  font-size: 1.5em;
  background-color: #d9f7fa;
}

html {
  background-color: #d9f7fa;
}

a {
  font-weight: bold;
  color: #8c271e;
  text-decoration: none;
}

a:hover {
  color: rgb(192, 88, 78);
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
  background-color: #d8ddde;
  color: #8c271e;
  border-width: 0px;
  box-shadow: 1px 2px #bec1c1;
  border-radius: 3px;
}
.foreground-light{
  background-color: #e5e9eb;  
  box-shadow: 1px 5px #bec1c1;
  border-radius: 3px;
}
.tail{
  box-shadow: 1px 5px #bec1c1;  
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
background: rgb(140,39,30);
background: linear-gradient(90deg, rgba(140,39,30,1) 12%, rgba(229,233,235,1) 12%, rgba(229,233,235,1) 88%, rgba(140,39,30,1) 88%);
}

::-webkit-scrollbar {
    width: 12px;
}
body{
  overflow-x:hidden
}
::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 1px rgba(255, 255, 255, 0.2); 
    /* border-radius: 10px; */
}

::-webkit-scrollbar-thumb {
    /* border-radius: 10px; */
    -webkit-box-shadow: inset 0 0 3px #8c271e2e;
    border-radius:2px; 
    background-color: #bcb2a4e4;
}
.highlight:hover {
  background-color: #aba194;
  box-shadow: 1px 3px #91897f;
  color: #eae0d9;
}

.btn-bw {
  background-color: #8c271e;
  color: #eae0d9;
  font-weight: bold;
}
.btn-bw:hover {
  background-color: #9d3127;
  color: #d7c8bd;
  font-weight: bold;
}
.btn-bw:focus {
  border-color: #bcb2a4e4;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px #bcb2a4e4;
  outline: 0 none;
}

.btn-bw-light {
  background-color: #fe9e3f;
  color: #ffffff;
  font-weight: bold;
}
.btn-bw-light:hover {
  background-color: #cb843e;
  color: #ffffff;
  font-weight: bold;
}
.btn-bw-light:focus {
  border-color: #bcb2a4e4;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px #bcb2a4e4;
  outline: 0 none;
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
  border-color: #bcb2a4e4;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px #bcb2a4e4;
  outline: 0 none;
}
</style>
