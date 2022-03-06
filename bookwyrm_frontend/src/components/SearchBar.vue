<template>
  <div class="d-flex justify-content-between ">
    <div class="p-2">
      <router-link to="/" id="home_button" >
        <img src="../assets/logo.png" alt="logo" class="p-1" id="logo">
      </router-link>
    </div>
    <div class="p-4">
      <input type="text" id="search_bar" class="m-2" v-model="searchString" /> 

      <router-link :to="{name:'Search', params:{searchTerm:searchString}}" class="btn btn-primary m-2">search</router-link>
    </div>
    <div class="p-2">
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
.btn-primary {
  background-color: #c28061;
  border-color: #803f21;
}
#logo {
  height: 15vh;
  background-color: #34383a;
  border-radius: 30%;
  box-shadow: 1px 5px #222425;
}
</style>
