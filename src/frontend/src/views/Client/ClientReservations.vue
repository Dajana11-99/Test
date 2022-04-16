<template>
  <ClientNavbar />

  <ul class="nav justify-content-center" style="background-color: #0f5591">
    <li class="nav-item">
      <a
        style="color: white"
        class="nav-link"
        @click="changeDisplay('upcoming')"
      >
        UPCOMING</a
      >
    </li>

    <li class="nav-item">
      <a
        style="color: white"
        class="nav-link"
        @click="changeDisplay('history')"
      >
        HISTORY</a
      >
    </li>
  </ul>

  <template v-if="upcomingReservationsShown">
    <ClientReservationsList :upcomingCabinReservations="true" />
  </template>
  <template v-if="reservationHistoryShown">
    <ClientReservationsList :upcomingCabinReservations="false" />
  </template>
</template>

<script>
import ClientNavbar from "./ClientNavbar";
import ClientReservationsList from "./ClientReservationsList.vue";

export default {
  components: {
    ClientNavbar,
    ClientReservationsList,
  },
  data() {
    return {
      email: "",
      upcomingReservationsShown: true,
      reservationHistoryShown: false,
    };
  },
  mounted() {
    this.email = this.$route.params.email;
  },
  methods: {
    changeDisplay: function (toDisplay) {
      if (toDisplay === "upcoming") {
        this.upcomingReservationsShown = true;
        this.reservationHistoryShown = false;
      } else {
        this.upcomingReservationsShown = false;
        this.reservationHistoryShown = true;
      }
    },
  },
  computed: {},
};
</script> 

<style>
</style>
