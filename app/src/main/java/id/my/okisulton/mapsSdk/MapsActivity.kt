package id.my.okisulton.mapsSdk

import android.Manifest
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import id.my.okisulton.mapsSdk.databinding.ActivityMapsBinding
import id.my.okisulton.mapsSdk.misc.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(),
    OnMapReadyCallback,
    EasyPermissions.PermissionCallbacks,
    GoogleMap.OnMarkerClickListener,
    GoogleMap.OnMarkerDragListener,
    GoogleMap.OnPolylineClickListener {

    companion object {
        const val PERMISSION_LOCATION_REQUEST_CODE = 1
    }

    private lateinit var mMap: GoogleMap
    private var _binding: ActivityMapsBinding? = null
    private val binding get() = _binding

    private val typeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewPort by lazy { CameraAndViewPort() }
    private val masterFunction by lazy { MasterFunction() }
    private val shapes by lazy { Shapes() }
    private val overlays by lazy { Overlays() }

    private lateinit var clusterManager: ClusterManager<MyItem>
    private val localeList = listOf(
        LatLng(
            -7.757811596531729,
            110.41946411132812,
        ),
        LatLng(
            -7.761978786926806,
            110.42487144470213,
        ),
        LatLng(
            -7.766613674653602,
            110.41997909545898,
        ),
        LatLng(
            -7.77031304375889,
            110.4127264022827,
        ),
        LatLng(
            -7.769760266538134,
            110.42667388916016,
        ),
        LatLng(
            -7.77949754335223,
            110.4122543334961,
        ),
        LatLng(
            -7.780475510630797,
            110.43474197387694,
        ),
        LatLng(
            -7.767251499237745,
            110.43757438659668,
        ),
        LatLng(
            -7.756706008475139,
            110.4328966140747,
        ),
        LatLng(
            -7.757811596531729,
            110.41946411132812,
        ),
    )

    private val jakarta = LatLng(-6.209932521996178, 106.85216437477612)
    private val kinarya = LatLng(-7.7646443734274415, 110.43661213363588)
    private val home = LatLng(-7.366166508639234, 110.16435351313473)
    private val hawai = LatLng(19.44837319638125, -155.31798377034738)
    private val iceland = LatLng(65.32478740490669, -19.07198693723813)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
//        requestLocationPermission()
        setupListener()
    }

    private fun setupListener() {
        binding?.fabLoc?.setOnClickListener {
            masterFunction.showMessages("FAB Clicked", this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.maps_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        typeAndStyle.setMapType(item, mMap)
        return true
    }

    @SuppressLint("MissingPermission", "PotentialBehaviorOverride")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val jakartaMarker = mMap.addMarker(
            MarkerOptions()
                .position(localeList.first())
                .title("Marker in Jakarta")
                .snippet("this is a desc")
                .draggable(true)
                .icon(
                    masterFunction.fromVectorToBitmap(
                        resources,
                        R.drawable.ic_pin,
                        Color.parseColor("#35858B")
                    )
                )
        )

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kinarya, 10f))
        mMap.setOnMarkerClickListener(this)
        mMap.setOnMarkerDragListener(this)
//        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewPort.positionCamera(jakarta)))
        mMap.uiSettings.apply {
            isZoomControlsEnabled = true
            //digunakan agar maps tidak bisa digeser
//            isScrollGesturesEnabled = false
            isMyLocationButtonEnabled = true
        }

        mMap.isMyLocationEnabled = true

//        val groundOverlay = overlays.addGroundOverlay(mMap)

        //add from shape
//        shapes.addPolygon(mMap)
        // Custom Info Window
//        mMap.setInfoWindowAdapter(CustomInfoAdapter(this))
        typeAndStyle.setMapStyle(mMap, this)

        addHeatmap()

//        mMap.setOnPolylineClickListener(this)
//        lifecycleScope.launch {
//            delay(3000L)
//            masterFunction.showLog(TAG = "GroundOverlay TAG",
//                messages = overlays.addGroundOverlayWithTag(mMap)?.tag.toString())
//            groundOverlay?.transparency = 0.5f

        // Add Shape
//            shapes.addCircle(mMap)
//            shapes.addPolyline(mMap)

////            mMap.moveCamera(CameraUpdateFactory.zoomBy(8f))
////            mMap.moveCamera(CameraUpdateFactory.newLatLng(kinarya))
////            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewPort.temanggung,0))
////            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewPort.temanggung,0), 2000, null)
//
//            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewPort.positionCamera(jakarta)), 2000, object : GoogleMap.CancelableCallback{
//                override fun onCancel() {
//                    showMessages("Canceled animated")
//                }
//
//                override fun onFinish() {
//                    showMessages("Finished animated")
//                }
//
//            })
        // to restrict camera movement
//            mMap.setLatLngBoundsForCameraTarget(cameraAndViewPort.temanggung)
//        }
//
//        singleClickMap()
//        longClickMap()
    }

    private fun addHeatmap(){
        val color = intArrayOf(
            Color.rgb(88, 37, 155), // Purple
            Color.rgb(84, 182, 239), // Blue
            Color.rgb(255, 51, 51), // Red
        )

        val startPoint = floatArrayOf(0.25f, 0.5f, 1f)
        val gradient = Gradient(color, startPoint)

        val provider = HeatmapTileProvider.Builder()
            .data(localeList)
            .gradient(gradient)
            .opacity(0.30)
            .build()
        val overlay = mMap.addTileOverlay(TileOverlayOptions().tileProvider(provider))

        lifecycleScope.launch {
            delay(5000)
            overlay?.clearTileCache()
            provider.setRadius(50)
            delay(3000)
            overlay?.remove()
        }
    }

    private fun longClickMap() {
        mMap.setOnMapLongClickListener {
            mMap.clear()
            mMap.addMarker(
                MarkerOptions()
                    .position(it)
                    .title("New Marker")
                    .snippet("Info Window")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_png))
            )
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it, 20f), 2000, null)
        }
    }

    private fun singleClickMap() {
        mMap.setOnMapClickListener {
            masterFunction.showMessages("Single Clicked", this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setViewVisibility() {
        if (hasLocationPermission()) {
            masterFunction.showMessages("Permission Granted", this)
        } else {
            masterFunction.showMessages("Permission Denied", this)
        }
    }

    private fun hasLocationPermission() =
        EasyPermissions.hasPermissions(
            context = this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

    override fun onMarkerClick(marker: Marker): Boolean {
        masterFunction.showMessages("marker is clicked", this)
        return false
    }

    override fun onMarkerDrag(p0: Marker) {
        masterFunction.showLog("Drag", "Drag")
    }

    override fun onMarkerDragEnd(p0: Marker) {
        masterFunction.showLog("Drag", "End")
    }

    override fun onMarkerDragStart(p0: Marker) {
        masterFunction.showLog("Drag", "Start")
    }

    override fun onPolylineClick(p0: Polyline) {
        masterFunction.showMessages("Polyline Clicked", this)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(this).build().show()
        } else {
//            requestLocationPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        setViewVisibility()
    }


}