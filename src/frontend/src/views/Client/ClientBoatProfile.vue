<template>

  <ClientNavbar v-if="role=='CLIENT'" />
  <AdminNavbar v-else :username=email />
 
  <BookBoat :bookingProcess="false" />
</template>

<script>
import ClientNavbar from "./ClientNavbar";
import BookBoat from "./BookBoat";
import AdminNavbar from "../Admin/AdminNav.vue"

export default {
  components: {
    ClientNavbar,
    BookBoat,
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
