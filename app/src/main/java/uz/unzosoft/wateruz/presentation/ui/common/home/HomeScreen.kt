package uz.unzosoft.wateruz.presentation.ui.common.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.hitomi.cmlibrary.OnMenuStatusChangeListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import uz.unzosoft.wateruz.R
import uz.unzosoft.wateruz.data.local.LocalStorage
import uz.unzosoft.wateruz.data.models.api.OrdersItem
import uz.unzosoft.wateruz.data.models.api.OrdersResponse
import uz.unzosoft.wateruz.databinding.ScreenHomeBinding
import uz.unzosoft.wateruz.presentation.ui.adapters.OrdersAdapters
import uz.unzosoft.wateruz.presentation.ui.base.BaseScreen
import uz.unzosoft.wateruz.presentation.ui.utils.context.getBitmapDescriptor
import uz.unzosoft.wateruz.presentation.ui.utils.context.showSnackBar
import uz.unzosoft.wateruz.presentation.ui.utils.context.showToast
import javax.inject.Inject

@AndroidEntryPoint
class HomeScreen : BaseScreen(R.layout.screen_home), OnMapReadyCallback {
    override val viewModel: HomeVM by viewModels()
    private val adapter by lazy { OrdersAdapters() }
    private val binding by viewBinding(ScreenHomeBinding::bind)
    private lateinit var map: GoogleMap
    private var location: String? = null
    private var mMapFrag: SupportMapFragment? = null

    @Inject
    lateinit var cache: LocalStorage

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        statusBarColor()
        setupUi()
        mMapFrag = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        mMapFrag?.getMapAsync(this)
        viewModel.apply {
            ordersLiveData.observe(viewLifecycleOwner, ordersObserver)
            loadingLiveData.observe(viewLifecycleOwner, loadingObserver)
            ordersState.map {ordersObserver}.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    private fun setupUi() {
        binding.apply {
            circleMenu.setMainMenu(
                Color.parseColor("#D81B60"),
                R.drawable.ic_add_circle,
                R.drawable.ic_minus_cirlce
            )
                .addSubMenu(requireActivity().getColor(R.color.purple_700), R.drawable.ic_call)
                .addSubMenu(requireActivity().getColor(R.color.holo_purple), R.drawable.ic_location_cross)
                .addSubMenu(requireActivity().getColor(R.color.holo_blue_bright), R.drawable.ic_profile)
                .setOnMenuSelectedListener {
                    when (it) {
                        0 -> {
                            showSnackBar("Call")
                        }
                        1 -> {
                            showSnackBar("Location")
                        }
                        2 -> {
                            showSnackBar("Profile")
                        }
                    }
                }.setOnMenuStatusChangeListener(object : OnMenuStatusChangeListener {
                    override fun onMenuOpened() {

                    }

                    override fun onMenuClosed() {

                    }
                })
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private val ordersObserver = Observer<List<OrdersItem>> {
        var location = it[2].client?.location
        binding.ordersRv.adapter = adapter
        adapter.submitList(it)
    }
    private val loadingObserver = Observer<Unit> {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        location = "39.726333052078786,64.52545166015625"
        val latLng = location?.split(",")?.toList()
        val lat = latLng?.get(0)?.toDouble()
        val lng = latLng?.get(1)?.toDouble()
        val samarkand = LatLng(lat!!, lng!!)
        map.addMarker(
            MarkerOptions().position(samarkand).title("Samarqand")
                .icon(activity?.getBitmapDescriptor(R.drawable.ic_car))
        )
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(samarkand, 13f))
        googleMap.uiSettings.isCompassEnabled = true
        googleMap.uiSettings.isMapToolbarEnabled = true
        googleMap.uiSettings.isZoomControlsEnabled = true
    }

}