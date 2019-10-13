package project.ui.map

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.animation.OvershootInterpolator
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.R
import ir.sinapp.classroom.databinding.ActivityMapBinding
import project.ui.base.BaseActivity
import project.utils.AppLogger
import project.utils.map.OnMapAndViewReadyListener
import javax.inject.Inject
import project.utils.map.DirectionCallback
import project.utils.map.util.DirectionConverter
import project.utils.map.constant.TransportMode
import project.utils.map.GoogleDirection
import project.utils.map.model.Direction
import project.utils.map.model.Route


class MapActivity : BaseActivity<ActivityMapBinding, MapViewModel>(ActivityMapBinding::class.java),
    MapNavigator, OnMapAndViewReadyListener.OnGlobalLayoutAndMapReadyListener, DirectionCallback {

    override val bindingVariable: Int
        get() = BR.viewModel

    @Inject
    override lateinit var viewModel: MapViewModel


    private val serverKey = "AIzaSyBq0OjqeLJmCBlS-BA81YlY8rhrnhJzQQc"
    private val origin = LatLng(37.7849569, -122.4068855)
    private val destination = LatLng(37.7814432, -122.4460177)


    lateinit var map:GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this


        val mapFragment = supportFragmentManager.findFragmentById(R.id.map)
                as SupportMapFragment
        OnMapAndViewReadyListener(mapFragment, this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {

        if (googleMap != null) {
            map = googleMap
        }

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = googleMap!!.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.style_map
                )
            )

            if (!success) {
                AppLogger.i("Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            AppLogger.i("Can't find style. Error: $e")
        }

        with(googleMap!!.uiSettings) {
            isTiltGesturesEnabled = false
            isRotateGesturesEnabled = false
        }

        requestDirection()
    }


    private fun requestDirection() {
        GoogleDirection.withServerKey(serverKey)
            .from(origin)
            .to(destination)
            .transportMode(TransportMode.DRIVING)
            .execute(this)
    }

    override fun onDirectionSuccess(direction: Direction, rawBody: String) {
        if (direction.isOK) {
            val route = direction.routeList[0]
            map.addMarker(MarkerOptions().position(origin))
            map.addMarker(MarkerOptions().position(destination))

            val directionPositionList = route.legList.get(0).directionPoint
            map.addPolyline(
                DirectionConverter.createPolyline(
                    this,
                    directionPositionList,
                    5,
                    Color.RED
                )
            )
            setCameraWithCoordinationBounds(route)

        } else {
            AppLogger.i("err dir")
        }
    }

    override fun onDirectionFailure(t: Throwable?) {
        AppLogger.i("err dir  $t")
    }

    private fun setCameraWithCoordinationBounds(route: Route) {
        val southwest = route.bound.southwestCoordination.coordination
        val northeast = route.bound.southwestCoordination.coordination
        val bounds = LatLngBounds(southwest, northeast)
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
    }

//      private void setCameraWithCoordinationBounds(Route route) {
//        LatLng southwest = route.getBound().getSouthwestCoordination().getCoordination();
//        LatLng northeast = route.getBound().getNortheastCoordination().getCoordination();
//        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
//    }

}




