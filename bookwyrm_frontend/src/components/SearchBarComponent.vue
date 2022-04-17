<template>
  <div class="d-flex justify-content-between foreground">
      <div class="col p-2">
        <router-link to="/" id="home_button" >
          <img src="../assets/logo.png" alt="logo" class="p-1" id="logo">
        </router-link>
      </div>
      <div class="col">
        <div class=" p-4 text-center input-group">
          <input type="text" id="search_bar" class="form-control" v-model="searchString" placeholder="Search"/> 
          <router-link :to="searchRoute" class="btn btn-primary ">Go!</router-link>
        </div>
      </div>
      <div class="col p-2">
        <UserComponent/>
      </div>
  </div>
</template>

<script>
import UserComponent from '@/components/UserComponent.vue'
export default {
    name: "SearchBarComponent",
    data() {
        return {
            searchString: null
        };
    },
    computed:{
      searchRoute(){
        return '/search/'+this.searchString;
      }
    },
    components: { UserComponent }
}
</script>
<style scoped>
#logo {
  height: 10vh;
  border-radius: 30%;
}
</style>
