<template>
  <div class="BookDetail" v-if="pageLoaded">
    <div class="container justify-content-center">
      <!-- Book Info -->
      <div class="row justify-content-center">
        <div class="justify-content-center text-center mt-3 p-3">
          <div class="bubble p-5">
            <h1 class="display-4">{{ bookDetails.title }}</h1>
            <h3>By {{ bookDetails.author }}</h3>
            
            <h5 v-if="bookDetails.genre">{{ bookDetails.genre }}</h5>
          </div>
          
           <!-- Avg Rating -->
          <RatingComponent
            class="foreground mt-4"
            v-bind:displayOnly="true"
            v-bind:ratings="bookDetails.avg"
          />
          <hr>
          <!-- Editing Mode -->
          <div v-if="editing">
            <select v-model="genreEdit" class="col-4 form-select w-50 mb-3">
              <option value="null" selected>No Genre</option>
              <option
                v-for="genre in genres"
                v-bind:key="genre"
                v-bind:value="genre"
              >
                {{ genre }}
              </option>
            </select>
            <textarea
              class="w-100"
              id=""
              cols="30"
              rows="10"
              v-model="descEdit"
            ></textarea>
          </div>
          <div v-else>
            <p>{{ bookDetails.description }}</p>
          </div>
          <!-- Edit / Cancel / Save Buttons -->
          <div>
            <div
              class="btn btn-bw"
              @click="
                editing = !editing;
                descEdit = bookDetails.description;
                genreEdit = bookDetails.genre;
              "
              v-if="isAuthor"
            >
              {{ editing ? "Cancel" : "" }}
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="16"
                height="16"
                fill="currentColor"
                class="bi bi-pencil"
                viewBox="0 0 16 16"
                v-if="!editing"
              >
                <path
                  d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"
                />
              </svg>
            </div>
            <div class="btn btn-bw-light m-3" @click="updateDesc" v-if="editing">
              Update
            </div>
            <div
              class="spinner-border text-light"
              role="status"
              v-if="saving"
            ></div>
          </div>
          <br />
         
        </div>
      </div>
      <!-- Review List -->
      <div class="row">
        <div class="col-6  w-100 mt-3 p-3">
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
import RatingComponent from "@/components/RatingComponent.vue";
import BookService from "@/services/BookService";
import ReviewService from "@/services/ReviewService";
import CommentService from "@/services/CommentService";
export default {
  name: "Book Detail",
  data() {
    return {
      editing: false,
      descEdit: "",
      genreEdit: "",
      saving: false,
      pageLoaded: false,
      ratingsData: null,
    };
  },
  methods: {
    addReview(newReview) {
      ReviewService.postReview(
        this.$route.params.bookId,
        this.$store.state.username ? this.$store.state.username : "Guest",
        newReview.reviewText,
        newReview.reviewAnonymousFlag,
        newReview.reviewRatingsList,
        newReview.reviewJournalistFlag,
        localStorage.getItem("journalistName") != undefined
          ? localStorage.getItem("journalistName")
          : null
      );
    },
    addComment(newComment) {
      CommentService.uploadComment(
        newComment.reviewId,
        this.$store.state.username ? this.$store.state.username : "Guest",
        newComment.content,
        newComment.commentAnonymousFlag
      );
    },
    updateDesc() {
      this.saving = true;
      BookService.searchBookUpdate(
        this.bookDetails.id,
        this.descEdit,
        this.genreEdit
      )
        .then((response) => {
          this.bookDetails = response.data.bookDao;
        })
        .finally(() => {
          this.saving = false;
          this.editing = false;
        });
    },
  },
  components: { ReviewComponent, RatingComponent },
  mounted() {
    BookService.searchBookDetail(this.$route.params.bookId).then((response) => {
      this.bookDetails = response.data.bookDao;
      this.pageLoaded = true;
    });
  },
  computed: {
    isAuthor() {
      return (
        localStorage.getItem("authorName") == this.bookDetails.author &&
        localStorage.getItem("authorFlag") == "true"
      );
    },
    
    genres(){
      return this.$store.getters.getStrictGenres
    },
  },
};
</script>
