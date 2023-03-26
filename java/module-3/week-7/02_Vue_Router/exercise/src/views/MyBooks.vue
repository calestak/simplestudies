<template>
   <main>
      <reading-list/>
      <router-link v-bind:to="{name:'newBook'}"></router-link>
      </main>
</template>

<script>
import ReadingList from '../components/ReadingList.vue'
export default {
  components: { ReadingList },

}
</script>

<style>

</style>