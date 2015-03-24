package pt.iscte_iul.guideme;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MainActivity extends ActionBarActivity implements OnMapReadyCallback {
    public double latitude, longitude;
    public GoogleMap t_map;
    public Map <Marker, Integer> hmap;
    public static final String PREFS_NAME = "AppData";
    public static final int RANGE = 50;
    public int myRange;
    public String serviceURL;
    public static String APIURL = "http://192.168.1.104:3000";
    protected SharedPreferences appData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        latitude = i.getDoubleExtra("latitude", 0);
        longitude = i.getDoubleExtra("longitude", 0);

        appData = getSharedPreferences(PREFS_NAME, 0);
        //try to read from SharedPreferences
        myRange = appData.getInt("range", RANGE);
        serviceURL = appData.getString("URL", APIURL);
        Log.i("guideMe", "Range is = " + myRange);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
        t_map = mapFragment.getMap();

        t_map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Log.i("guideMe", "Click on marker with ID = " + hmap.get(marker));
                Intent i = new Intent(MainActivity.this, POIDetailsActivity.class);
                i.putExtra("id", hmap.get(marker));
                startActivity(i);
            }
        });

        t_map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                // delete all POIs from map
                for(Map.Entry<Marker,Integer> entry : hmap.entrySet()) {
                    entry.getKey().remove();
                }
                // get and add new POIs
                new getPOIs().execute();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng myPos = new LatLng(latitude, longitude);


        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(myPos, 13));

        new getPOIs().execute();
    }

    private class getPOIs extends AsyncTask<String, Void, String> {
        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Getting POIs from server...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params)
        {
            String response = null;

            try {
                myRange = appData.getInt("range", RANGE);
                Log.i("guideMe", "Using this range = " + myRange);
                HttpClient httpclient = new DefaultHttpClient();
                Log.i("guideMe", "Asking  = " + serviceURL + "/poi/range/"+latitude+"/"+longitude+"/"+myRange*1000);

                HttpResponse httpResponse = httpclient.execute(new HttpGet(serviceURL + "/poi/range/"+latitude+"/"+longitude+"/"+myRange*1000));

                BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
                response = reader.readLine();

                Log.i("guideMe", "Response = " + response);

            } catch(Exception e) {
                Log.i("guideMe", "An exception has occured in the connection - " + e.toString());
                return "-ERR";
            }

            return response;
        }

        @Override
        protected void onPostExecute(String result)
        {
            dialog.dismiss();

            try {
                // now, we have to handle all the necessary results and add them to the map
                JSONObject jobj = new JSONObject(result);

                if(jobj.get("status").toString().compareTo("OK")==0) {
                    JSONArray poi = jobj.getJSONArray("poi");
                    hmap = new HashMap<Marker, Integer>();
                    for(int i=0; i<poi.length(); i++) {
                        JSONObject t_poi = poi.getJSONObject(i);
                        Log.i("guideMe", "-> " + t_poi.getString("name"));

                        //add the marker to the map
                        Marker m = t_map.addMarker(new MarkerOptions()
                                .title(t_poi.getString("name"))
                                .snippet(t_poi.getString("address"))
                                .position(new LatLng(t_poi.getDouble("latt"), t_poi.getDouble("logt"))));


                        hmap.put(m, t_poi.getInt("id"));
                    }
                }

            } catch (Exception e) {
                Log.i("guideMe", "An exception has occured in the connection - " + e.toString());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}