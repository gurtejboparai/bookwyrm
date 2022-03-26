<template>
    <div id="container">
        <div id="focusedView"  v-if="focused">
            <div>
                <RatingComponent
                    v-bind:displayOnly="true"
                    v-bind:initialRatings="topic.ratingsList"
                />
            </div>
            <button @click="hideDetail()" class="btn btn-light">show all reviews</button>
            <div class=" p-3">
                <div class="revDisplay card mt-4 p-2 shadow-sm">
                    <h2>{{(topic.author == "" || topic.author == null) ? "- Guest -" : topic.author }}</h2>
                    <p class="p-4">{{topic.content}}</p>
                </div>
                <div class="pt-3">
                    <h4 class="text-align-center">Comments</h4>
                    <div class="card foreground p-3 shadow-sm m-3">
                        <details>
                            <summary>Add a comment</summary>
                            <form @submit="postComment">
                                <textarea name="commentInput" id="commentTextBox" v-model="newCommentText" placeholder="Write your comment here" class="rounded w-100 mt-3"></textarea>
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
                            v-bind:displayOnly="false"
                            v-model:ratings="newReviewRatingsList"
                        />
                        <input type="submit" class="btn btn-success mt-2">
                    </form>
                </details>
            </div>
            <div v-for="review in reviews" v-bind:key="review.reviewId" class="revDisplay card mt-4 p-2 shadow-sm">
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
            newReviewText:"",
            newReviewRatingsList: []
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
            this.$emit('addNewComment', {content: this.newCommentText, reviewId: this.topic.id});
        },

        postReview(){
            this.$emit('addNewReview', this.newReviewText, this.newReviewRatingsList);
        }
    },
    components: { CommentComponent, RatingComponent }
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
</style>