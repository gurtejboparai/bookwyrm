<template>
    <div class="container">
        <div class="focused_view" v-if="focused">
            <div>
                <h5>{{topic.author}}</h5>
                <p>{{topic.description}}</p>
                <button v-on:click="hide_detail()">show all reviews</button>
            </div>
        </div>
        <div class="list_view" v-else>
            <div v-for="review in reviews" v-bind:key="review.name">
                <h5>{{review.author}}</h5>
                <p>{{review.description}}</p>
                <button v-on:click="show_detail(review)">show full review and comments</button>
            </div>
        </div>
    </div>
</template>

<script>
    export default{
        name:'review_component',
        data() {
            return {
                focused:false,
                topic:null,
                //Contains hard coded test data for now
                reviews:[
                    {name:'theUglyBarnacle/Spongebob',
                    author:'Spongebob Squarepants',
                    description:'A friend told me this story once when I was feeling bad about my appearance, it didn\'t help at all.',
                    comment_list:{},
                    annonymous:true},
                    {name:'theUglyBarnacle/Plankton',
                    author:'Sheldon J. Plankton',
                    description:'While the story is brief, I ultimately enjoyed the story overall, especially the ending.',
                    comment_list:{},
                    annonymous:false}
                ]
            }
        },
        methods: {
            show_detail(review){
                this.topic=review
                this.focused=true
            },
            hide_detail(){
                this.focused=false
                this.topic=null
            }

        },
    }
</script>