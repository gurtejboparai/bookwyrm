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
  color: #c4e0e9;
  font-size: 1.5em;
  background-color: #2a2e2f;
}

html {
  background-color: #2a2e2f;
}

a {
  font-weight: bold;
  color: #c4e0e9;
  text-decoration: none;
}

a:hover {
  color: #c4e0e9;
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
  background-color: #34383a;
  color: #c4e0e9;
  border-width: 0px;
  box-shadow: 1px 2px #36393a;
  border-radius: 3px;
}
.foreground-light{
  background-color: #44484a;  
  box-shadow: 1px 5px #36393a;
  border-radius: 3px;
}
.tail{
  box-shadow: 0px 1px #36393a;  
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
background: linear-gradient(90deg, rgba(140,39,30,1) 12%, #34383a 12%, #34383a 88%, rgba(140,39,30,1) 88%);
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
    background-color: #44484a;
}
.highlight:hover {
  background-color: #44484a;
  box-shadow: 3px 5px #393c3d;
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
  border-color: #9d3127;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px #9d312780;
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
  border-color: #cb843e;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px #cb843e80;
  outline: 0 none;
}
.highlightedReview {
  border-top-color: #ffc285;
  border-top-style: groove;
  border-top-width: 10px;
}
.professionalJournalistHeader {
  border-color: #ffc284;
  border-style: groove;
  border-width: 10px;
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
