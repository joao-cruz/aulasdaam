package pt.iscte.testandroidlayout;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

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
	
	public void carregaButtonClick(View v)
	{
		TextView tv = (TextView) findViewById(R.id.textView1);
		
		tv.setText("Carregou no bot‹o!!!");
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Carregou no botao!!!");
		builder.setCancelable(true);

		AlertDialog alert = builder.create();
		alert.show();
		
	}

}
