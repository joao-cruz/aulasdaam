package pt.iscte.mapssample;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class ChangeMapActivity extends Activity {
	
	GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_map);
		
		map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-18.142, 178.431), 2));

        // Other supported types include: MAP_TYPE_NORMAL,
        // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_map, menu);
		return true;
	}
	
	//clickMapButton
	public void clickMapButton(View v)
	{
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	}
	//clickHybridButton
	public void clickHybridButton(View v)
	{
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	}
	//clickTerrainButton
	public void clickTerrainButton(View v)
	{
		map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
	}

}
