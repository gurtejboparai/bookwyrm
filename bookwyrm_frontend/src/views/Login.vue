<template>
<div class="container">
  <div class="row justify-content-center">
    <small id="passwordHelpBlock" v-if="messageToDisplay" class="form-text card foreground m-3 p-3">
          {{this.message}}
    </small>
  </div>
  <div class="row justify-content-center">
    <div class="col-3 pt-4">
      <div class="input-group">
          <input type="text" class="form-control m-2" v-model="accountName" placeholder="Username" required>
      </div>
      <div class="input-group">
          <input type="password" class="form-control m-2" v-model="password" placeholder="Password" required>
      </div>
      <div class="row justify-content-between">
        <div class="col-4 btn btn-bw-light m-3 text-nowrap" @click="login" >Sign in</div>
        <div class="col-4 text-align-right btn btn-bw m-3 text-nowrap" @click="signup">Sign up</div>
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
      messageToDisplay:false,
      message:null,
      timer:null
    }
  },
  methods:{
    login(){
      if(!this.validateFields()){
        window.crypto.subtle.digest('SHA-256',(new TextEncoder()).encode(this.password)).then((passwordHash)=>{
          let digestArray = Array.from(new Int32Array(passwordHash));
          let compoundedDigest = 1
          digestArray.forEach(element => {
            compoundedDigest = element * compoundedDigest
          });
          UserService.signin(this.accountName, compoundedDigest).then((response)=>{
            if(response.data.user!=null){
              this.$store.commit('login',response.data.user);
              this.$router.push('/')  
            }else{
              this.displayMessage("Username password combination not found." );
            }
          });
        });
      }
    },
    signup(){
      if(!this.validateFields()){
        window.crypto.subtle.digest('SHA-256',(new TextEncoder()).encode(this.password)).then((passwordHash)=>{
          let digestArray = Array.from(new Int32Array(passwordHash));
          let compoundedDigest = 1
          digestArray.forEach(element => {
            compoundedDigest = element * compoundedDigest
          });
          UserService.signup(this.accountName,compoundedDigest).then(()=>{
            this.login();
          }).catch((error)=>{
            let errorMessage = ""
            error.response.data.forEach(error => {
              errorMessage += `${error}\n`;
            });
            this.displayMessage(errorMessage);
          });
        })
      }
    },
    validateFields(){
      if(this.accountName == null){
        this.displayMessage("Please enter a username");
      }else if(this.password == null){
        this.displayMessage("Please enter a password");
      }else{
        this.messageToDisplay = false;
        clearTimeout(this.timer);
      }
      return this.messageToDisplay
    },
    displayMessage(message){
        clearTimeout(this.timer);
        this.message=message
        this.messageToDisplay=true;
        this.timer = setTimeout(()=>{this.messageToDisplay=false;this.message=null},5000)
    }
  }
}
</script>
