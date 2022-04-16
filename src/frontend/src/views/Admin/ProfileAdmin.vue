<template>
<AdminNav :username=email />

<ul class="nav justify-content-center" style="background-color: #0f5591;">

      <li class="nav-item">
      <a style="color: white;" class="nav-link" @click="changeDisplay('cabins')"> CABINS</a>
      </li>

      <li class="nav-item">
      <a style="color: white;" class="nav-link" @click="changeDisplay('boats')">  BOATS</a>
      </li>
       
       <li class="nav-item">
      <a style="color: white;"  class="nav-link" @click="changeDisplay('adventures')"> ADVENTURES</a>
      </li>

     </ul>

  <template v-if="cabinsShown"> 
    <ClientCabins :reservationProcess="false" :availableCabins="[]"/>
  </template>
  <template v-if="boatsShown"> 
     <ClientBoats/>
  </template>
  <template v-if="adventuresShown"> 
     <ClientAdventures/>
  </template>
</template>

<script>
import AdminNav from './AdminNav.vue'
import ClientCabins from '../Client/ClientCabins'
import ClientBoats from '../Client/ClientBoats'
import ClientAdventures from '../Client/ClientAdventures'

   export default{
     components:{
       AdminNav,
       ClientCabins,
       ClientBoats,
       ClientAdventures
     },
     data(){
       return{
         email: '', 
         cabinsShown: true,
         boatsShown: false, 
         adventuresShown: false,   

       }
     },
     mounted() {
       this.email = this.$route.params.email

     },
     methods: {
     changeDisplay: function(toDisplay){
       if(toDisplay === 'cabins'){
         this.cabinsShown = true;
         this.boatsShown = false;
         this.adventuresShown = false;
       }else if(toDisplay === 'boats'){
         this.cabinsShown = false;
         this.boatsShown = true;
         this.adventuresShown = false;
       }else{
         this.cabinsShown = false;
         this.boatsShown = false;
         this.adventuresShown = true;
       }
     }

    },
    computed: {

  },
  }

</script> 

<style>

</style>
