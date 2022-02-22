<template>
  <div class="header">
    <!-- This will eventually be an image -->
    <router-link to="/" id="home_button">home</router-link>
    <input type="text" id="search_bar" v-model="searchString" /> 
    <router-link to="/search" id="search_button">search</router-link>
  </div>
</template>

<script>
export default {
  name: 'SearchBar',
  data() {
    return {
      searchString: ""
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
