<template>
    <form class="new-book-form" v-on:submit.prevent="saveBook">
        <input class="title-input" type="text" placeholder="Title" v-model="book.title">
        <input class="author-input" type="text" placeholder="Author" v-model="book.author">
        <input class="isbn-input" type="text" placeholder="ISBN" v-model="book.isbn">
</template>
<script>
 export default {
     data() {
         return {
             book: {
                 title: '',
                 author: '',
                 read: false,
                 isbn: '',
             }
         }
     },
     methods:{
         saveBook() {
             this.$store.commit('SAVE_BOOK', this.book);
             this.book = {
                 title: '',
                 author: '',
                 read: false,
                 isbn: '',
             }
         }
     }
 }
</script>
<style>
</style>