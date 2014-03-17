package pt.iscte.mathmatrix;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Notification.Action;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	public static final String PREFS_NAME = "mathprefs";


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
	
	public void jogarButtonClick(View v)
	{
		startActivity(new Intent(this, JogoActivity.class));
		
		EditText idade = (EditText) findViewById(R.id.txtIdade);
		
		Log.i("MathMatrix", "Idade = " + idade.getText().toString());
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("idade", idade.getText().toString());
		editor.commit();
		
	}
	
	//Intent urlInt = new Intent(Intent.ACTION_VIEW);
	//urlInt.setData(Uri.parse("http://www.iscte.pt"));
	//startActivity(urlInt);
	
	//Intent telfInt = new Intent(Intent.ACTION_CALL);
	//telfInt.setData(Uri.parse("tel:912345667"));
	//startActivity(telfInt);
			
	public void saberMaisClickButton(View v)
	{		
		Intent urlInt = new Intent(Intent.ACTION_VIEW);
		urlInt.setData(Uri.parse("http://www.iscte.pt"));
		startActivity(urlInt);
	}

}
