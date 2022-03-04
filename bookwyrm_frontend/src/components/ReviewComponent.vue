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
    data() {
        return {
            focused: false,
            topic: null,
            //Contains hard coded test data for now
            reviews: [
                { 
                    name: "theUglyBarnacle/Spongebob", 
                    author: "Spongebob Squarepants", 
                    description: "A friend told me this story once when I was feeling bad about my appearance, it didn't help at all.", 
                    commentList: [], 
                    anonymous: true 
                },
                { 
                    name: "theUglyBarnacle/Plankton",
                    author: "Sheldon J. Plankton", 
                    description: "While the story is brief, I ultimately enjoyed the story overall, especially the ending.", 
                    commentList: [
                        {
                            identity:"Spongebob Squarepants/0",
                            author:"Spongebob Squarepants",
                            content:"Did you forget to turn on anonymous? This is something most people woudn't admit publicly.",
                            anonymous:true
                        },
                        {
                            identity:"Sheldon J. Plankton/0",
                            author:"Sheldon J. Plankton",
                            content:"I meant what I said you anonymous coward",
                            anonymous:false
                        }
                        ], 
                    anonymous: false 
                }
            ]
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