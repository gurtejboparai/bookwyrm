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
        <label for="name" class="text-center">Title:</label>
      </div>
    </div>
    <div class="row justify-content-center p-2 text-center">
      <div class="col-sm-12 col-md-4">
        <input type="text" id="name" class="form-control" v-model="bookName" placeholder="The Book's Title"/>
      </div>
    </div>
    <div class="row justify-content-center p-2 pt-4">
      <div class="col-sm-12 col-md-4">
        <label for="author">Author:</label>
      </div>
    </div>
    <div class="row justify-content-center p-2  text-center">
      <div class="col-sm-12 col-md-4">
        <input type="text" id="author" class="form-control" v-model="bookAuthor" placeholder="The Book's Author"/>
      </div>
    </div>
    <div class="row justify-content-center p-2 pt-4">
      <div class="col-sm-12 col-md-4">
        <label for="genre">Genre (Optional):</label>
      </div>
    </div>
    <div class="row justify-content-center p-2  text-center">
      <div class="col-sm-12 col-md-4">
        <select id="genre" v-model="genreEdit" class="form-select w-50 mb-3">
          <option value="null" selected>No Genre</option>
          <option
            v-for="genre in genres"
            v-bind:key="genre"
            v-bind:value="genre"
          >
            {{ genre }}
          </option>
        </select>
      </div>
    </div>
    <div class="row justify-content-center p-2 pt-4">
      <div class="col-sm-12 col-md-4">
        <label for="desc">Description:</label>
      </div>
    </div>
    <div class="row justify-content-center p-2  text-center">
      <div class="col-sm-12 col-md-4">
        <textarea  id="desc" class="form-control" v-model="bookDesc" placeholder="The Book's Desc"/>
      </div>
    </div>
    <div class="row justify-content-center p-2 pt-4 text-center">
      <div class="col-sm-12 col-md-4">
        <button @click="submitBook" class="btn btn-success m-2">
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
        bookAuthor: "",
        isbn:"",
        bookDesc:"",
        genreEdit: "",
      }
    },
    methods: {
      submitBook() {
        BookService.uploadBook(this.bookName, this.bookAuthor, this.bookDesc, this.isbn, this.genreEdit)
          .then((response) => this.$router.push('/detail/' + response.data.bookId) );
      },
      searchAutofill(){
        if(this.isbn!=""){
          this.isbn = this.isbn.replace('-',"");
          BookService.searchBookViaGoogle(this.isbn).then((response)=>{
            if(response.data.totalItems == 0){
              alert("Book with ISBN "+this.isbn+" not found. Try an alternative ISBN. \nNot all ISBNs are autofillable.")
            }else{
              this.bookName = response.data.items[0].volumeInfo.title;
              this.bookAuthor = response.data.items[0].volumeInfo.authors[0];
              this.bookDesc = response.data.items[0].volumeInfo.description;
            }
          })
        }else
          alert("ISBN required for autofill")
      },
      clearLock(){
        this.bookName= "";
        this.bookAuthor= "";
        this.bookDesc= "";
        this.isbn= "";
      }
    },
    computed: {
      genres(){
        return this.$store.getters.getGenres
      }
    }
  }
</script>
<style>
textarea{
  height:10em;
}
</style>