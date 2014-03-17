package pt.iscte.mathmatrix;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class JogoActivity extends Activity {

	public static final String PREFS_NAME = "mathprefs";
	
	public int age;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_jogo);
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		
		String myage = settings.getString("idade", "");
		
		age = Integer.parseInt(myage);
		
		String question = "";
		
		if (age <= 10)
		{
			question = "(1 + 2) + 1";
		}
		
		if (age > 10) 
		{
			question = "(3 + 1) x 4";
		}
		
		
		TextView tp = (TextView) findViewById(R.id.txtPergunta);
		tp.setText(question);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jogo, menu);
		return true;
	}
	
	public void enviarRespostaClick(View v)
	{
		EditText resposta = (EditText) findViewById(R.id.txtResposta);
		
		Log.i("Mathmatrix", "Valor da resposta = " + resposta.getText().toString());
		
		int r = Integer.parseInt(resposta.getText().toString());
		
		String resultado = "";
		
		if (age <= 10)
		{
			if (r == 4) {
				resultado = "Acertou!!!";
			} else {
				resultado = "Errou!!!";
			}
		}
		
		if (age > 10)
		{
			if (r == 16) {
				resultado = "Acertou!!!";
			} else {
				resultado = "Errou!!!";
			}
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(resultado);
		builder.setCancelable(true);

		AlertDialog alert = builder.create();
		alert.show();
	}

}
