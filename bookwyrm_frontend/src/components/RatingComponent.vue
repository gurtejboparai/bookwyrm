<template>
    <div>
        <h3>Ratings</h3>
        <div v-if="!displayOnly">
            <button v-on:click.prevent="addRating" class="btn btn-light mb-1">
                Add a rating
            </button>
        </div>
        <div></div>
        <div id="ratingSpace" class="p-1">
            <div v-for="(genreRating, index) in ratings" :key="genreRating.ratingId" 
                class="ratingDisp m-2 p-2 rounded row">
                
                <h3 v-if="displayOnly" class="col-4">{{genreRating.genre}}</h3>
                <select name="genreSelection" id="genreSelector" v-else v-model="genreRating.genre" class="col-4">
                    <option value="" disabled selected>Select a genre</option>
                    <option v-for="genre in genres" v-bind:key="genre" v-bind:value="genre">{{genre}}</option>
                </select>
                <StarRatingWrapperComponent 
                    v-bind:score="genreRating.score"
                    v-bind:ratingId="genreRating.ratingId"
                    v-bind:displayOnly="displayOnly"
                    @updateScoreOf="changeScoreOf"
                    class="col-8 flex-shrink-1"
                />
                <button v-if="!displayOnly" v-on:click.prevent="removeRating(index)" 
                    class="btn btn-danger btn-sm align-self-center" id="delbtn">X</button>
            </div>
        </div>
    </div>
</template>




<script>
//import StarRating from 'vue-star-rating'
import StarRatingWrapperComponent from './StarRatingWrapperComponent.vue'
    export default{
        name: "RatingComponent",
        //The property "initialRatings" is used as an initial value for ratings in the data
        //The property "displayOnly" is UI specific and is therfore not in the database
        props: ['ratings', 'displayOnly'],
        emits: ['update:ratings'],
        data() {
            return {
                //ratings: this.initialRatings,
                //Processing for rating is done on this variable before being sent to the parent component
                localRatings: this.ratings,
                nextNewRatingId:0,
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
                this.localRatings.push({ratingId:this.nextNewRatingId, genre:"Select a genre", score:0.5})
                this.nextNewRatingId++
                this.$emit('update:ratings', this.$event.target.this.localRatings)
            },

            removeRating(index){
                this.localRatings.splice(index, 1)
                this.$emit('update:ratings', this.$event.target.this.localRatings)
            },
            changeScoreOf(scoreChange){
                this.localRatings.forEach(rating => {
                    if(rating.ratingId == scoreChange.ratingId){
                        rating.score=scoreChange.newScore
                    }
                })
                this.$emit('update:ratings', this.$event.target.this.localRatings)
            }
        },
        components: {
            StarRatingWrapperComponent
        }
    }
</script>

<style scoped>
    #ratingSpace{
        display: block;
        background: #2e2f34;
    }
    #genreSelector{
        display:inline-flex;
        min-width: 20ch;
    }
    #delbtn{
        width: 4ch;
        height: 4ch;
    }
    .ratingDisp{
        display: flex;
        justify-content: stretch;
        align-items: stretch;
        flex-direction: row;
        flex-flow: row;
        background-color: #3a3e41;
    }
    option{
        color:black;
    }
</style>