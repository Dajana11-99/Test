<template>
  <div>
    <div style="margin-top: 1%; width: 100%; height: 100" class="row">
      <div style="padding-left: 3%" class="col-sm-6">
        <div class="row" style="padding-top: 2%">
          <h1 style="text-align: left">{{ cabinDto.name.toUpperCase() }}</h1>
          <h6 style="text-align: left; color: gray">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="16"
              height="16"
              fill="currentColor"
              class="bi bi-geo-alt"
              viewBox="0 0 16 16"
            >
              <path
                d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A31.493 31.493 0 0 1 8 14.58a31.481 31.481 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94zM8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10z"
              />
              <path
                d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4zm0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"
              />
            </svg>
            {{ getFullAddress() }}
          </h6>
        </div>

        <div
          style="height: 70%; width: 100%"
          id="carouselExampleControls"
          class="carousel slide"
          data-bs-ride="carousel"
        >
          <div class="carousel-indicators">
            <button
              class="active"
              @click="clickedImage(index)"
              v-for="(image, index) in cabinDto.images"
              :key="index"
              type="button"
              data-bs-target="#carouselExampleIndicators"
            ></button>
          </div>
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img
                :src="require('@/assets/' + currentImageUrl)"
                class="d-block w-100"
                alt="..."
              />
            </div>
          </div>
          <button
            @click="previousImage()"
            class="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleControls"
            data-bs-slide="prev"
          >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button
            @click="nextImage()"
            class="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleControls"
            data-bs-slide="next"
          >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>

        <div
          style="
            text-align: left;
            padding-left: 1%;
            padding-top: 1%;
            border: 2px solid #bfbfbf;
          "
        >
          <p>{{ cabinDto.description }}</p>

          <p>Rules: {{ cabinDto.rules }}</p>
        </div>
      </div>

      <div
        style="
          text-align: left;
          border: 2px solid #bfbfbf;
          padding-left: 2%;
          margin-top: 6.5%;
        "
        class="col-sm-4"
      >
        <div class="row">
          <div style="text-align: left; font-size: 200%" class="col">
            <span class="badge bg-warning text-light"
              ><svg
                xmlns="http://www.w3.org/2000/svg"
                width="16"
                height="16"
                fill="currentColor"
                class="bi bi-star-fill"
                viewBox="0 0 16 16"
              >
                <path
                  d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"
                />
              </svg>
              {{ cabinDto.rating }}</span
            >
          </div>
          <div class="col">
            <h4 style="text-align: right; padding-top: 5%">
              Booking information
            </h4>
          </div>
        </div>

        <hr />
        <div class="row">
          <div class="col">
            <p>Owner:</p>
          </div>
          <div class="col">
            <p>
              <b>{{ cabinDto.ownerUsername }}</b>
            </p>
          </div>
        </div>

        <div class="row">
          <div class="col">
            <p>Price per night:</p>
          </div>
          <div class="col" style="color: green">
            <p>
              <b>{{ cabinDto.price }}$</b>
            </p>
          </div>
        </div>

        <div class="row">
          <div class="col">
            <p>Number of rooms:</p>
          </div>
          <div class="col">
            <p>{{ cabinDto.numOfRooms }}</p>
          </div>
        </div>

        <div class="row">
          <div class="col">
            <p>Beds per room:</p>
          </div>
          <div class="col">
            <p>{{ cabinDto.bedsPerRoom }}</p>
          </div>
        </div>

        <div class="row">
          <div class="col">
            <p>Cancelling:</p>
          </div>
          <div class="col">
            <p>{{ cabinDto.cancellingConditions }}</p>
          </div>
        </div>

        <template v-if="bookingProcess">
          <hr />

          <p>Additional services:</p>

          <table class="table" v-show="cabinDto.additionalServices.length != 0">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Service</th>
                <th scope="col">Price ($)</th>
                <th scope="col">&nbsp;</th>
              </tr>
            </thead>

            <tbody>
              <tr
                v-for="(service, index) in availableAdditionalServices"
                :key="index"
              >
                <th scope="row">{{ index + 1 }}</th>
                <td>{{ service.name }}</td>
                <td>{{ service.price }}</td>
                <td>
                  <input
                    @click="addService(service)"
                    type="button"
                    value="add"
                    class="btn btn-outline-success"
                  />
                </td>
              </tr>
            </tbody>
          </table>

          <p v-show="availableAdditionalServices.length == 0">
            No additional services for adding.
          </p>

          <p>Added additional services:</p>
          <template v-if="addedAdditionalServices">
            <table class="table" v-show="addedAdditionalServices.length != 0">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Service</th>
                  <th scope="col">Price ($)</th>
                  <th scope="col">&nbsp;</th>
                </tr>
              </thead>

              <tbody>
                <tr
                  v-for="(service, index) in addedAdditionalServices"
                  :key="index"
                >
                  <th scope="row">{{ index + 1 }}</th>
                  <td>{{ service.name }}</td>
                  <td>{{ service.price }}</td>
                  <td>
                    <input
                      @click="removeService(service)"
                      type="button"
                      value="remove"
                      class="btn btn-outline-danger"
                    />
                  </td>
                </tr>
              </tbody>
            </table>
          </template>
          <p
            v-show="
              addedAdditionalServices && addedAdditionalServices.length == 0
            "
          >
            No additional services added.
          </p>
          <hr />
          <div class="row">
            <div class="col">
              <p>Start date:</p>
            </div>
            <div class="col">
              <Datepicker
                @change="calculatePrice()"
                v-model="start"
                required
              ></Datepicker>
            </div>
          </div>

          <div class="row">
            <div class="col">
              <p>End date:</p>
            </div>
            <div class="col">
              <Datepicker
                @change="calculatePrice()"
                v-model="end"
                required
              ></Datepicker>
            </div>
          </div>
          <hr />
          <div class="row">
            <div class="col">
              <p>Total price:</p>
            </div>
            <div class="col">
              <p>{{ totalPrice }}</p>
            </div>
          </div>

          <button
            style="background-color: #1d7ac9; width: 100%"
            type="button"
            class="btn text-light rounded-pill"
            @click="bookCabin()"
          >
            BOOK
          </button>
        </template>

        <template v-if="!bookingProcess">
          <p>Additional services:</p>
          <div
            v-for="(service, index) in cabinDto.additionalServices"
            :key="index"
            class="group"
            role="group"
            aria-label="Basic outlined example"
          >
            <span
              v-if="service.price == 0"
              style="background-color: #59d47a"
              class="badge rounded-pill text-light"
              >{{ service.name }} - Free</span
            >
            <span
              v-else
              style="background-color: #703636"
              class="badge rounded-pill text-light"
              >{{ service.name }} - {{ service.price }}$ per day</span
            >
          </div>
        </template>
      </div>

      <template v-if="bookingProcess">
        <div class="col" style="margin-top: 3%">
          <button
            @click="back()"
            style="background-color: #1d7ac9; width: 100%"
            type="button"
            class="btn text-light rounded-pill"
          >
            BACK
          </button>
        </div>
      </template>
      <template v-if="!bookingProcess && role == 'CLIENT'">
        <template v-if="!cabinDto.subscription && role == 'CLIENT'">
          <div class="col" style="margin-top: 3%">
            <button
              @click="subscribe()"
              style="background-color: #1d7ac9; width: 100%"
              type="button"
              class="btn text-light rounded-pill"
            >
              SUBSCRIBE
            </button>
          </div>
        </template>
        <template v-if="cabinDto.subscription && role == 'CLIENT'">
          <div class="col" style="margin-top: 3%">
            <button
              @click="unsubscribe()"
              style="background-color: #1d7ac9; width: 100%"
              type="button"
              class="btn text-light rounded-pill"
            >
              UNSUBSCRIBE
            </button>
          </div>
        </template>
      </template>
      <template v-if="role == 'ADMIN'">
        <div class="col" style="margin-top: 3%">
          <button
            @click="deleteCabin()"
            style="background-color: #1d7ac9; width: 100%"
            type="button"
            class="btn text-light rounded-pill"
          >
            DELETE CABIN
          </button>
        </div>
      </template>
      <div
        style="
          text-align: left;
          border: 2px solid #bfbfbf;
          padding-left: 2%;
          margin-top: 2%;
          width: 80.5%;
          margin-left: 3%;
        "
        class="row-cols-sm-1"
      >
        <OpenLayersMap
          :coordinates="[
            cabinDto.addressDto.latitude,
            cabinDto.addressDto.longitude,
          ]"
        />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import OpenLayersMap from "../../components/OpenLayersMap.vue";
