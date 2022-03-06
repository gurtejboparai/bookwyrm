<template>
  <div class="container p-4" id="book_create">
    <div class="row justify-content-center p-2">
      <div class="col-sm-12 col-md-4">
        <label for="name" class="text-center">Name</label>
      </div>
    </div>
    <div class="row justify-content-center p-2">
      <div class="col-sm-12 col-md-4">
        <input type="text" id="name" v-model="bookName"/>
      </div>
    </div>
    <div class="row justify-content-center p-2">
      <div class="col-sm-12 col-md-4">
        <label for="author">Author</label>
      </div>
    </div>
    <div class="row justify-content-center p-2">
      <div class="col-sm-12 col-md-4">
        <input type="text" id="author" v-model="bookAuthor" />
      </div>
    </div>
    <div class="row justify-content-center p-2">
      <div class="col-sm-12 col-md-4">
        <button @click="submitBook">
          Submit
        </button>
      </div>
    </div>
    
  </div>
</template>
<script>
import BookService from '@/services/BookService';
  export default {
    name: 'Home',
    data() {
      return {
        bookName: "",
        bookAuthor: ""
      }
    },
    methods: {
      submitBook() {
        BookService.uploadBook(this.bookName, this.bookAuthor)
          .then((response) => this.$router.push('/detail/' + response.data.bookId) );
      }
    }
  }
</script>