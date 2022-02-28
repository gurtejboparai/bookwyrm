<template>
    <div class="box">
        <div v-if="signedIn">
            <h5>You are logged in as, {{activeUser}}</h5>
            <div></div>
            <button v-on:click="logout" id="logout_button">logout</button>
        </div>
        <div v-else>
            <form  method="post">
                <table>
                    <tr>
                        <td>
                            <label for="username">Username: </label>
                            <input type="text" name="userField" id="user_name" required v-model="accountName">
                            <div></div>
                            <label for="pWord">Password: </label>
                            <input type="password" name="passField" id="p_word" required>
                        </td>
                        <td>
                            <input type="submit" value="login" v-on:click="login" id="login_button">
                        </td>
                        
                    </tr>
                    
                </table>
                
            </form>
        </div>
    </div>
</template>


<script>
export default {
    name:'log_in_out_box',
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