<template>
<div class="container">
  <div class="row justify-content-center">
    <div class="col-3 pt-4">
      <small id="passwordHelpBlock" v-if="failLogin" class="form-text">
        Username password combination not found.
      </small>
      <div class="input-group">
          <input type="text" class="form-control m-2" v-model="accountName" placeholder="Username">
      </div>
      <div class="input-group">
          <input type="password" class="form-control m-2" v-model="password" placeholder="Password">
      </div>
      <div class="row justify-content-between">
        <div class="col-4 btn btn-primary m-3 text-nowrap" @click="login" >Sign in</div>
        <div class="col-4 text-align-right btn btn-success m-3 text-nowrap">Sign up</div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import UserService from '@/services/UserService'
export default {
  name: 'Login',
  data(){
    return {
      accountName:null,
      password:null,
      failLogin:false
    }
  },
  methods:{
    login(){
      UserService.signin(this.accountName, window.crypto.subtle.digest('SHA-256', this.password)).then((response)=>{
        if(response.body){
          this.$store.commit('login',this.accountName);
          this.$router.push('/')    
        }else{
          this.failLogin=true;
        }
      });
      
    },
    signup(){
      UserService.signup(this.accountName, window.crypto.subtle.digest('SHA-256', this.password));
      this.$store.commit('login',this.accountName);
      this.$router.push('/')
    }
  }
}
</script>
