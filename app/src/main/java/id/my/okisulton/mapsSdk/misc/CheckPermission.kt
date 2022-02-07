package id.my.okisulton.mapsSdk.misc

import android.Manifest
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import id.my.okisulton.mapsSdk.MapsActivity

/**
 *Created by osalimi on 07-02-2022.
 **/
class CheckPermission {
//    private fun requestLocationPermission() {
//        EasyPermissions.requestPermissions(
//            this,
//            "This application not working without location permission!",
//            MapsActivity.PERMISSION_LOCATION_REQUEST_CODE,
//            Manifest.permission.ACCESS_FINE_LOCATION
//        )
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
//    }
//
//    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//            SettingsDialog.Builder(this).build().show()
//        } else {
//            requestLocationPermission()
//        }
//    }
//
//    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
//        setViewVisibility()
//    }
}