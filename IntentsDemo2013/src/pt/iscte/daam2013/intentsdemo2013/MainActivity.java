package pt.iscte.daam2013.intentsdemo2013;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore.Action;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	static int INPUT_REQUEST = 0;

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

	public void onNameEnterButton(View v)
	{
		// call the activity, do not pass anything
		//startActivity(new Intent(this, SecondActivity.class));
		
		EditText name = (EditText) findViewById(R.id.nameEditTxt);
		
		Intent activityIntent = new Intent(this, SecondActivity.class);
		Bundle newActivityInfo = new Bundle();
		newActivityInfo.putCharSequence("NOME", name.getText());
		activityIntent.putExtras(newActivityInfo);
		startActivity(activityIntent);
	}
	
	public void onGotoURLButton(View v)
	{
		EditText url = (EditText) findViewById(R.id.urlTextEdit);
		Intent urlIntent = new Intent(Intent.ACTION_VIEW);
		urlIntent.setData(Uri.parse(url.getText().toString()));
		
		startActivity(urlIntent);
	}
	
}
