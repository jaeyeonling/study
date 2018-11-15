<template>
  <div class="about">
    <h1>Hello {{ their_name }}, This is an about page</h1>
    <p>:)</p>
  </div>
</template>

<script>
  export default {
    name: 'About',
    data() {
      return {
        their_name: this.$route.params.name
      }
    },
  }
</script>