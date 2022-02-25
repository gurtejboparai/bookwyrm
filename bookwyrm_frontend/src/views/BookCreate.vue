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
        alert("Book submitting attempted for book with name: "+this.bookName+" and author: "+this.bookAuthor);
      }
    }
  }
</script>