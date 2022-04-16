<template>
  <ClientNavbar />
    <div>
      <div class="row justify-content-center" style="width: 100%">
        <div class="col-md-6">
          <div class="card card-outline-secondary">
            <div class="card-header">
              <h3 style="text-align: center" class="mb-0">Evaluation</h3>
            </div>
            <div class="card-body">
              <div style="text-align: left" class="form-group">
                <label>Comment for the {{ selectedEntity }}:</label>
                <textarea
                  v-model="commentEntity"
                  class="form-control"
                  id="exampleFormControlTextarea1"
                  rows="3"
                ></textarea>
              </div>

              <br />

              <div style="text-align: left" class="form-group">
                <label>Grade for the {{ selectedEntity }}:</label>
                <star-rating v-model:rating="gradeEntity"></star-rating>
              </div>

              <br />

              <div style="text-align: left" class="form-group">
                <label>Comment for the {{ selectedEntity }} owner:</label>
                <textarea
                  v-model="commentEntityOwner"
                  class="form-control"
                  id="exampleFormControlTextarea1"
                  rows="3"
                ></textarea>
              </div>

              <br />

              <div style="text-align: left" class="form-group">
                <label>Grade for the {{ selectedEntity }} owner:</label>
                <star-rating v-model:rating="gradeEntityOwner"></star-rating>
              </div>

              <br />

              <div class="form-group" style="text-align: right">
                <button
                  class="btn btn-outline-dark"
                  @click="submitEvaluation"
                >
                  Confirm
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import axios from "axios";
import ClientNavbar from "./ClientNavbar";
import StarRating from 'vue-star-rating'

export default {
  components: {
    ClientNavbar,
    StarRating
  },
  data() {
    return {
      email: "",
      selectedEntity: "",
      commentEntity: "",
      gradeEntity: 5,
      commentEntityOwner: "",
      gradeEntityOwner: 5,
      reservationId: 0,
    };
  },
  mounted() {
    this.email = this.$route.params.email;
    this.selectedEntity = this.$route.params.entity;
    this.reservationId = this.$route.params.reservationId;
  },
  methods: {
    validateData: function(){
      if(this.commentEntity==""){
        this.$swal.fire({
            position: "top-end",
            icon: "error",
            title: "You must add comment about " + this.selectedEntity + "!",
            showConfirmButton: false,
            timer: 2500,
          });
          return false;
      }else if(this.commentEntityOwner==""){
        this.$swal.fire({
            position: "top-end",
            icon: "error",
            title: "You must add comment about " + this.selectedEntity + " owner!",
            showConfirmButton: false,
            timer: 2500,
          });
          return false;
      }
        
      return true;
    },
    submitEvaluation: function (event) {
      event.preventDefault();
      if(!this.validateData())
        return;
      if (this.selectedEntity === "cabin") {
        axios
          .post(
            "http://localhost:8081/cabinEvaluation/addEvaluation",
            {
              reservationId: this.reservationId,
              commentForTheEntity: this.commentEntity,
              gradeForTheEntity: this.gradeEntity,
              commentForTheEntityOwner: this.commentEntityOwner,
              gradeForTheEntityOwner: this.gradeEntityOwner,
              clientUsername: this.email,
            },
            {}
          )
          .then((response) => {
            this.$swal.fire({
            position: "top-end",
            icon: "success",
            title: response.data,
            showConfirmButton: false,
            timer: 2500,
          });
          this.$router.push("/reservations/" + this.email);
          }).catch((error) => {
          this.$swal.fire({
            icon: "error",
            title: "Something went wrong!",
            text: error.response.data,
          });
        });
      } else if (this.selectedEntity === "boat") {
        console.log("Boats");
      } else {
        console.log("adventures");
      }
    },
  },
  computed: {},
};
</script> 

<style>
</style>
