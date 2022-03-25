<template>
  <div class="book_search">
    <div class="row justify-content-center">
      <div class="col-md-6 text-align-center card foreground btn highlight p-3 mt-4" @click="$router.push('/create')">
        <h3>create</h3>
      </div>
    </div>
    <div class="row justify-content-center">
      <div id="bookList" class="col-6 ">
        <div id="bookItem" v-for="book in bookList" :key="book.id">
          <BookBriefView class="m-3 highlight" :title="book.title"  :author="book.author" :bookId="book.id"/>
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
