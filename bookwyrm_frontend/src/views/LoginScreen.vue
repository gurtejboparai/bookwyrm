<template>
<div class="container">
  <div class="row justify-content-center">
    <div class="col-3">
      <div class="input-group">
          <input type="text" class="form-control m-2" v-model="accountName" placeholder="Username">
      </div>
      <div class="input-group">
          <input type="password" class="form-control m-2" v-model="password" placeholder="Password">
      </div>
      <div class="row justify-content-between"><div class="col-4 btn btn-primary m-3 text-nowrap" @click="login" >Sign in</div><div class="col-4 text-align-right btn btn-success m-3 text-nowrap">Sign up</div></div>
    </div>
  </div>
</div>
</template>

<script>

export default {
  name: 'Login',
  data(){
    return {
      accountName:null,
      password:null
    }
  },
  methods:{
    login(){
      this.$store.commit('login',this.accountName);
      this.$router.push('/')
    }
  }
}
</script>
