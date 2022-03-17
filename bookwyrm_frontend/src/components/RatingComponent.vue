<template>
    <div>
        <h3>Ratings</h3>
        <div v-if="!displayOnly">
            <button>
                Add another rating
            </button>
        </div>
        <div id="ratingSpace">
            <div v-for="rating in ratings" v-bind:key="rating.genre" class="ratingDisp">
                
                <h3 v-if="displayOnly">{{rating.genre}}</h3>
                <select name="genreSelection" id="genreSelector" v-else>
                    <option value="Select a genre"></option>
                    <option v-for="genre in genres" :key="genre"></option>
                </select>
                <StarRating 
                    v-bind:rating="rating.score"
                    v-bind:read-only="displayOnly"
                    v-bind:max-rating="5"
                    v-bind:increment="0.5"
                />
                <button v-if="!displayOnly">X</button>

            </div>
        </div>
    </div>
</template>

<script>
import StarRating from 'vue-star-rating'
    export default{
        name: "RatingComponent",
        //The property "displayOnly" is UI specific and is therfore not in the database
        props: ['ratings', 'displayOnly'],
        data() {
            return {
                genres:[
                    "Overall",
                    "Adventure",
                    "Action",
                    "Bedtime",
                    "Comedy/Humor",
                    "Children's",
                    "Drama",
                    "Fantasy",
                    "Gothic",
                    "Horror",
                    "Historical Fiction",
                    "Alternate History",
                    "Mystery",
                    "Romance",
                    "Sport",
                    "Science-Fiction",
                    "Thriller"
                ]
            }
        },
        methods: {
            
        },
        components: {
            StarRating
        }
    }
</script>

<style scoped>
    #ratingSpace{
        display: block;
        background-color: #2a2a2e;
        padding: 5px;
    }
    .ratingDisp{
        display: block;
        background-color: #343438;
        padding: 5px;
        margin: 5px;
        border-radius: 5px;
    }
</style>