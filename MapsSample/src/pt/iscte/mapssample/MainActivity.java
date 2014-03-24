package pt.iscte.mapssample;

import android.os.Bundle;
import android.view.Menu;

import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap;

public class MainActivity extends FragmentActivity {

	SupportMapFragment mMap;
	GoogleMap googleMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mMap = (SupportMapFragment) getSupportFragmentManager()
	            .findFragmentById(R.id.map);

	            googleMap = mMap.getMap();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}