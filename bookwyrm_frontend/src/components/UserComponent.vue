<template>
    <div class="box">
        <div v-if="isLoggedIn" class="text-end">
            <span>You are logged in as <router-link to="/user">{{activeUser}}</router-link></span><br>
            <div class="btn btn-link p-0 text-info text-decoration-none" @click="logout">Logout</div>
        </div>
        <div v-else class="text-end">
            <router-link to="/login" id="login_button" class="m-3 text-align-center text-decoration-none">Login</router-link>
        </div>
    </div>
</template>

<script>
export default {
    name:'UserComponent',
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
    }
}
</script>

<style>

</style>