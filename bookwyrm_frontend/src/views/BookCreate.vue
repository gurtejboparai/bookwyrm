<template>
  <div class="container p-4" id="book_create">
    <div class="row justify-content-center p-2 pt-4">
      <div class="col-sm-12 col-md-4">
        <label for="isbn" class="text-center">ISBN:</label>
      </div>
    </div>
    <div class="row justify-content-center p-2 text-center">
      <div class="col-sm-12 col-md-4">
        <input type="text" id="isbn" class="form-control" v-model="isbn" placeholder="The Book's ISBN"/>
        <button @click="searchAutofill" class="btn btn-light m-2">Autofill</button>
      </div>
    </div>

    <div class="row justify-content-center p-2 pt-4">
      <div class="col-sm-12 col-md-4">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-lock" viewBox="0 0 16 16" v-if="autofilled">
          <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2zM5 8h6a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V9a1 1 0 0 1 1-1z"/>
        </svg>
        <label for="name" class="text-center">Title:</label>
      </div>
    </div>
    <div class="row justify-content-center p-2 text-center">
      <div class="col-sm-12 col-md-4">
        <input type="text" id="name" class="form-control" v-model="bookName" placeholder="The Book's Title" :disabled="autofilled"/>
      </div>
    </div>

    <div class="row justify-content-center p-2 pt-4">
      <div class="col-sm-12 col-md-4">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-lock" viewBox="0 0 16 16" v-if="autofilled">
          <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2zM5 8h6a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V9a1 1 0 0 1 1-1z"/>
        </svg>
        <label for="author">Author:</label>
      </div>
    </div>
    <div class="row justify-content-center p-2  text-center">
      <div class="col-sm-12 col-md-4">
        <input type="text" id="author" class="form-control" v-model="bookAuthor" placeholder="The Book's Author" :disabled="autofilled"/>
      </div>
    </div>

    <div class="row justify-content-center p-2 pt-4">
      <div class="col-sm-12 col-md-4">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-lock" viewBox="0 0 16 16" v-if="autofilled">
          <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2zM5 8h6a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V9a1 1 0 0 1 1-1z"/>
        </svg>
        <label for="desc">Description:</label>
      </div>
    </div>
    <div class="row justify-content-center p-2  text-center">
      <div class="col-sm-12 col-md-4">
        <textarea  id="desc" class="form-control" v-model="bookDesc" placeholder="The Book's Desc" :disabled="autofilled"/>
      </div>
    </div>
    <div class="row justify-content-center p-2 pt-4 text-center">
      <div class="col-sm-12 col-md-4">
        <button @click="submitBook" class="btn btn-success m-2">
          Submit
        </button>
        <button @click="clearLock" class="btn btn-danger m-2" v-if="autofilled">
          Clear
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
        bookAuthor: "",
        isbn:"",
        bookDesc:"",
        autofilled:false
      }
    },
    methods: {
      submitBook() {
        BookService.uploadBook(this.bookName, this.bookAuthor, this.bookDesc)
          .then((response) => this.$router.push('/detail/' + response.data.bookId) );
      },
      searchAutofill(){
        if(this.isbn!="")
          BookService.searchBookViaGoogle(this.isbn).then((response)=>{
            if(response.data.totalItems == 0){
              alert("Book with ISBN "+this.isbn+" not found.")
            }else{
              this.bookName = response.data.items[0].volumeInfo.title;
              this.bookAuthor = response.data.items[0].volumeInfo.authors[0];
              this.bookDesc = response.data.items[0].volumeInfo.description;
              this.autofilled = true;
            }
          })
        else
          alert("ISBN required for autofill")
      },
      clearLock(){
        this.bookName= "";
        this.bookAuthor= "";
        this.bookDesc= "";
        this.isbn= "";
        this.autofilled= false;
      }
    }
  }
</script>
<style>
textarea{
  height:10em;
}
</style>