<template>
  <div class="carouselContainer foreground">
    <div
      :id="carouselId"
      class="carousel slide"
      data-bs-ride="carousel"
      :ref="carouselId"
    >
      <div class="carousel-inner">
        <div
          v-for="(book, key, index) in booksByGenre"
          :key="key"
          class="carousel-item text-center p-4"
          :class="{ active: index == 0 }"
        >
          <router-link :to="bookPagePath(book.id)" class="text-decoration-none">
            <p>{{ book.title }}</p>
            <p>{{ key }}</p>
          </router-link>
        </div>
      </div>
      <button
        :data-bs-target="'#'+ carouselId"
        class="carousel-control-prev"
        data-bs-slide="prev"
        type="button"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button
        class="carousel-control-next"
        :data-bs-target="'#'+ carouselId"
        data-bs-slide="next"
        type="button"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>
</template>

<script>
import { Carousel } from "bootstrap";
export default {
  name: "CarouselComponent",
  props: {
    booksByGenre: Object,
    carouselId: String,
  },
  mounted() {
    this.carousel = new Carousel(this.$refs[this.carouselId]);
  },
  data() {
    return {
      carousel: null,
    };
  },
  methods: {
    bookPagePath(id) {
      return "/detail/" + id;
    },
  },
};
</script>
