package pt.iscte.daam.webviewsample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;

public class MainActivity extends Activity {
	
	WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		wv = (WebView) findViewById(R.id.display_webView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clickLoadLocalContent(View v) 
	{
		wv.loadData("<html><body><h1>WebView</h1><p>How are you today?!?</p></body></html>", "text/html", "UTF-8");
	}
	
	public void clickLoadRemoteContent(View v)
	{
		wv.loadUrl("http://www.carlosserrao.net");
	}

}
