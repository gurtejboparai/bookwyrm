<template>
    <div id="container">
        <div id="focusedView" v-if="focused">
            <div class="revDisplay">
                <h5>{{topic.author}}</h5>
                <p>{{topic.content}}</p>
                <button v-on:click="hideDetail()">show all reviews</button>
                <div>
                    <div>
                        <h5>Post a comment</h5>
                        <form v-on:submit="postComment">
                            <textarea name="commentInput" id="commentTextBox" v-model="newCommentText" rows="10" placeholder="Write your comment here"></textarea>
                            <input type="submit" value="POST">
                        </form>
                    </div>
                </div>
                <div>
                    <h4>Comments</h4>
                    <div v-for="comment in topic.commentList" v-bind:key="comment.commentId">
                        <CommentComponent
                            v-bind:commentId="comment.commentId"
                            v-bind:author="comment.author"
                            v-bind:content="comment.content"
                            v-bind:anonymousFlag="comment.anonymousFlag"
                        />
                    </div>
                </div>
            </div>
        </div>
        <div id="listView" v-else>
            <div id="reviewCreator">
                <details>
                    <summary>Add a review</summary>
                    <form v-on:submit="postReview">
                        <textarea name="reviewInput" id="reviewTextBox" class="rounded w-100 mt-3" 
                            placeholder="Write your review here" v-model="newReviewText"></textarea>
                        <input type="submit" class="btn btn-success mt-2" value="POST">
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
import CommentComponent from "./CommentComponent.vue"
export default{
    name: "ReviewComponent",
    props: ['reviews', 'bookTitle', 'bookAuthor'],
    data() {
        return {
            focused: false,
            topic: null,
            newCommentText: "",
            newReviewText:""
        };
    },
    calculated:{
        author(){
            return 
        }
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
            this.$emit('addNewReview', this.newReviewText);
        }
    },
    components: { CommentComponent }
}
</script>

<style scoped>
    .revDisplay{
        background-color: #3a3e41;
    }
    #commentTextBox{
        display: block;
        text-justify: start;
        width:100%;
        max-height: 4em;
    }
    #reviewCreator{
        background-color: #34383a;
        padding: 5px;
        margin: 5px;
        border-radius: 5px;
    }
    #reviewTextBox{
        max-height: 15em;
        min-height: 2em;
    }
</style>