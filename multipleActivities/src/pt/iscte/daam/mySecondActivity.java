package pt.iscte.daam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mySecondActivity extends Activity {
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
	}
	
	public void handleButton(View v)
	{
		switch(v.getId())
		{
		case R.id.button1: 
			// add intent data
			Intent data = new Intent();
			data.putExtra("mydata", "returned from secondary activity");
			
			setResult(Activity.RESULT_OK, data);
			
	    	finish();
			break;
		}
	}
}
