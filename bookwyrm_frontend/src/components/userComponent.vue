<template>
    <div class="box">
        <div v-if="isLoggedIn">
            <h5>You are logged in as, {{activeUser}}</h5>
            <div></div>
            <button v-on:click="logout" id="logoutButton">logout</button>
        </div>
        <div v-else>
            <form  method="post">
                <table>
                    <tr>
                        <td>
                            <label for="usernameField">Username: </label>
                            <input type="text" name="usernameField" id="usernameField" required v-model="accountName">
                            <div></div>
                            <label for="passwordField">Password: </label>
                            <input type="password" name="passwordField" id="passwordField" required>
                        </td>
                        <td>
                            <input type="submit" value="login" v-on:click="login" id="loginButton">
                        </td>
                        
                    </tr>
                    
                </table>
                
            </form>
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