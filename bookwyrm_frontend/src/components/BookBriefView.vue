<template>
  <div class="card w-100 h-100px">
    <div class="card-body">
      <router-link :to=detailRoute id="detail_button">
        <h2>{{this.title}}</h2>
        <h4>{{this.author}}</h4>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
    name: "SearchBar",
    props:{
      title: String,
      author: String,
      bookId: String
    },
    computed:{
      detailRoute(){
        return '/detail/' + this.bookId;
      }
    }
}
</script>
<style>
.h-100px{
  height: 100px;
}
.card {
  background-color: #343a3b;
}

</style>