package id.my.okisulton.mapsSdk.misc

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

/**
 *Created by osalimi on 07-02-2022.
 **/
class MyItem(
    private val position : LatLng,
    private val title: String,
    private val snippet: String
): ClusterItem {
    override fun getPosition(): LatLng {
        return position
    }

    override fun getTitle(): String {
        return title
    }

    override fun getSnippet(): String {
        return snippet
    }
}