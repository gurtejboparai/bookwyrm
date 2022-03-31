<template>
    <div>
        <StarRating
            v-bind:rating="score"
            v-bind:read-only="displayOnly"
            v-bind:max-rating="5"
            v-bind:increment="0.5"
            @update:rating="changeScore"
        />
 
    </div>
</template>




<script>
import StarRating from 'vue-star-rating'
export default {
    name: "StarRatingWrapperComponent",
    props:['score', 'ratingId', 'displayOnly'],
    methods:{
        changeScore(changedTo){
            this.$emit('updateScoreOf', {newScore:changedTo, ratingId:this.ratingId})
        }
    },
    components: {StarRating}
}
</script>