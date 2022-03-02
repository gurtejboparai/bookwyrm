<template>
    <div id="container">
        <div id="focusedView" v-if="focused">
            <div class="revDisplay">
                <h5>{{topic.author}}</h5>
                <p>{{topic.description}}</p>
                <button v-on:click="hideDetail()">show all reviews</button>
                <div>
                    <p>Comments Component goes here</p>
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
    export default{
        name:'ReviewComponent',
        data() {
            return {
                focused:false,
                topic:null,
                //Contains hard coded test data for now
                reviews:[
                    {name:'theUglyBarnacle/Spongebob',
                    author:'Spongebob Squarepants',
                    description:'A friend told me this story once when I was feeling bad about my appearance, it didn\'t help at all.',
                    commentList:{},
                    annonymous:true},
                    {name:'theUglyBarnacle/Plankton',
                    author:'Sheldon J. Plankton',
                    description:'While the story is brief, I ultimately enjoyed the story overall, especially the ending.',
                    commentList:{},
                    annonymous:false}
                ]
            }
        },
        methods: {
            showDetail(review){
                this.topic=review
                this.focused=true
            },
            hideDetail(){
                this.focused=false
                this.topic=null
            }

        },
    }
</script>

<style scoped>
    .revDisplay{
        display: block;
        background-color: #34383a;
        padding: 5px;
        margin: 10px;
    }
</style>