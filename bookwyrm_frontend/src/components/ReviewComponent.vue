<template>
    <div id="container">
        <div id="focusedView" v-if="focused">
            
            <button @click="hideDetail()" class="btn btn-light">show all reviews</button>
            <div class=" p-3">
                <div class="revDisplay card mt-4 p-2 shadow-sm foreground">
                    <div v-if="(topic.journalistReview)" class="text-center professionalJournalistHeader m-0">Professional Reviewer: {{topic.journalistName}}</div>
                    <h2 class="pt-2">{{(topic.user == "" || topic.user == null) ? "- Guest -" : topic.user }}</h2>
                    <div>
                        <RatingComponent
                            v-bind:displayOnly="true"
                            v-bind:initialRatings="topic.ratingsList"
                        />
                    </div>
                    <p class="p-4">{{topic.content}}</p>
                </div>
                <div class="pt-3">
                    <h4 class="text-align-center">Comments</h4>
                    <div class="card foreground p-3 shadow-sm m-3">
                        <details>
                            <summary>Add a comment</summary>
                            <form @submit="postComment">
                                <textarea name="commentInput" id="commentTextBox" v-model="newCommentText" placeholder="Write your comment here" class="rounded w-100 mt-3"></textarea>
                                Post Anonymously <input type="checkbox" v-model="newCommentAnonymousFlag"/>
                                <br>
                                <input type="submit" class="btn btn-success mt-2">
                            </form>
                        </details>
                    </div>
                    <div v-for="comment in topic.commentList" :key="comment.commentId" class="pt-3 p-2">
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


        <div id="listView" v-else>
            <div id="reviewCreator" class="card foreground p-3 shadow-sm">
                <details>
                    <summary>Add a review</summary>
                    <form @submit="postReview">
                        <textarea name="reviewInput" id="reviewTextBox" class="rounded w-100 mt-3" 
                            placeholder="Write your review here" v-model="newReviewText"></textarea>
                        <RatingComponent
                            v-if="isLoggedIn"
                            v-bind:displayOnly="false"
                            v-model:ratings="newReviewRatingsList"
                        />
                        <h2 v-else class="text-danger">You must log in to add star ratings to your review</h2>
                        Post Anonymously <input type="checkbox" v-model="newReviewAnonymousFlag"/>
                        <div v-if="isJournalist">Highlight review <input type="checkbox" v-model="newReviewJournalistFlag"/></div>
                        <br>
                        <input type="submit" class="btn btn-success mt-2">
                    </form>
                </details>
            </div>
            <div v-for="review in reviews" v-bind:key="review.reviewId" :class="this.isHighlightedReview(review.journalistReview)">
                <h2>{{(review.user == "") ? "- Guest -" : review.user }}</h2>
                <p class="p-4">{{review.content}}</p>
                <div @click="showDetail(review)" class="btn btn-light">show comments</div>
            </div>
        </div>
    </div>
</template>



<script>
import RatingComponent from "./RatingComponent.vue"
import CommentComponent from "./CommentComponent.vue"
export default{
    name: "ReviewComponent",
    props: ['reviews', 'bookTitle', 'bookAuthor'],
    data() {
        return {
            focused: false,
            topic: null,
            newCommentText: "",
            newCommentAnonymousFlag:false,
            newReviewText:"",
            newReviewRatingsList: {
                "Overall":0,
                "Adventure":0,
                "Action":0,
                "Bedtime":0,
                "Comedy/Humor":0,
                "Children's":0,
                "Drama":0,
                "Fantasy":0,
                "Gothic":0,
                "Horror":0,
                "Historical Fiction":0,
                "Alternate History":0,
                "Mystery":0,
                "Romance":0,
                "Sport":0,
                "Science-Fiction":0,
                "Thriller":0
                },
            newReviewAnonymousFlag:false,
            newReviewJournalistFlag:false
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

        postComment(){
            this.$emit('addNewComment', {content: this.newCommentText, reviewId: this.topic.id, commentAnonymousFlag: this.newCommentAnonymousFlag});
        },

        postReview(){
            this.$emit('addNewReview', 
            {
                reviewText: this.newReviewText, 
                reviewAnonymousFlag: this.newReviewAnonymousFlag, 
                reviewRatingsList: this.newReviewRatingsList, 
                reviewJournalistFlag: this.newReviewJournalistFlag
            });
        },
        isHighlightedReview(journalistFlag){
            return ("revDisplay card mt-4 p-2 shadow-sm foreground ".concat((journalistFlag)?"highlightedReview":""));
        }
    },
    components: { CommentComponent, RatingComponent },
    computed: {
        isLoggedIn() {
            return this.$store.state.username != "";
        },
        isJournalist(){
            return localStorage.getItem("journalistFlag") == 'true'
        }
    }
}
</script>

<style scoped>
    .revDisplay{
        background-color: #3a3e41;
    }
    #commentTextBox{
        max-height: 5em;
        min-height: 2em;
    }
    #reviewTextBox{
        max-height: 20em;
        min-height: 2em;
    }
    .highlightedReview{
        border-top-color: #e1f878;
        border-top-style:groove;
        border-top-width: 10px;
    }
    .professionalJournalistHeader{
        border-color: #e1f878;
        border-style:groove;
        border-width: 10px;
    }
</style>