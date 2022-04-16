<template>
                    <ol-map class="map" @click="clicked">
                        <ol-view
                            ref="view"
                            :center="center"
                            :rotation="rotation"
                            :zoom="zoom"
                            :projection="projection"
                        />

                        <ol-tile-layer>
                            <ol-source-osm />
                        </ol-tile-layer>
                        <ol-vector-layer>
                            <ol-source-vector>
                                <ol-feature>
                                    <ol-geom-point
                                        :coordinates="coordinate"
                                    ></ol-geom-point>
                                    <ol-style>
                    <ol-style-circle radius="30">
                        <ol-style-fill color="white"></ol-style-fill>
                        <ol-style-stroke color="blue" :width="10" ></ol-style-stroke>
                    </ol-style-circle>
                </ol-style>
                                    
                                </ol-feature>
                            </ol-source-vector>
                        </ol-vector-layer>
                    </ol-map>
</template>

<script>
import { ref } from "vue";
export default {
    name: "ChooseLocation",
    setup() {
        const projection = ref("EPSG:4326");
        const zoom = ref(8);
        const rotation = ref(0);
        const view = ref(null);
        const map = ref(null);
        return {
            projection,
            zoom,
            rotation,
            view,
            map
            
        };
    },
    props: {
        coordinates: Array,
        updateShowMap: Function,
        updateCoords: Function,
    },
    components: {},
    data() {
        return {
            center:
                this.$props.coordinates != undefined
                    ? this.$props.coordinates
                    : [19.833549, 45.267136],
            coordinate: this.$props.coordinates,
            longitude:
                this.$props.coordinates != undefined
                    ? this.$props.coordinates[0]
                    : "",
            latitude:
                this.$props.coordinates != undefined
                    ? this.$props.coordinates[1]
                    : "",
            buttonText: this.$props.prevPage,
        };
    },
    mounted() {},
    methods: {
        clicked(evt) {
            if (evt.isTrusted != undefined) {
                return;
            }
            this.coordinate = evt.coordinate;
            this.longitude = evt.coordinate[0];
            this.latitude = evt.coordinate[1];
            this.$parent.updateLocation(this.longitude,this.latitude);
            
        },
        Toggle() {
            this.$props.updateShowMap();
        },
    },
};
</script>

<style scoped>
.map {
    position: relative;
    margin: auto;
    width: 100%;
    height: 100%;
}
</style>