<template>
<section class="new-book">
    <form class="new-book-form" v-on:submit.prevent="saveBook">
        <input class="title-input" type="text" placeholder="Title" v-model="book.title">
        <input class="author-input" type="text" placeholder="Author" v-model="book.author">
        <input class="isbn-input" type="text" placeholder="ISBN" v-model="book.isbn">
        <select v-model="newBookForm.book">
            <option value="New Book">New Book</option>
        </select>
        <button>Add New Book</button>
    </form>
</section>
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
.card {
    border: 2px solid black;
    border-radius: 10px;
    width: 250px;
    height: 550px;
    margin: 20px;
    position: relative;
}
</style>