<template>
    <div>
        <h3>Ratings</h3>
        <div v-if="!displayOnly">
            <button v-on:click.prevent="addRating">
                Add another rating
            </button>
        </div>
        <div id="ratingSpace">
            <div v-for="(rating, index) in ratings" v-bind:key="index" class="ratingDisp">
                
                <h3 v-if="displayOnly">{{rating.genre}}</h3>
                <select name="genreSelection" id="genreSelector" v-else v-model.lazy="rating.genre">
                    <option value="" disabled selected>Select a genre</option>
                    <option v-for="genre in genres" v-bind:key="genre" v-bind:value="genre">{{genre}}</option>
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
        //The property "initialRatings" is used as an initial value for ratings in the data
        //The property "displayOnly" is UI specific and is therfore not in the database
        props: ['initialRatings', 'displayOnly'],
        data() {
            return {
                ratings: this.initialRatings,
                //This is a temporary substitute for the planned genre enum
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
            addRating(){
                this.ratings.push({genre:"Select a genre", score:0.5})
            }
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
    #genreSelector{
        display:inline-flex;
        min-width: 20ch;
    }
    .ratingDisp{
        display: block;
        flex-direction: row;
        flex-flow: row;
        background-color: #343438;
        padding: 5px;
        margin: 5px;
        border-radius: 5px;
    }
    option{
        color:black;
    }
</style>