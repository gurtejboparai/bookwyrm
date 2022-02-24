<template>
  <SearchBar/>
  <Logger/>
  <router-view/>
</template>
<script>
import SearchBar from '@/components/SearchBar.vue'
import Logger from '@/components/Logger.vue'

export default {
  name: 'Home',
  components: {
      SearchBar,
      /*I put it here because I think it would be nice to always have access to the login/logout abilities*/ 
      Logger
  }
}
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

#search_bar {
  margin: 1em;
}
</style>
