<template>
  <div class="span">
    <SearchBar/>
  </div>
  
  <router-view/>
</template>


<script>
import SearchBar from '@/components/SearchBar.vue'


export default {
  name: 'Home',
  components: {
      SearchBar
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

</style>
