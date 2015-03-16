package pt.iscte.daam.guessgame;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		TextView v = (TextView) findViewById(R.id.valorTextView);
		
		Intent i= getIntent();
		Bundle d = i.getExtras();
		
		if (d!=null)
		{
			v.setText(d.getString("valor"));
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}
	
	public void onVoltarButton(View v)
	{
		TextView val = (TextView) findViewById(R.id.valorTextView);
		
		Intent dadosARetornar = new Intent();
		dadosARetornar.putExtra("dadosRetornados", "Valor passado originalmente = " + val.getText().toString());
		setResult(Activity.RESULT_OK, dadosARetornar);
		
		finish();
		
	}

}
