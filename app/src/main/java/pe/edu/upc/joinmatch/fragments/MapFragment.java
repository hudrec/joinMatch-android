package pe.edu.upc.joinmatch.fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.edu.upc.joinmatch.R;
import pe.edu.upc.joinmatch.models.Event;
import pe.edu.upc.joinmatch.network.EventsApi;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    private static String TAG = "JoinMAtch";
    private JSONArray events = new JSONArray();
    public MapFragment() {
        // Required empty public constructor
    }
    private void updateData() {

        AndroidNetworking.get(EventsApi.EVENTS_URL)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        /*if(response.getString("status").equalsIgnoreCase("error")) {
                            Log.d(TAG, response.getString("message"));
                            return;
                        }*/

                        events = response;



                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getLocalizedMessage());
                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_map, container, false);
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                //Criteria criteria = new Criteria();
                //LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                //String provider = locationManager.getBestProvider(criteria, false);
                LatLng sydney = new LatLng(-34, 151);
                LatLng upc = new LatLng(-12.087856,-77.0525091);
                //Location location = locationManager.getLastKnownLocation(provider);
                //double lat =  location.getLatitude();
                //double lng = location.getLongitude();
                //LatLng coordinate = new LatLng(lat, lng);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
                googleMap.addMarker(new MarkerOptions().position(upc).title("UPC").snippet("Peloteada"));
                for(int i = 0; i < events.length(); i++) {
                    try {
                        JSONObject event = events.getJSONObject(i);
                        LatLng home = new LatLng(-1*Double.parseDouble(event.getString("latitude")),
                                -1*Double.parseDouble(event.getString("longitud")));

                        googleMap.addMarker(new MarkerOptions().position(home).title("UPC").snippet("Peloteada"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(upc).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }



    @Override
    public void onResume() {
        super.onResume();
        updateData();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


}
