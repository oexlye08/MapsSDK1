package id.my.okisulton.geofencing.misc

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import id.my.okisulton.geofencing.R

/**
 *Created by osalimi on 20-01-2022.
 **/
class Overlays {
    private val kinarya = LatLng(-7.7646443734274415, 110.43661213363588)

    fun addGroundOverlay(map: GoogleMap): GroundOverlay? {
        val groundOverlay = map.addGroundOverlay(
            GroundOverlayOptions().apply {
                position(kinarya, 1000f, 1000f)
                image(BitmapDescriptorFactory.fromResource(R.drawable.pin_png))
            }
        )
        return groundOverlay
    }
    fun addGroundOverlayWithTag(map: GoogleMap): GroundOverlay? {
        val groundOverlayTag = map.addGroundOverlay(
            GroundOverlayOptions().apply {
                position(kinarya, 100f, 100f)
                image(BitmapDescriptorFactory.fromResource(R.drawable.pin_png))
            }
        )
        groundOverlayTag?.tag = "Ground overlay Tag"
        return groundOverlayTag
    }
}