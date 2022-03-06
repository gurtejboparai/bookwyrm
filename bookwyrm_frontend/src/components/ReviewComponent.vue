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
                    <textarea name="reviewInput" id="reviewTextBox" cols="30" rows="10" 
                        placeholder="Write your review here" v-model="newReviewText"></textarea>
                    <input type="submit" value="POST">
                </form>
                </details>
                
            </div>
            <div v-for="review in reviews" v-bind:key="review.reviewId" class="revDisplay">
                <h5>{{review.user}}</h5>
                <p>{{review.content}}</p>
                <button v-on:click="showDetail(review)">show full review and comments</button>
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
        display: block;
        background-color: #34383a;
        padding: 5px;
        margin: 5px;
        border-radius: 5px;
    }
    #commentTextBox{
        display: block;
        text-justify: start;
        width:100%;
        height: 4em;
    }
    #reviewCreator{
        background-color: #34383a;
        padding: 5px;
        margin: 5px;
        border-radius: 5px;
    }
    #reviewTextBox{
        display:block;
        width:100%;
    }
</style>