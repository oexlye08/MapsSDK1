package id.my.okisulton.geofencing.misc

import android.content.Context
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions
import id.my.okisulton.geofencing.R
import java.lang.Exception

/**
 *Created by osalimi on 06-01-2022.
 **/
class TypeAndStyle {
    private val masterFunction by lazy { MasterFunction() }

    fun setMapStyle(mMap: GoogleMap, context: Context) {
        try {
            val success = mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    context,
                    R.raw.stylemaps
                )
            )
            if (!success) {
                masterFunction.showMessages("Failed to add style", context)
            }
        }catch (e: Exception){
            masterFunction.showMessages(e.message.toString(), context)
        }
    }

    fun setMapType(item: MenuItem, mMap: GoogleMap) {
        when(item.itemId){
            R.id.normalMap -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
            R.id.hybridMap -> {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
            R.id.satelitMap -> {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }
            R.id.terrainMap -> {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
            R.id.noneMap -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NONE
            }
        }
    }
}