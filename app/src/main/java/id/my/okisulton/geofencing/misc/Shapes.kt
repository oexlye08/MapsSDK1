package id.my.okisulton.geofencing.misc

import android.graphics.Color
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import id.my.okisulton.geofencing.R
import kotlinx.coroutines.delay

/**
 *Created by osalimi on 19-01-2022.
 **/
class Shapes {
    private val jakarta = LatLng(-6.209932521996178, 106.85216437477612)
    private val kinarya = LatLng(-7.7646443734274415, 110.43661213363588)
    private val home = LatLng(-7.366166508639234, 110.16435351313473)
    private val hawai = LatLng(19.44837319638125, -155.31798377034738)
    private val iceland = LatLng(65.32478740490669, -19.07198693723813)

    private val p0 = LatLng(5.459298377321468, 95.47583312715659)
    private val p1 = LatLng(6.139423991142308, 141.15465123898542)
    private val p2 = LatLng(-10.20154750811824, 141.27894734269108)
    private val p3 = LatLng(-8.668913509843613, 95.7244253345679)

    private val p00 = LatLng(0.5578726037060444, 108.52692401625052)
    private val p01 = LatLng(-0.3121898168458265, 129.346521386948)
    private val p02 = LatLng(-6.204097182925468, 131.7081473573555)
    private val p03 = LatLng(-4.781292378515169, 108.65122011995618)

    suspend fun addPolyline(mMap: GoogleMap) {
        val pattern = listOf(Dot(), Gap(30f), Dash(50f), Gap(30f))
        val polyline = mMap.addPolyline(
            PolylineOptions().apply {
                add(jakarta, home, kinarya)
                width(5f)
                color(Color.GREEN)
                geodesic(true) // agar polyline mengikuti bentuk bumi
                clickable(true)
                // Add Pattern - . - . -.
//                pattern(pattern)
                // Add Join type
                jointType(JointType.ROUND)
                startCap(RoundCap())
                endCap(CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.pin_png), 8f))
            }
        )
        delay(4000L)

        polyline.points = listOf(
            iceland, jakarta, hawai
        )
    }

    fun addPolygon(mMap: GoogleMap) {
        val polygon = mMap.addPolygon(
            PolygonOptions().apply {
                add(p0, p1, p2, p3)
                fillColor(R.color.teal_200)
                strokeColor(R.color.teal_200)
                addHole(listOf(p00,p01,p02,p03))
            }
        )
    }

    suspend fun addCircle (mMap: GoogleMap) {
        val circle = mMap.addCircle(
            CircleOptions().apply {
                center(kinarya)
                radius(50000.0)
                fillColor(R.color.purple_200)
                strokeColor(R.color.purple_200)
            }
        )

        delay(4000L)
        circle.fillColor = (R.color.black)
    }
}