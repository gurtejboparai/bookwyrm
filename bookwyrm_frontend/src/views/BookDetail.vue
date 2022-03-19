<template>
  <div class="BookDetail">
    <div class="container justify-content-center">
      <div class="row">
        <div class="col-6 foreground w-50 mt-3 p-3">
          <h2>{{bookDetails.title}}</h2>
          <h5>By {{bookDetails.author}}</h5>
        </div>
      </div>
      <div class="row">
        <div class="col-6 foreground w-100 mt-3 p-3">
          <ReviewComponent
              :reviews="bookDetails.reviewList"
              :bookTitle="bookDetails.title"
              :bookAuthor="bookDetails.author"
              @addNewReview="addReview"
              @addNewComment="addComment"
            />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ReviewComponent from "@/components/ReviewComponent.vue";
import BookService from "@/services/BookService";
import ReviewService from "@/services/ReviewService";
import CommentService from "@/services/CommentService";
export default {
    name: "Book Detail",
    data() {
        return {
          bookDetails:{
            title: "loading",
            author: "loading",
            description: "loading",
            //Hard coded sample data for now in the future this should retrieve data from the backend
            reviewList: [
                { 
                    reviewId:0,
                    bookId: "loading", 
                    author: "loading", 
                    content: "loading", 
                    commentList: [], 
                    anonymous: true 
                },
                { 
                    reviewId:1,
                    bookId: "loading",
                    author: "loading", 
                    content: "loading", 
                    commentList: [
                        {
                            commentId:0,
                            author:"loading",
                            content:"loading",
                            anonymousFlag:true
                        },
                        {
                            commentId:1,
                            author:"loading",
                            content:"loading",
                            anonymousFlag:false
                        }
                        ], 
                    anonymous: false 
                }
            ]
        }
        };
    },
    methods: {
      addReview(newReview){
        ReviewService.postReview(this.$route.params.bookId, this.$store.state.username, newReview, false);
      },
      loadBookDetails(){
        BookService.searchBookDetail(this.$route.params.bookId).then(response => this.bookDetails = response.data.bookDao);
      },
      addComment(newComment){
        CommentService.uploadComment(newComment.reviewId, this.$store.state.username, newComment.content, false);
      }
    },
    components: { ReviewComponent},
    created(){
      this.loadBookDetails();
    }
}
</script>
