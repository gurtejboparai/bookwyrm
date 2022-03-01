<template>
  <div class="book_detail">
    
    <div id="enclosure">
      <div id="left_side">
        
        <div id="info_block">
          <h2>{{book_name}}</h2>
          <h5>By {{book_author}}</h5>
        </div>
        
        <div id="description_block">
          <p>{{book_description}}</p>
        </div>

        <div id="ratings_block">
          <p>Ratings component goes here</p>
        </div>
        
      </div>

      <div id="right_side">
        <div id="reviews_block">
          <Review_component/>
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>
import Review_component from '@/components/review_component.vue'
export default {
    name: "Book Detail",
    data() {
        return {
            book_name: "Title",
            book_author: "Author",
            book_description: "Book description goes here",
            book_ratings: {},
            book_reviews: {}
        };
    },
    components: { Review_component }
}
</script>


<style scoped>
  #book_detail{
    width: 100%;
  }
  #enclosure{
    display: flex;
    flex-direction: row;
    flex-flow: row nowrap;
    justify-content: stretch;
    align-items: stretch;
  }
  #left_side{
    flex-direction: column;
    flex-flow: column nowrap;
    flex-grow: 1;
    min-width: 25%;
    max-width: 33%;
    margin:10px;
  }
  #right_side{
    flex-direction: column;
    flex-flow: column nowrap;
    flex-grow: 2;
    margin: 10px;
    min-width: 50%;
    max-width: 67%;
  }
  #info_block{
    display:flex;
    flex-direction: column;
    flex-grow: 1;
  }
  #description_block{
    display:flex;
    flex-direction: column;
    flex-grow: 4;
  }
  #ratings_block{
    display:flex;
    flex-direction: column;
    flex-grow: 3;
  }
  #reviews_block{
    display: flex;
    flex-direction: column;
  }
  
</style>
