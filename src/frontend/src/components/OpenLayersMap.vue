<template>
<ol-map :loadTilesWhileAnimating="true" :loadTilesWhileInteracting="true" style="height:400px">

    <ol-view ref="view" :center=coordinates :rotation="rotation" :zoom="zoom" 
    :projection="projection" />

    <ol-tile-layer>
        <ol-source-osm />
    </ol-tile-layer>
     <ol-vector-layer>
        <ol-source-vector>
              
            <ol-feature>
                <ol-geom-point :coordinates=coordinates></ol-geom-point>
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
<div ref="map-container"
        style="width: 100%; height: 100%">
    </div>
</template>
<script>
import {
    ref
} from 'vue'

export default {
    props: {
    coordinates: [],
    },
    setup() {
        const center = ref([40, 40])
        const projection = ref('EPSG:4326')
        const zoom = ref(8)
        const rotation = ref(0)
        return {
            center,
            projection,
            zoom,
            rotation
        }
    },
}
</script>