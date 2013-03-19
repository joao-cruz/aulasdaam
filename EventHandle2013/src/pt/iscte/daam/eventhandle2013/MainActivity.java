package pt.iscte.daam.eventhandle2013;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
	
	public void onPressMeButton(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Thank you!!!");
		builder.setCancelable(true);
		builder.setPositiveButton("Red Pill", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "You selecte the Red Pill. There's no turning back!", Toast.LENGTH_SHORT).show();
			}
		});
		
		builder.setNegativeButton("Blue Pill", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "You selecte the Blue Pill. You have no guts!!!", Toast.LENGTH_SHORT).show();
			}
		});
		
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	public void onCanYouPressMeTooBtn(View v){
		final CharSequence[] items = {"Red", "Green", "Blue"};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Pick a color");
		
		builder.setItems(items, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
			}
		});

		AlertDialog alert = builder.create();
		alert.show();
	}

	public void onShowMeTheNameBtn(View v){
		EditText nome = (EditText) findViewById(R.id.editText1);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Your name...");
		builder.setMessage(nome.getText().toString() + ", you may enter the Matrix.");
		
		AlertDialog alert = builder.create();
		alert.show();
		
	}
}
