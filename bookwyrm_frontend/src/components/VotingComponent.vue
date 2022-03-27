<template>
  <div class="w-25">
    <svg
      @click="upVote"
      xmlns="http://www.w3.org/2000/svg"
      width="16"
      height="16"
      fill="currentColor"
      class="bi bi-arrow-up-circle"
      viewBox="0 0 16 16"
    >
      <path fill-rule="evenodd" :d="iconUpVote" />
    </svg>
  </div>

  <div>
    {{ voteCount }}
  </div>

  <div class="w-25">
    <svg
      @click="downVote"
      xmlns="http://www.w3.org/2000/svg"
      width="16"
      height="16"
      fill="currentColor"
      class="bi bi-arrow-down-circle"
      viewBox="0 0 16 16"
    >
      <path fill-rule="evenodd" :d="iconDownVote" />
    </svg>
  </div>
</template>

<script>
export default {
  name: "votingComponent",
  data() {
    return {
      iconUpVote:
        "M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-7.5 3.5a.5.5 0 0 1-1 0V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V11.5z",
      iconDownVote:
        "M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v5.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V4.5z",
      voteCount: 0,
    };
  },
  methods: {
    upVote() {
      this.voteCount++;
      alert("Up Vote");
    },
    downVote() {
      this.voteCount--;
      alert("Down Vote");
    },
  },
};
</script>
