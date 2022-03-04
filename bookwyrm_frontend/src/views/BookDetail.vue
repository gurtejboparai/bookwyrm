<template>
  <div class="BookDetail">
    
    <div id="enclosure">
      <div id="left_side">
        
        <div id="info_block">
          <h2>{{title}}</h2>
          <h5>By {{book_author}}</h5>
        </div>
        
        <div id="description_block">
          <p>{{description}}</p>
        </div>

        <div id="ratings_block">
          <p>Ratings component goes here</p>
        </div>
        
      </div>

      <div id="right_side">
        <div id="reviews_block">
          <ReviewComponent
            v-bind:reviews="reviewList"
          />
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>
import ReviewComponent from "../components/ReviewComponent.vue";
export default {
    name: "Book Detail",
    data() {
        return {
            title: "Title",
            author: "Author",
            description: "Book description goes here",
            reviewList: [
                { 
                    name: "theUglyBarnacle/Spongebob", 
                    author: "Spongebob Squarepants", 
                    description: "A friend told me this story once when I was feeling bad about my appearance, it didn't help at all.", 
                    commentList: [], 
                    anonymous: true 
                },
                { 
                    name: "theUglyBarnacle/Plankton",
                    author: "Sheldon J. Plankton", 
                    description: "While the story is brief, I ultimately enjoyed the story overall, especially the ending.", 
                    commentList: [
                        {
                            identity:"Spongebob Squarepants/0",
                            author:"Spongebob Squarepants",
                            content:"Did you forget to turn on anonymous? This is something most people woudn't admit publicly.",
                            anonymous:true
                        },
                        {
                            identity:"Sheldon J. Plankton/0",
                            author:"Sheldon J. Plankton",
                            content:"I meant what I said you anonymous coward",
                            anonymous:false
                        }
                        ], 
                    anonymous: false 
                }
            ]
        };
    },
    components: { ReviewComponent}
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
    background-color: #34383a;
    padding: 5px;
    margin: 10px;
    border-radius: 10px;
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
