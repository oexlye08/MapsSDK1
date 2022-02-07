package id.my.okisulton.mapsSdk.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds


/**
 *Created by osalimi on 06-01-2022.
 **/
class CameraAndViewPort {
    /*
        zoom level on maps
        1 = world
        5 = continent
        10 = city
        15 = street
        20 = Building
    */
    fun positionCamera(latLng: LatLng): CameraPosition {
        return CameraPosition.Builder()
            .target(latLng)
            .zoom(17f)
            .bearing(0f)
            .tilt(45f) // derajat kemiringan
            .build()
    }

    val temanggung  = LatLngBounds(
        LatLng(-7.370038275736354, 110.13582481214934), //SW
        LatLng(-7.2835463341430255, 110.22251380041436) // NE
    )
}