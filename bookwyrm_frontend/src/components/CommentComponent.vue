<template>
    <div id="enclosure">
        <h5>{{author}}</h5>
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
    #enclosure{
        margin: 5px;
        padding: 5px;
        background-color: #404446;
        border-radius: 5px;
    }
</style>