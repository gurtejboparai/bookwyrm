<template>
<div class="container">
  <div class="row justify-content-center">
    <small id="passwordHelpBlock" v-if="messageToDisplay" class="form-text card foreground m-3 p-3">
          {{this.message}}
    </small>
  </div>
  <div class="row justify-content-center">
    <div class="col-6 pt-4">
      <div class="input-group">
        Are you a Professional Journalist? <input type="checkbox" class="m-3" v-model=profJournalistFlag>
      </div>
      <div class="input-group">
        <input type="text" class="form-control m-2" v-model="profJournalistName" placeholder="Name / Nickname" v-if="profJournalistFlag">
      </div>
    </div>
  </div>
  <div class="row justify-content-center">
    <div class="col-6 pt-4">
      <div class="input-group">
        Are you an Author? <input type="checkbox" class="m-3" v-model=authorFlag>
      </div>
      <div class="input-group">
        <input type="text" class="form-control m-2" v-model="authorName" placeholder="Name / Pen Name" v-if="authorFlag">
      </div>
    </div>
  </div>
  <div class="row justify-content-center">
    <div class="col-3 btn btn-primary m-3 text-nowrap" @click="updateUser">Update</div>
  </div>
</div>
</template>

<script>
import userService from '@/services/UserService';
export default {
  name: 'Login',
  data(){
    return {
      profJournalistName:"",
      authorName:"",
      profJournalistFlag:false,
      authorFlag:false,
      messageToDisplay:false,
      message:null,
      timer:null
    }
  },
  methods:{
    updateUser(){
      userService.updateUser(this.$store.state.username, this.authorFlag, this.authorName, this.profJournalistFlag, this.profJournalistName).then((response)=>{
        this.displayMessage("Update successful!");
        this.$store.commit('login', response.data.user)
      }).catch((error)=>{
        let errorMessage = ""
        error.response.data.forEach(error => {
          errorMessage += `${error}\n`;
        });
        this.displayMessage(errorMessage);
      })
    },
    displayMessage(message){
        clearTimeout(this.timer);
        this.message=message
        this.messageToDisplay=true;
        this.timer = setTimeout(()=>{this.messageToDisplay=false;this.message=null},5000)
    }
  },
  mounted(){
    if(localStorage.getItem("username") != undefined){
      this.profJournalistName=localStorage.getItem("journalistName");
      this.authorName=localStorage.getItem("authorName");
      this.profJournalistFlag=localStorage.getItem("journalistFlag") == 'true';
      this.authorFlag=localStorage.getItem("authorFlag") == 'true';
    }
  }
}
</script>
