<template>
  <div id="container">
    <div id="focusedView" v-if="focused">
      <div class="p-3">
        <div class="revDisplay card mt-4 foreground">

          <button @click="hideDetail()" class="btn btn-bw head">
            show all reviews
          </button>
          
          <div class="p-5">
            <div
              v-if="topic.journalistReview"
              class="text-center professionalJournalistHeader m-0"
            >
              Professional Reviewer: {{ topic.journalistName }}
            </div>
            <h2 class="pt-2">
              {{
                topic.user == "" || topic.user == null ? "- Guest -" : topic.user
              }}
            </h2>
            <div>
              <RatingComponent
                v-bind:displayOnly="true"
                v-bind:ratings="topic.ratings"
              />
            </div>
            <br>
            <div class="row">
              
              <div class="col-1 m-2 btn-lg">
                <VotingComponent
                  :reviewId="topic.id"
                  :downVoteUserList="topic.downVoteIdsList"
                  :upVoteUserList="topic.upVoteIdsList"
                />
              </div>
              <div class="col-10">
                <p class="p-4">{{ topic.content }}</p>
              </div>
            </div>
          </div>
          <div class="pt-3">
          <div class="foreground-light tail p-3  ">
              <form @submit="postComment">
                <textarea
                  name="commentInput"
                  id="commentTextBox"
                  v-model="newCommentText"
                  placeholder="Write your comment here"
                  class="rounded w-100 mt-3"
                ></textarea>
                <div class="form-check form-switch">
                  <input class="form-check-input" type="checkbox" id="flexSwitchCheckChecked"  v-model="newCommentAnonymousFlag">
                  <label class="form-check-label" for="flexSwitchCheckChecked">Post Anonymously</label>
                </div>
                <input type="submit" class="btn btn-bw mt-2" />
              </form>
            <div
            v-for="comment in topic.commentList"
            :key="comment.commentId"
            class="pt-3 p-2"
          >
            <CommentComponent
              :commentId="comment.commentId"
              :author="comment.author"
              :content="comment.content"
              :anonymousFlag="comment.anonymousFlag"
            />
          </div>
          </div>
          
        </div>
        </div>
        
      </div>
    </div>

    <div id="listView" v-else>
      <div id="" class="card p-3 foreground">
        <details>
          <summary>Add a review</summary>
          <form @submit="postReview">
            <textarea
              name="reviewInput"
              id="reviewTextBox"
              class="rounded w-100 mt-3"
              placeholder="Write your review here"
              v-model="newReviewText"
            ></textarea>
            <RatingComponent
              v-if="isLoggedIn"
              v-bind:displayOnly="false"
              v-model:ratings="newReviewRatingsList"
            />
            <span v-else class="text-danger">
              You must log in to add star ratings to your review
            </span>
            <hr>
            <div class="form-check form-switch">
              <input class="form-check-input" type="checkbox" id="newReviewAnonymousFlagChecked"  v-model="newReviewAnonymousFlag">
              <label class="form-check-label" for="newReviewAnonymousFlagChecked">Post Anonymously</label>
            </div>
            <div v-if="isJournalist">
              <div class="form-check form-switch">
                <input class="form-check-input" type="checkbox" id="newReviewJournalistFlagChecked"  v-model="newReviewJournalistFlag">
                <label class="form-check-label" for="newReviewJournalistFlagChecked">Highlight review</label>
              </div>
            </div>
            <br />
            <input type="submit" class="btn btn-bw-light mt-2" />
          </form>
        </details>
      </div>
      <div
        v-for="review in reviews"
        v-bind:key="review.reviewId"
        :class="this.isHighlightedReview(review.journalistReview)"
      >
      <h2 class="p-2">{{ review.user == "" ? "- Guest -" : review.user }}</h2>
        <RatingComponent
          v-bind:displayOnly="true"
          v-bind:ratings="review.ratings"
        />
        <div class=" p-2">
          <div class="row">
            <div class="col-1 m-2 btn-lg">
              <VotingComponent
                :reviewId="review.id"
                :downVoteUserList="review.downVoteIdsList"
                :upVoteUserList="review.upVoteIdsList"
              />
            </div>
            <div class="col-10">
              <p class="p-4">{{ review.content }}</p>
            </div>
          </div>
        </div>
        <div @click="showDetail(review)" class="btn btn-bw tail">
          show comments
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import VotingComponent from "@/components/VotingComponent";
import RatingComponent from "./RatingComponent.vue";
import CommentComponent from "./CommentComponent.vue";
export default {
  name: "ReviewComponent",
  props: ["reviews", "bookTitle", "bookAuthor"],
  data() {
    return {
      focused: false,
      topic: null,
      newCommentText: "",
      newCommentAnonymousFlag: false,
      newReviewText: "",
      newReviewRatingsList: {},
      newReviewAnonymousFlag: false,
      newReviewJournalistFlag: false,
    };
  },
  methods: {
    showDetail(review) {
      this.topic = review;
      this.focused = true;
    },

    hideDetail() {
      this.focused = false;
      this.topic = null;
    },

    postComment() {
      this.$emit("addNewComment", {
        content: this.newCommentText,
        reviewId: this.topic.id,
        commentAnonymousFlag: this.newCommentAnonymousFlag,
      });
    },

    postReview() {
      this.$emit("addNewReview", {
        reviewText: this.newReviewText,
        reviewAnonymousFlag: this.newReviewAnonymousFlag,
        reviewRatingsList: this.newReviewRatingsList,
        reviewJournalistFlag: this.newReviewJournalistFlag,
      });
    },
    isHighlightedReview(journalistFlag) {
      return "revDisplay card mt-5 shadow-sm foreground ".concat(
        journalistFlag ? "highlightedReview" : ""
      );
    },
  },
  components: { CommentComponent, RatingComponent, VotingComponent },
  computed: {
    isLoggedIn() {
      return this.$store.state.username != "";
    },
    isJournalist() {
      return localStorage.getItem("journalistFlag") == "true";
    },
    genres(){
      return this.$store.getters.getGenres
    },
  },
  created(){
    
    this.newReviewRatingsList = {}
      this.genres.forEach(category=> {
        this.newReviewRatingsList[category]=0
      })
  },
};
</script>

<style scoped>
#commentTextBox {
  max-height: 5em;
  min-height: 2em;
}
#reviewTextBox {
  max-height: 20em;
  min-height: 2em;
}
.highlightedReview {
  border-top-color: #c69c72;
  border-top-style: groove;
  border-top-width: 10px;
}
.professionalJournalistHeader {
  border-color: #c69c72;
  border-style: groove;
  border-width: 10px;
}
</style>
