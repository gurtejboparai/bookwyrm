<template>
  <div v-if="!(displayOnly && localRatings.length==0)" style="overflow-x:scroll">
    <div v-if="!displayOnly">
      <button v-on:click.prevent="addRating" class="btn btn-bw mb-1">
        Add a rating
      </button>
    </div>
    <div></div>
    <div id="ratingSpace" class="">
      <span v-for="(genreRating, index) in localRatings" :key="genreRating.ratingId"
           class="ratingDisp m-2 p-2 card">

        <div v-if="displayOnly" >{{genreRating.genre}}</div>
        <select name="genreSelection" id="genreSelector" v-else v-model="genreRating.genre" 
          v-on:change="genreChanged(genreRating)">
          <option value="" disabled selected>Select a genre</option>
          <option v-for="genre in genres" v-bind:key="genre" v-bind:value="genre">{{genre}}</option>
        </select>
        <StarRatingWrapperComponent
            v-bind:score="genreRating.score"
            v-bind:ratingId="genreRating.ratingId"
            v-bind:displayOnly="displayOnly"
            @updateScoreOf="changeScoreOf"
            class="flex-shrink-1 "
        />
        <button v-if="!displayOnly" v-on:click.prevent="removeRating(index)"
                class="btn btn-danger btn-sm align-self-center" id="delbtn">X</button>
      </span>
    </div>
  </div>
</template>




<script>
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
                //If the value stored here is true that mean that the genre doesn't have a rating
                availableGenres:{},


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
        computed: {
            genres(){
                return this.$store.getters.getGenres
            }
        },
        created(){
            //check if given data is null and fix it if it is
            if(this.ratingsObject == null){
                this.ratingsObject = {}
                this.genres.forEach(category=> {
                    this.ratingsObject[category]=0
                })
            }
            //inititalize localRatings variable and genre Availability
            this.genres.forEach(category => {
                this.availableGenres[category] = !this.ratingsObject[category] > 0.0
                if(!this.availableGenres[category]){
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
  display: inline-flex;
  background: #0000;
  white-space: nowrap;
  overflow-x: scroll;
  min-width: 100%;
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
  justify-content: space-between;
  background-color: #0000;
}
option{
  color:black;
}
</style>