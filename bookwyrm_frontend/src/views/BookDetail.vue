<template>
  <div class="BookDetail">
    
    <div id="enclosure">
      <div id="left_side">
        
        <div id="info_block">
          <h2>{{bookDetails.title}}</h2>
          <h5>By {{bookDetails.author}}</h5>
        </div>
        
        <div id="description_block">
          <p>{{bookDetails.description}}</p>
        </div>

        <div id="ratings_block">
          <RatingComponent
          v-bind:displayOnly="true"
          v-bind:ratings="ratingsData"
          />
        </div>
        
      </div>

      <div id="right_side">
        <div id="reviews_block">
          
          <ReviewComponent
            v-bind:reviews="bookDetails.reviewList"
            v-bind:bookTitle="bookDetails.title"
            v-bind:bookAuthor="bookDetails.author"
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
import RatingComponent from "@/components/RatingComponent.vue";
import BookService from "@/services/BookService";
import ReviewService from "@/services/ReviewService";
import CommentService from "@/services/CommentService";
export default {
    name: "Book Detail",
    data() {
        return {
          ratingsData: [
            {
              genre: "Fantasy",
              score: 3.0
            },
            {
              genre: "Adventure",
              score: 4.5
            }
          ],
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
    components: { ReviewComponent, RatingComponent },
    created(){
      this.loadBookDetails();
    }
}
</script>


<style scoped>
  #book_detail{
    width: 100%;
  }
  #enclosure{
    display: flex;
    flex-direction: row;
    flex-flow: row nowrap;
    justify-content: stretch;
    align-items: stretch;
  }
  #left_side{
    flex-direction: column;
    flex-flow: column nowrap;
    flex-grow: 1;
    min-width: 25%;
    max-width: 33%;
    background-color: #34383a;
    padding: 5px;
    margin: 10px;
    border-radius: 10px;
  }
  #right_side{
    flex-direction: column;
    flex-flow: column nowrap;
    flex-grow: 2;
    margin: 10px;
    min-width: 50%;
    max-width: 67%;
  }
  #info_block{
    display:flex;
    flex-direction: column;
    flex-grow: 1;
  }
  #description_block{
    display:flex;
    flex-direction: column;
    flex-grow: 4;
  }
  #ratings_block{
    display:flex;
    flex-direction: column;
    flex-grow: 3;
  }
  #reviews_block{
    display: flex;
    flex-direction: column;
  }
  
</style>
