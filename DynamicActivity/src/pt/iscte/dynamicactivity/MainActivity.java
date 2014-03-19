package pt.iscte.dynamicactivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Um exemplo para mostrar a possibilidade de criar layouts din‰micos diretamente a partir do Java
 * @author carlosserrao
 *
 */
public class MainActivity extends Activity {
	
	LinearLayout llayout;
	
	public final int NUMBER_ROWS = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		llayout = (LinearLayout) findViewById(R.id.mainLayout);
		
		for(int i=0; i<NUMBER_ROWS; i++) {
			LinearLayout clayout = new LinearLayout(this);
			
			ImageView iv = new ImageView(this);
			iv.setImageResource(R.drawable.ic_launcher);

			TextView tv = new TextView(this);
			tv.setText("This is a text");

			clayout.addView(iv);
			clayout.addView(tv);
			
			llayout.addView(clayout);
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
