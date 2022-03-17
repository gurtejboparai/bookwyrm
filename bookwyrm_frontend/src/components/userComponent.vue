<template>
    <div class="box">
        <div v-if="isLoggedIn">
            <span>You are logged in as {{activeUser}}</span><br>
            <div class="btn btn-link p-0 text-info text-decoration-none" @click="logout">Logout</div>
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
    }
}
</script>

<style>

</style>