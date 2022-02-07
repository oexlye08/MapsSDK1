package id.my.okisulton.mapsSdk.misc

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.maps.model.BitmapDescriptorFactory

/**
 *Created by osalimi on 06-01-2022.
 **/
class MasterFunction {

    fun showMessages(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showLog(TAG: String, messages: String) {
        Log.d(TAG, "showLog: $messages")
    }


    fun fromVectorToBitmap(
        resources: Resources,
        id: Int,
        color: Int
    ): BitmapDescriptor {
        val vectorDrawable: Drawable? = ResourcesCompat.getDrawable(resources, id, null)
        if (vectorDrawable == null){
            showLog("from vector to bitmap", "Resources not found!")
            return BitmapDescriptorFactory.defaultMarker()
        }
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0,0,canvas.width, canvas.height)
        DrawableCompat.setTint(vectorDrawable, color)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}