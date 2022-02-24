<template>
    <div class="box">
        <div v-if="signedIn">
            <h5>You are logged in as, {{accountName}}</h5>
            <button>logout</button>
        </div>
        <div v-else>
            <form  method="post">
            <label for="username">Username: </label>
            <input type="text" name="userField" id="username" required>
            <label for="pWord">Password</label>
            <input type="password" name="passField" id="pWord" required>
            <input type="submit" value="login">
            </form>
        </div>
    </div>
</template>


<script>
import Logger from './components/Logger.vue'

export default {
    name:'Logger'
    data() {
        return {
            accountName: ""
            signedIn: false
        }
    },
    methods()
}
</script>

<style>

</style>