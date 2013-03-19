package pt.iscte.daam2013.intentsdemo2013;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		TextView nome =  (TextView) findViewById(R.id.nameTxt);
		Intent intent = getIntent();
		Bundle data = intent.getExtras();
		if(data!=null){
			nome.setText(data.getCharSequence("NOME"));
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

}
