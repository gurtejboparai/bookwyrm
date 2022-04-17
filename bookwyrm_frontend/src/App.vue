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
  color: #ffb8ad;
  font-size: 1.5em;
  background-color: #140c0b;
}

html {
  background-color: #140c0b;
}

a {
  font-weight: bold;
  color: #ffb8ad;
  text-decoration: none;
}

a:hover {
  color: #fdaea1;
}
.bubble {
  border-radius: 5%;
  border-width: 10px;
  border-style: double;
  width: fit-content;
  margin:auto;
  border-color:#fdaea1;
  text-align: center;
}
.foreground{
  background-color: #211c14;
  color: #ffb8ad;
  border-width: 0px;
  box-shadow: 1px 2px #211c1462;
  border-radius: 3px;
}
.foreground-light{
  background-color: #2d261c;  
  box-shadow: 1px 5px #211c1462;
  border-radius: 3px;
}
.tail{
  box-shadow: 1px 5px #211c1462;  
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
background: #f2b749;
background: linear-gradient(90deg, #f2b749 12%, #1c140f 12%, #1c140f 88%, #f2b749 88%);
}

::-webkit-scrollbar {
    width: 12px;
}
body{
  overflow-x:hidden
}
/* ::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 1px rgba(255, 255, 255, 0.2); 
} */

::-webkit-scrollbar-thumb {
    /* border-radius: 10px; */
    -webkit-box-shadow: inset 0 0 3px #8c271e2e;
    border-radius:2px; 
    background-color: #bcb2a4e4;
}
.highlight:hover {
  background-color: #302a1f;
  box-shadow: 1px 3px #302a1f;
  color: #eae0d9;
}

.btn-bw {
  background-color: #f2b749;
  color: #211c14;
  font-weight: bold;
}
.btn-bw:hover {
  background-color: #ffca69;
  color: #0d0909;
  font-weight: bold;
}
.btn-bw:focus {
  border-color: #f2b749e4;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px #bcb2a4e4;
  outline: 0 none;
}

.btn-bw-light {
  background-color: #ffb8ad;
  color: #211c14;
  font-weight: bold;
}
.btn-bw-light:hover {
  background-color: #ffd4cd;
  color: rgb(11, 8, 7);
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
