package pt.iscte.daam.webviewjssample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		WebView jswv;



		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			jswv = (WebView) rootView.findViewById(R.id.jsWebview);
			
			WebSettings ws = jswv.getSettings();
			ws.setJavaScriptEnabled(true);
			
			/*
			String webContentData = "<html>"+
									"<head></head>"+
									"<body bgcolor=\"#00bbaa\">"+
									"<script>"+
									"function validate() {"+
									"	document.write('xss');"+
									"}"+
									"</script>"+
									"<form>"+
									"<input type=\"button\" value=\"click me\" onClick=\"javascript:validate()\">"+
									"</form>"+
									"</body>"+
									"</html>";
			*/
			//jswv.loadData("<html><body><h1>teste</h1><a a href='#' onclick='alert('test')'>teste</a></body></html>", "text/html", "UTF-8");
			/*
			jswv.loadData(webContentData, "text/html", "UTF-8");
			*/
			
			//jswv.loadUrl("http://www.google.com");

			// LOCAL
			String html = readHtml("sample.html", rootView.getContext());
			
			Log.i("BUM", html);
			
			jswv.loadDataWithBaseURL( "file:///android_asset/", html, "text/html", "utf-8", null ); 
			
			
			// REMOTO
			//jswv.loadUrl("http://www.carlosserrao.net/test/sample.html");
			
			return rootView;
		}
		
		private String readHtml(String asset, Context ctx) {
			StringBuilder buf = new StringBuilder();
			
			try {
			    InputStream html = ctx.getAssets().open(asset);
			    BufferedReader in=
			        new BufferedReader(new InputStreamReader(html));
			    String str;

			    while ((str=in.readLine()) != null) {
			      buf.append(str);
			    }

			    in.close();
			    
			    return buf.toString();
			} catch (Exception e) {
				Log.w("BUM:", e.getMessage());
				return null;
			}
		}
	}

}
