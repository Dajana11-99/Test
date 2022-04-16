<template>

  <ClientNavbar v-if="role=='CLIENT'" />
  <AdminNavbar v-else :username=email />
 
  <BookCabin :bookingProcess="false" />
</template>

<script>
import ClientNavbar from "./ClientNavbar";
import BookCabin from "./BookCabin";
import AdminNavbar from "../Admin/AdminNav.vue"

export default {
  components: {
    ClientNavbar,
    BookCabin,
    AdminNavbar
    
  },
  data() {
    return {
      role:localStorage.role,
      email: '',
    };
  },
  mounted() {
    this.email = this.$route.params.email;
  },
  methods: {},
  computed: {},
};
</script> 

<style>
</style>