import Datepicker from "vue3-date-time-picker";
import dayjs from "dayjs";

export default {
  props: {
    cabinName: String,
    back: Function,
    startDate: Date,
    endDate: Date,
    bookingProcess: Boolean,
  },
  components: {
    OpenLayersMap,
    Datepicker,
  },
  data() {
    return {
      email: "",
      role: localStorage.role,
      cabinDto: {
        id: null,
        name: "",
        description: "",
        numOfRooms: 1,
        bedsPerRoom: 1,
        rules: "",
        price: 1.0,
        addressDto: {
          longitude: 0.0,
          latitude: 0.0,
          country: "",
          city: "",
          streetAndNum: "",
        },
        additionalServices: [
          {
            id: null,
            name: "",
            price: 0.0,
          },
        ],
        rating: 0.0,
        images: [
          {
            id: null,
            url: "",
            cabin: "",
          },
        ],
        ownerUsername: "",
        cancellingConditions: '',
      },
      addedAdditionalServices: [
        {
          id: null,
          name: "",
          price: 0.0,
        },
      ],
      availableAdditionalServices: [
        {
          id: null,
          name: "",
          price: 0.0,
        },
      ],
      user: {
        username: "",
      },
      cabinLoaded: false,
      currentImageUrl: "logoF1.png",
      imageIndex: 0,
      maxImageIndex: 0,
      start: null,
      end: null,
      totalPrice: 0,
      cabinNameParam: "",
    };
  },
  mounted() {
    this.role = localStorage.role;
    this.email = this.$route.params.email;
    this.cabinNameParam = this.$route.params.cabinName;
    this.start = this.startDate;
    this.end = this.endDate;
    this.getCabin();
    if (this.bookingProcess) this.calculatePrice();
  },
  methods: {
    calculatePrice: function () {
      let numOfDays = this.getNumberOfDays(this.start, this.end);
      var pricePerDay = this.cabinDto.price;
      for (let i = 0; i < this.addedAdditionalServices.length; i++) {
        pricePerDay += this.addedAdditionalServices[i].price;
      }
      this.totalPrice = numOfDays * pricePerDay;
    },
    getNumberOfDays: function (start, end) {
      const date1 = new Date(start);
      const date2 = new Date(end);

      // One day in milliseconds
      const oneDay = 1000 * 60 * 60 * 24;

      // Calculating the time difference between two dates
      const diffInTime = date2.getTime() - date1.getTime();

      // Calculating the no. of days between two dates
      const diffInDays = Math.ceil(diffInTime / oneDay);

      return diffInDays;
    },
    addService: function (service) {
      if (this.addedAdditionalServices == null)
        this.addedAdditionalServices = [];
      this.addedAdditionalServices.push(service);
      let newList = [];
      for (let i = 0; i < this.availableAdditionalServices.length; i++) {
        if (this.availableAdditionalServices[i].id === service.id) {
          continue;
        }
        newList.push(this.availableAdditionalServices[i]);
      }
      this.availableAdditionalServices = newList;
      this.calculatePrice();
    },
    removeService: function (service) {
      this.availableAdditionalServices.push(service);
      let newList = [];
      for (let i = 0; i < this.addedAdditionalServices.length; i++) {
        if (this.addedAdditionalServices[i].id === service.id) {
          continue;
        }
        newList.push(this.addedAdditionalServices[i]);
      }
      this.addedAdditionalServices = newList;
      this.calculatePrice();
    },
    getCabin: function () {
      this.cabinDto.ownerUsername = this.email;
      if (this.bookingProcess) {
        this.cabinDto.name = this.cabinName;
      } else {
        this.cabinDto.name = this.cabinNameParam;
      }
      axios
        .post(
          "http://localhost:8081/cabins/findByNameClient",
          this.cabinDto,
          {}
        )
        .then((response) => {
          this.addedAdditionalServices = [];
          this.cabinDto = response.data;
          this.cabinLoaded = true;
          this.currentImageUrl = this.cabinDto.images[0].url;
          this.maxImageIndex = this.cabinDto.images.length - 1;
          this.availableAdditionalServices = this.cabinDto.additionalServices;
          if (this.bookingProcess) this.calculatePrice();
        });
    },
    previousImage: function () {
      if (this.imageIndex > 0) {
        this.imageIndex--;
        this.currentImageUrl = this.cabinDto.images[this.imageIndex].url;
      }
    },
    nextImage: function () {
      if (this.imageIndex < this.maxImageIndex) {
        this.imageIndex++;
        this.currentImageUrl = this.cabinDto.images[this.imageIndex].url;
      }
    },
    clickedImage: function (index) {
      this.imageIndex = index;
      this.currentImageUrl = this.cabinDto.images[this.imageIndex].url;
    },
    getFullAddress: function () {
      return (
        this.cabinDto.addressDto.streetAndNum +
        ", " +
        this.cabinDto.addressDto.city +
        ", " +
        this.cabinDto.addressDto.country
      );
    },
    dataIsValid() {
      const date1 = new Date(this.start);
      const date2 = new Date(this.end);
      const currentDate = new Date();
      if (date1.getTime() - date2.getTime() > 0) {
        this.$swal.fire({
          position: "top-end",
          icon: "error",
          title: "Start date must be before end date!",
          showConfirmButton: false,
          timer: 1500,
        });
        return false;
      }
      if (date1.getTime() - currentDate.getTime() < 0) {
        this.$swal.fire({
          position: "top-end",
          icon: "error",
          title: "Start date can't be before today!",
          showConfirmButton: false,
          timer: 1500,
        });
        return false;
      }
      return true;
    },
    formatDate: function (formatDate) {
      const date = dayjs(formatDate);
      return date.format("YYYY-MM-DDTHH:mm:ss");
    },
    bookCabin: function () {
      if (!this.dataIsValid()) {
        return;
      }
      if (this.addedAdditionalServices == null)
        this.addedAdditionalServices = null;
      else if (this.addedAdditionalServices.length == 0)
        this.addedAdditionalServices = null;

      this.loader = this.$loading.show({
        // Optional parameters
        container: this.fullPage ? null : this.$refs.formContainer,
        canCancel: true,
        onCancel: this.onCancel,
      });

      axios
        .post(
          "http://localhost:8081/reservationCabin/makeReservation",
          {
            id: null,
            startDate: this.formatDate(this.start),
            endDate: this.formatDate(this.end),
            paymentInformationDto: {
              totalPrice: this.totalPrice,
              companysPart: 0,
              ownersPart: 0,
            },
            ownerWroteAReport: false,
            ownersUsername: this.cabinDto.ownerUsername,
            cabinDto: this.cabinDto,
            addedAdditionalServices: this.addedAdditionalServices,
            clientUsername: this.email,
          },
          {}
        )
        .then((response) => {
          this.availableCabins = response.data;
          this.display = "CABINS";
          this.loader.hide();
          this.$swal.fire({
            position: "top-end",
            icon: "success",
            title: "Cabin reservation successful!",
            showConfirmButton: false,
            timer: 2000,
          });
          this.$router.push("/reservations/" + this.email);
        })
        .catch((error) => {
          this.loader.hide();
          this.$swal.fire({
            icon: "error",
            title: "Something went wrong!",
            text: error.response.data,
          });
        });
    },
    subscribe: function () {
      axios
        .post(
          "http://localhost:8081/cabinSubscription/addSubscription",
          {
            cabinDto: this.cabinDto,
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
            timer: 2000,
          });
          this.getCabin();
        });
    },
    unsubscribe: function () {
      axios
        .post(
          "http://localhost:8081/cabinSubscription/removeSubscription",
          {
            cabinDto: this.cabinDto,
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
            timer: 2000,
          });
          this.getCabin();
        });
    },

    deleteCabin: function () {
      axios
        .post(
          "http://localhost:8081/cabins/canBeEditedOrDeleted/" +
            this.cabinDto.id
        )
        .then((response) => {
          if (response.data == true) {
            axios
              .post("http://localhost:8081/cabins/delete", this.cabinDto)
              .then((response) => {
                this.$router.push("/profileAdmin/" + this.email);
                return response;
              });
          } else {
            this.$swal.fire({
              position: "top-end",
              icon: "error",
              title:
                "This cabin has future reservations and can not be deleted!",
              showConfirmButton: false,
              timer: 2500,
            });
          }
        });
    },
  },
};
</script> 

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
}
#logincard {
  width: 47%;
  background-image: url("../../assets/IMG_3872.jpeg");
}
#nav a {
  font-weight: bold;
  color: #2c3e50;
}
#nav a.router-link-exact-active {
  color: #42b983;
}
</style>