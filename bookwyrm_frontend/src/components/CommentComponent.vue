<template>
    <div id="enclosure">
        <h5>posted by, {{(author == ""||author == null)?"- Guest -":author}}</h5>
        <p>{{content}}</p>
    </div>
</template>

<script>

    export default{
        name: "CommentComponent",
        props:[
            'commentId',
            'author',
            'content',
            'anonymousFlag'
        ]
    }
</script>

<style scoped>

</style>