<template>
  <div class="book_detail">
    
    <table id="enclosure">
      <tr>
        <td id="left_side">
          
          <div id="info_block">
            <h2>{{book_title}}</h2>
            <h5>By {{book_author}}</h5>
          </div>
          
          <div id="description_block">
            <p>{{book_description}}</p>
          </div>

          <div id="ratings_block">
            <p>Ratings component goes here</p>
          </div>
          
        </td>

        <td id="right_side">
          <div id="reviews_block">
            <p>Reviews component goes here</p>
          </div>
        </td>
      </tr>
    </table>
    
  </div>
</template>

<script>

export default {
  name: 'Book Detail',
  data(){
    return{
      book_title:"Title",
      book_author:"Author",
      book_description:"Book description goes here",
      book_ratings:{},
      book_reviews:{}
    }
    

  }
}
</script>


<style scoped>
  #book_detail{
    width: 100%;
  }
  #enclosure{
    border-collapse: separate;
    display: block;
    justify-content: space-between;
  }
  #left_side{
    display: inline-flex;
    flex-grow: 1;
    flex-direction: column;
    margin:15px;
  }
  #right_side{
    display: inline-flex;
    flex-grow: 2;
    flex-direction: row;
    margin: 15px;
  }
  #info_block{
    display:flex;
    flex-direction: column;
  }
  #description_block{
    display:flex;
    flex-direction: column;
  }
  #ratings_block{
    display:flex;
    flex-direction: column;
  }
  #reviews_block{
    display: flex;
    flex-direction: column;
  }
  
</style>
