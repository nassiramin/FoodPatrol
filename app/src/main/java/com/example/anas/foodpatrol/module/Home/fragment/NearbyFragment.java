package com.example.anas.foodpatrol.module.Home.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.anas.foodpatrol.R;
import com.example.anas.foodpatrol.activity.MainActivity;
import com.example.anas.foodpatrol.adapter.CustomListAdapter;
import com.example.anas.foodpatrol.app.AppController;
import com.example.anas.foodpatrol.model.Jarak;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class NearbyFragment extends Fragment implements LocationListener,
        SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout swipe;
    ListView list;
    CustomListAdapter adapter;
    List<Jarak> itemList = new ArrayList<>();
    Double latitude, longitude;
    Criteria criteria;
    Location location;
    LocationManager locationManager;
    String provider;

    private static final String url = "http://192.168.181.2/Kuliner/haversine.php?lat=";
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nearby, container, false);
        list    = (ListView) v.findViewById(R.id.list);
        swipe   = (SwipeRefreshLayout) v.findViewById(R.id.swipe);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CustomListAdapter(getActivity(), itemList);
        list.setAdapter(adapter);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();

        provider = locationManager.getBestProvider(criteria, false);

        swipe.setOnRefreshListener(this);
        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           lokasi();
                       }
                   }
        );

    }

    public void onRefresh(){
        lokasi();
    }
    private void lokasi(){
        location = locationManager.getLastKnownLocation(provider);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        locationManager.requestLocationUpdates(provider, 10000, 1, this);

        if(location!=null){
            onLocationChanged(location);
            callListVolley(latitude, longitude);
        }else{
            Toast.makeText(getActivity().getBaseContext(), "Lokasi device pengguna tidak ditemukan.\nMohon hidupkan GPS.",
                    Toast.LENGTH_LONG).show();
           /* latitude longitude Alun-alun Demak sebagai default jika tidak ditemukan lokasi dari device pengguna */
            callListVolley(-6.894796, 110.638413);
        }
    }

    private void callListVolley(double lat, double lng) {
        itemList.clear();
        adapter.notifyDataSetChanged();

        swipe.setRefreshing(true);

        JsonArrayRequest jArr = new JsonArrayRequest(url + lat +"&lng="+ lng,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Jarak j = new Jarak();
                                j.setNama(obj.getString("nama"));
                                j.setGambar(obj.getString("gambar"));

                                double jarak = Double.parseDouble(obj.getString("jarak"));

                                j.setJarak(""+round(jarak, 2));

                                itemList.add(j);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // memberitahu adapter jika ada perubahan data
                        adapter.notifyDataSetChanged();

                        swipe.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity().getBaseContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                swipe.setRefreshing(false);
            }
        });

        // menambah permintaan ke queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

    public void onBackPressed(){
        getActivity().finish();
        System.exit(0);
    }

    // untuk menyederhanakan angka dibelakan koma jarak
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    // untuk menentukan lokasi gps dari device pengguna
    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        // untuk melihat latitude longitude posisi device pengguna pada logcat ditemukan atau tidak
        Log.d(TAG, " "+ latitude +", "+longitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}
