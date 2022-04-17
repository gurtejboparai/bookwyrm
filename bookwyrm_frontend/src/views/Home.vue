<template>
  <div class="home">
    <div v-if="finishedLoading" class="row m-3 justify-content-between">
      <div class="col-5  rounded p-5 m-5">
        <div class="text-center ">
          <h2>Top Rated Books</h2>
        </div>
        <CarouselComponent :booksByGenre="topBooksByGenre" carouselId="topRated" class="bookCarousel" />
      </div>

      <div class="col-5 rounded p-5 m-5">
        <div class="text-center ">
          <h2>Recently Added Books</h2>
        </div>
        <CarouselComponent
          :booksByGenre="recentBooksByGenre"
          carouselId="recentBooks"
          class="bookCarousel"
        />
      </div>
    </div>
  </div>
</template>

<script>
import BookService from "@/services/BookService";
import CarouselComponent from "@/components/CarouselComponent";
export default {
  name: "Home",
  components: { CarouselComponent },
  data() {
    return {
      finishedLoading: false,
      topBooksByGenre: {},
      recentBooksByGenre: {},
    };
  },
  created() {
    this.$store.state.genreList.forEach((genre) => {
      BookService.getTopRatedBookbyGenre(genre).then((response) => {
        this.topBooksByGenre[genre] = response.data.bookDao;
      });

      BookService.getRecentlyAddedBookByGenre(genre).then((response) => {
        this.recentBooksByGenre[genre] = response.data.bookDao;
      }).catch(()=>{});
    });
    this.finishedLoading = true;
  },
};
</script>
