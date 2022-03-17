<template>
    <div class="box">
        <div v-if="isLoggedIn">
            <h5>You are logged in as, {{activeUser}}</h5>
            <div></div>
            <button v-on:click="logout" id="logoutButton">logout</button>
        </div>
        <div v-else>
            <router-link to="/login" id="login_button" style="text-align: center" class="m-3">Login</router-link>
        </div>
    </div>
</template>

<script>
export default {
    name:'userComponent',
    data() {
        return {
            accountName: ""
        }
    },
    computed: {
        isLoggedIn(){
            return this.$store.state.username != "";
        },
        activeUser(){
            return this.$store.state.username;
        }
    },
    methods: {
        logout(){
            this.$store.commit('logout');
        },
        login(){
            this.$store.commit('login',this.accountName);
            this.accountName = "";
        }
    }
}
</script>

<style>

</style>