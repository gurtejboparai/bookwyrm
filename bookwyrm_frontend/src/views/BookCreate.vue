<template>
  <div class="book_create">
    CREATE
    <button @click="foobar">btn</button>
  </div>
</template>
<script>
import BookService from '@/services/BookService';

export default {
  methods:{
    foobar(){
      BookService.uploadBook("name","author")
        .then((response) => (alert(response?.data?.bookName)));
    }
  }
}
</script>