<template>
  <div v-if="!(displayOnly && localRatings.length==0)">
    <h3>Ratings</h3>
    <div v-if="!displayOnly">
      <button v-on:click.prevent="addRating" class="btn btn-light mb-1">
        Add a rating
      </button>
    </div>
    <div></div>
    <div id="ratingSpace" class="p-1">
      <div v-for="(genreRating, index) in localRatings" :key="genreRating.ratingId"
           class="ratingDisp m-2 p-2 rounded row">

        <h3 v-if="displayOnly" class="col-4">{{genreRating.genre}}</h3>
        <select name="genreSelection" id="genreSelector" v-else v-model="genreRating.genre" 
          class="col-4" v-on:change="genreChanged(genreRating)">
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
                //Processing for ratings as it relates to rendered html tags is done on this variable
                localRatings: [],
                //This is the storage for the backend friendly ratings data
                ratingsObject: this.ratings,
                nextNewRatingId: 0,
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
                ],
                //list of genres that are available, these don't have a rating yet
                availableGenres:{
                    "Overall": true,
                    "Adventure": true,
                    "Action": true,
                    "Bedtime": true,
                    "Comedy/Humor": true,
                    "Children's": true,
                    "Drama": true,
                    "Fantasy": true,
                    "Gothic": true,
                    "Horror": true,
                    "Historical Fiction": true,
                    "Alternate History": true,
                    "Mystery": true,
                    "Romance": true,
                    "Sport": true,
                    "Science-Fiction": true,
                    "Thriller": true
                },


            }
        },
        methods: {
            addRating(){
                this.updateAvailableGenres()
                let initialGenre = this.findAvailableGenre()
                if(initialGenre != ""){
                    this.localRatings.push({ratingId:this.nextNewRatingId, genre:initialGenre, score:0.5})
                    this.availableGenres[initialGenre] = false
                    this.nextNewRatingId++
                    this.ratingsObject[initialGenre]=0.5
                    this.$emit('update:ratings', this.ratingsObject)
                }
            },

            removeRating(index){
                let targetGenre = this.localRatings[index].genre
                //remove rating from list
                this.localRatings.splice(index, 1)
                //upadate local data to reflect the change
                this.ratingsObject[targetGenre]=0;
                this.updateAvailableGenres()
                this.$emit('update:ratings', this.ratingsObject)
            },
            
            changeScoreOf(scoreChange){
                let targetGenre = ""
                //Find the genre we are changing the score for
                this.localRatings.forEach(rating => {
                    if(rating.ratingId == scoreChange.ratingId){
                        targetGenre=rating.genre
                    }
                })
                //change the score for the appropriate genre
                this.ratingsObject[targetGenre]=scoreChange.newScore
                this.localRatings.forEach(rating => {
                    if(targetGenre == rating.genre){
                        rating.score=scoreChange.newScore
                    }
                })
                
                this.$emit('update:ratings', this.ratingsObject)
            },

            //This function is called when genres are changed to keep the data displayed and stored in sync
            genreChanged(whatChanged){
                this.updateAvailableGenres()
                //update scores
                this.ratingsObject[whatChanged.genre] = whatChanged.score
                this.changeScoreOf({ratingId:whatChanged.ratingId, newScore:whatChanged.score})
                this.$emit('update:ratings', this.ratingsObject)
            },

            //this is a utility function that updates genre availability and resets scores to 0 if don't have
            //a rating currently
            //!NOTE! this function assumes that it will be proceeded by an emit to update ratings
            updateAvailableGenres(){
                this.genres.forEach(category => {
                    this.availableGenres[category] = this.localRatings.every(genreRating =>{
                        return genreRating.genre != category
                    })
                    if(this.availableGenres[category]){
                        this.ratingsObject[category] = 0.0
                    }
                })
            },

            //this utility function will retrieve the first genre that doesn't have a rating
            //it returns a string
            findAvailableGenre(){
                let available = "";
                let found = false;

                this.genres.forEach(category =>{
                    if(this.availableGenres[category] & !found){
                        available = category;
                        found = true;
                    }
                })

                return available;
            }

        },
        components: {
            StarRatingWrapperComponent
        },
        created(){
            //check if given data is null and fix it if it is
            if(this.ratingsObject == null){
                this.ratingsObject = {
                    "Overall":0,
                    "Adventure":0,
                    "Action":0,
                    "Bedtime":0,
                    "Comedy/Humor":0,
                    "Children's":0,
                    "Drama":0,
                    "Fantasy":0,
                    "Gothic":0,
                    "Horror":0,
                    "Historical Fiction":0,
                    "Alternate History":0,
                    "Mystery":0,
                    "Romance":0,
                    "Sport":0,
                    "Science-Fiction":0,
                    "Thriller":0
                }
            }
            //inititalize localRatings variable
            this.genres.forEach(category => {
                if(this.ratingsObject[category] > 0.0){
                    this.localRatings.push({ratingId:this.nextNewRatingId, genre:category, score:this.ratingsObject[category]})
                    this.availableGenres[category] = true
                    this.nextNewRatingId++
                }

            });
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