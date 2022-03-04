<template>
    <div id="container">
        <div id="focusedView" v-if="focused">
            <div class="revDisplay">
                <h5>{{topic.author}}</h5>
                <p>{{topic.description}}</p>
                <button v-on:click="hideDetail()">show all reviews</button>
                <h4>Comments</h4>
                <div></div>
                <div>
                    <div v-for="comment in topic.commentList" v-bind:key="comment.identity">
                        <CommentComponent
                            v-bind:identity="comment.identity"
                            v-bind:author="comment.author"
                            v-bind:content="comment.content"
                            v-bind:anonymous="comment.anonymous"
                        />
                    </div>
                </div>
            </div>
        </div>
        <div id="listView" v-else>
            <div v-for="review in reviews" v-bind:key="review.name" class="revDisplay">
                <h5>{{review.author}}</h5>
                <p>{{review.description}}</p>
                <button v-on:click="showDetail(review)">show full review and comments</button>
            </div>
        </div>
    </div>
</template>

<script>
import CommentComponent from "./CommentComponent.vue"
export default{
    name: "ReviewComponent",
    props: ['reviews'],
    data() {
        return {
            focused: false,
            topic: null,
            //Contains hard coded test data for now
            
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
        margin: 10px;
        border-radius: 10px;
    }
</style>