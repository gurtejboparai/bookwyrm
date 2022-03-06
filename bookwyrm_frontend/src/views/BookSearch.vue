<template>
  <div class="book_search">
    <div class="row justify-content-center">
      <div class="col-md-6 text-align-center card">
        <router-link to="/create" id="create_button" style="text-align: center" class="m-3">create</router-link>
      </div>
    </div>
    <div class="row justify-content-center">
      <div id="bookList" class="col-md-6">
        <div id="bookItem" v-for="book in bookList" :key="book.id">
          <BookBriefView class="m-3" v-bind:title="book.title"  v-bind:author="book.author" v-bind:bookId="book.id"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BookService from "@/services/BookService"
import BookBriefView from "@/components/BookBriefView"
export default {
  name: 'Book Search',
  props:{
    searchTerm: String
  },
  data(){
    return {
      bookList : []
    }
  },
  created(){
    BookService.searchBook(this.searchTerm).then(response => this.bookList = response.data.bookDaoList);
  },
  components: {BookBriefView}
}
</script>
