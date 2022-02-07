package id.my.okisulton.mapsSdk.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import id.my.okisulton.mapsSdk.R

/**
 *Created by osalimi on 10-01-2022.
 **/
class CustomInfoAdapter(context: Context) : GoogleMap.InfoWindowAdapter {
    private val contentView =
        (context as Activity).layoutInflater.inflate(R.layout.custom_info_window, null)

    override fun getInfoContents(marker: Marker): View? {
        renderView(marker, contentView)
        return contentView
    }

    override fun getInfoWindow(marker: Marker): View? {
        renderView(marker, contentView)
        return contentView
    }

    private fun renderView(marker: Marker?, contentView: View) {
        val title = marker?.title
        val desc = marker?.snippet

        val titleTextView = contentView.findViewById<TextView>(R.id.title_textView)
        titleTextView.text = title
        val descTextView = contentView.findViewById<TextView>(R.id.description_textView)
        descTextView.text = desc
    }
}