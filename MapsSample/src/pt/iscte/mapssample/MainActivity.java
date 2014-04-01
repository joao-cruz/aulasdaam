package pt.iscte.mapssample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clickSimpleMapButton(View v)
	{
		startActivity(new Intent(this, BasicMapActivity.class));
	}
	
	public void clickPositionAndZoomButton(View v)
	{
		startActivity(new Intent(this, PositionAndZoomActivity.class));
	}
	
	public void clickChangeMapButton(View v)
	{
		startActivity(new Intent(this, ChangeMapActivity.class));
	}
	
	public void clickIndoorMapsButton(View v)
	{
		startActivity(new Intent(this, IndoorMapsActivity.class));
	}

	public void clickMapMarkersButton(View v)
	{
		startActivity(new Intent(this, MapMarkersActivity.class));
	}
	
	public void clickCameraAnimationsButton(View v)
	{
		startActivity(new Intent(this, CameraAnimationsActivity.class));
	}
	
	public void clickMapLinesButton(View v)
	{
		startActivity(new Intent(this, MapLinesActivity.class));
	}
}
