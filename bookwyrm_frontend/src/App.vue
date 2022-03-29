<template>
  <div>
    <div class="span">
      <SearchBar/>
    </div>

    <router-view/>
  </div>
</template>


<script>
import SearchBar from '@/components/SearchBar.vue'


export default {
  name: 'Home',
  components: {
      SearchBar
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
}

a.router-link-exact-active {
  color: #c4e0e9;
}
.foreground{
  background-color: #34383a;
  color: #c4e0e9;
  box-shadow: 1px 5px #222425;
}
::-webkit-scrollbar {
    width: 12px;
}
body{
  overflow-x:hidden
}
::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 3px rgba(255, 255, 255, 0.2); 
    /* border-radius: 10px; */
}

::-webkit-scrollbar-thumb {
    /* border-radius: 10px; */
    -webkit-box-shadow: inset 0 0 6px rgb(27, 27, 27); 
    background-color: rgb(126, 52, 52);
}
.highlight:hover {
  background-color: #3f4346;
  box-shadow: 2px 6px #222425;
  color: #b3c9d1;
}
</style>
