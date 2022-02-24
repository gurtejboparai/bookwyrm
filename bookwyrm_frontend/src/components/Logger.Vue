<template>
    <div class="box">
        <div v-if="signedIn">
            <h5>You are logged in as, {{activeUser}}</h5>
            <button v-on:click="logout">logout</button>
        </div>
        <div v-else>
            <form  method="post">
            <label for="username">Username: </label>
            <input type="text" name="userField" id="username" required v-model="accountName">
            <label for="pWord">Password</label>
            <input type="password" name="passField" id="pWord" required>
            <input type="submit" value="login" v-on:click="login">
            </form>
        </div>
    </div>
</template>


<script>

export default {
    name:'Logger',
    data() {
        return {
            accountName: "",
            activeUser: "",
            signedIn: false
        }
    },
    methods: {
        logout(){
            this.accountName = ""
            this.activeUser = ""
            this.signedIn = false
        },
        login(){
            this.signedIn = true
            this.activeUser = this.accountName
        }
    }
}
</script>

<style>

</style>