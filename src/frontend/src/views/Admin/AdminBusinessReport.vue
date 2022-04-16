<template>
<AdminNav :username=email />

</template>

<script>
import AdminNav from './AdminNav.vue'

   export default{
     components:{
       AdminNav,
     },
     data(){
       return{
         email: '', 

       }
     },
     mounted() {
       this.email = this.$route.params.email

     },
     methods: {
   
     

    },
    computed: {

  },
  }

</script> 

<style>

</style>
