<template>
  <div >
    <nav  class="navbar navbar-fixed-top navbar-expand" style="background-color: #1d7ac9; list-style: none;">
      <div class="container-fluid" style="background-color: #1d7ac9;">
      <a class="navbar-brand" href="http://localhost:8080/">
      <img src="../assets/logoF1.png" alt="" width="194" height="80" >
      </a>
      </div>
    </nav>

   <br>
   <br>
   <div class="alert alert-primary" style="margin-top: 3%;
  margin-bottom: 10%;
  margin-right: 10%;
  margin-left: 10%;" role="alert">
   <h4 class="alert-heading">One more step...</h4>
   <p>Please change your password before you start using admin account. </p>
     <button @click="changePassword()" class="btn btn-primary"> Change password </button>
   </div>



   

  
  </div>

</template>

<script>



export default {
  data() {
    return {
       email: '',
    };
  },
  mounted() {
    this.email = this.$route.params.email
  },
  methods: {
    changePassword: function(){
         this.$router.push('/changedPassword/'+this.email); 
    }
  }
};
</script>


<style>

</style>
