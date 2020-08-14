package com.thinktum.edna_v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMarkerClickListener(this)

        val mapSpot1 = db.collection("Vending Machine").document("thinktum Head Office 001")
        mapSpot1.get().addOnSuccessListener { document ->
            if(document != null) {

                val thinktumLat = document.getString("Latitude")
                val thinktumLatDBL : Double = thinktumLat!!.toDouble()

                val thinktumLong = document.getString("Longitude")
                val thinktumLongDBL : Double = thinktumLong!!.toDouble()

                mMap.setOnMarkerClickListener(this)

                val thinktum = LatLng(thinktumLatDBL, thinktumLongDBL)
                mMap.addMarker(MarkerOptions().position(thinktum).title("Vending Machine - thinktum inc. 222"))


                //mMap.moveCamera(CameraUpdateFactory.newLatLng(thinktum))
                // Move the camera instantly to Sydney with a zoom of 15.
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(thinktum, 15f))

            } else {
                Log.d("DB Error", "DB does not exist")
            }
        }
            .addOnFailureListener { exception ->
                Log.d("DB Error", "DB get() failure with ", exception)
            }

    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        val MarkerTitle = p0!!.title



        val intent = Intent(this, OrderActivity::class.java)

        intent.putExtra("VendingMachine", MarkerTitle)
        startActivity(intent)
        return true
    }


    /*db.collection("products").addSnapshotListener{
        result, e->
        if (e != null){
            log.w(TAG, "Listen failed.", e)
            return@addSnapshotListener
        }
        arProducts.clear()
        arProducts.addAll(result!!.toObjects(Product::class.java))

        for(product in arProducts){
            val geoPosition = LatLng(-34.0, 151.0)
            mMap.addMarker(MarkerOptions().position(geoPosition).title(product.name))
        }
    }*/

        // Add a marker to thinktum and move the camera
        /*val thinktum = LatLng(43.4792349,-80.5446183)
        mMap.addMarker(MarkerOptions().position(thinktum).title("Vending Machine - thinktum inc. 1"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(thinktum))*/

}