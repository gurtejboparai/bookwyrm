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
:root{
  --darkest: #232729;
  --darker: #343738;
  --dark: #354347;
  --light: #3a5f67;
  --lighter: #88b7f6;
  --lighter-hover: #368cfd;
  --alt-light:#b57248;
  --alt-light-hover:#ff8133;
  --lightest: #cbf1fa;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: var(--lightest);
  font-size: 1.5em;
  background-color: var(--darkest);
}

html {
  background-color: var(--darkest);
}

a {
  font-weight: bold;
  color: var(--lighter);
  text-decoration: none;
}

a:hover {
  color: var(--lightest);
}
.bubble {
  border-radius: 5%;
  border-width: 10px;
  border-style: double;
  width: fit-content;
  margin:auto;
  border-color: var(--lightest);
  text-align: center;
}
.foreground{
  background-color: var(--darker);
  color: var(--lightest);
  border-width: 0px;
  box-shadow: 1px 2px var(--darker);
  border-radius: 3px;
}
.foreground-light{
  background-color: var(--dark);  
  box-shadow: 1px 5px var(--dark);
  border-radius: 3px;
}
.tail{
  box-shadow: 1px 5px var(--dark);  
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
background: linear-gradient(90deg, var(--lighter) 12%, var(--darker) 12%, var(--darker) 88%, var(--lighter) 88%);
}
.bookCarousel:hover{
background: linear-gradient(90deg, var(--lighter-hover) 12%, var(--darker) 12%, var(--darker) 88%, var(--lighter-hover) 88%);
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
    background-color: var(--dark);
}
.highlight:hover {
  background-color: var(--light);
  color: inherit;
  box-shadow: 1px 3px rgba(50, 78, 86, 0.45);
}

.btn-bw {
  background-color: var(--lighter);
  color: var(--dark);
  font-weight: bold;
}
.btn-bw:hover {
  background-color: var(--lighter-hover);
  color: var(--lightest);
  font-weight: bold;
}
.btn-bw:focus {
  border-color: var(--lighter);
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px var(--lighter);
  outline: 0 none;
}
.highlightedReview {
  border-top-color: var(--lighter);
  border-top-style: groove;
  border-top-width: 10px;
}
.professionalJournalistHeader {
  border-color: var(--lighter);
  border-style: groove;
  border-width: 10px;
}
.btn-bw-light {
  background-color: var(--alt-light);
  color: var(--dark);
  font-weight: bold;
}
.btn-bw-light:hover {
  background-color: var(--alt-light-hover);
  color: var(--lightest);
  font-weight: bold;
}
.btn-bw-light:focus {
  border-color: var(--alt-light);
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px var(--alt-light);
  outline: 0 none;
}
#logo {
  height: 10vh;
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
  border-color: var(--lighter);
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px var(--lighter);
  outline: 0 none;
}
</style>
