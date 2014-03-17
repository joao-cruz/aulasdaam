package pt.iscte.mathmatrix;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class SplashScreen extends Activity {
	// TEMPO A MOSTRAR O SPLASH
	private static int SPLASH_TIME_OUT = 5000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
				startActivity(i);
				
				// termina a activity
				finish();				
			}
		}, SPLASH_TIME_OUT);
	}
}
