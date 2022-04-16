<template>
  <router-link :to=detailRoute id="detail_button">
    <div class="card btn foreground highlight ">
      <div class="card-body">
          <h2>{{this.title}}</h2>
          <h4>{{this.author}}</h4>
      </div>
    </div>
  </router-link>
</template>

<script>
export default {
    name: "BookBriefComponent",
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