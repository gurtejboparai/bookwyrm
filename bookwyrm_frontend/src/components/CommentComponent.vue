<template>
    <div>
        <div id="enclosure">
            <h5>posted by, {{(author == ""||author == null)?"- Guest -":author}}</h5>
            <p>{{content}}</p>
        </div>
        <div></div>
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