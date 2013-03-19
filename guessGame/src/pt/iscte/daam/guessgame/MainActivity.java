package pt.iscte.daam.guessgame;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.sax.TextElementListener;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

	public void onAdivinharButton(View v)
	{
		EditText valor = (EditText) findViewById(R.id.valorTextEdit);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("O valor introduzido:" + valor.getText().toString());
		
		AlertDialog dig = builder.create();
		dig.show();
		
		Toast.makeText(getApplicationContext(), "O valor introduzido:" + valor.getText().toString(), Toast.LENGTH_LONG).show();
	}
	
	public void onNovaActivity(View v)
	{
		startActivity(new Intent(this, SecondActivity.class));
	}
	
	public void onStartActividadeComDados(View v)
	{
		EditText valor = (EditText) findViewById(R.id.valorTextEdit);
		
		Intent i = new Intent(this, SecondActivity.class);
		Bundle bi = new Bundle();
		
		bi.putString("valor", valor.getText().toString());
		
		i.putExtras(bi);
		
		//startActivity(i);
		startActivityForResult(i, INPUT_REQUEST);
		
	}
	
	public void onAbrirURL(View v)
	{
		EditText url = (EditText) findViewById(R.id.valorTextEdit);
		
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url.getText().toString()));
		startActivity(i);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == Activity.RESULT_OK)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Activity2 = " + data.getStringExtra("dadosRetornados"));
			
			AlertDialog dig = builder.create();
			dig.show();
		}
	}
	
}
