package pt.iscte_iul.samplemaps;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class ChangeMapTypeActivity extends ActionBarActivity implements OnMapReadyCallback {
    protected GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_map_type);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapViewChangeMapType);
        mapFragment.getMapAsync(this);
        map = mapFragment.getMap();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng iscteiul = new LatLng(38.748547784895, -9.155314987341285);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(iscteiul, 10));

        map.addMarker(new MarkerOptions()
                .title("ISCTE-IUL")
                .snippet("O melhor sítio para estudar!")
                .position(iscteiul));

        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    public void mapOpen(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void hybridOpen(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

    public void terrainOpen(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_map_type, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
