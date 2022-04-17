<template>
        <StarRating
            v-bind:rating="score"
            v-bind:read-only="displayOnly"
            v-bind:max-rating="5"
            v-bind:increment="0.5"
            :show-rating="false"
            inactive-color="#0000"
            active-color="#aba194"
            :star-size="25"
            @update:rating="changeScore"
        />
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