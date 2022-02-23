<template>
  <div class="book_search">
    <div class="base">
      SEARCH
    </div>
    <div id="bookCreate">
    <router-link to="/create" id="create_button">create</router-link>
    </div>
    <div id="bookItem">
      <router-link to="/detail" id="detail_button">detail</router-link>
    </div>
    

    
  </div>
</template>

<script>
export default {
  name: 'Book Search'
}
</script>
