<template>
  <div class="d-flex justify-content-between" id="header">
      <div class="col p-2">
        <router-link to="/" id="home_button" >
          <img src="../assets/logo.png" alt="logo" class="p-1" id="logo">
        </router-link>
      </div>
      <div class="col p-3 text-center">
        <input type="text" id="search_bar" class="m-2" v-model="searchString" placeholder="Search"/> 
        <router-link :to="{name:'Search', params:{searchTerm:searchString}}" class="btn btn-primary m-2 mt-1">Go!</router-link>
      </div>
      <div class="col p-2">
        <userComponent/>
      </div>
  </div>
</template>

<script>
import userComponent from '@/components/userComponent.vue'
export default {
    name: "SearchBar",
    data() {
        return {
            searchString: ""
        };
    },
    components: { userComponent }
}
</script>
<style scoped>
#header{
  background-color: #34383a;
  box-shadow: 1px 5px #222425;
}
#logo {
  height: 10vh;
  border-radius: 30%;
}
</style>
