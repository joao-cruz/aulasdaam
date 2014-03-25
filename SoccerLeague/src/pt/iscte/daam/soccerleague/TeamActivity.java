package pt.iscte.daam.soccerleague;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TeamActivity extends Activity {
	
	EditText teamname, city, foundation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team);
		
		teamname = (EditText) findViewById(R.id.txtTeamname);
		city = (EditText) findViewById(R.id.txtCity);
		foundation = (EditText) findViewById(R.id.txtFYear);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.team, menu);
		return true;
	}

	public void clickAddTeam(View v)
	{
		ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
		if(netInfo != null && netInfo.isConnected()){
			//ok the network is running, so please go ahead and do your stuff
			new AddTeam().execute("ADDTEAM", teamname.getText().toString(), city.getText().toString(), foundation.getText().toString());
		} else {
			Toast.makeText(getApplicationContext(), "Problems with the network connection.", Toast.LENGTH_LONG).show();
		}
		
	}
	
	public void clickCheckTeams(View v)
	{
		startActivity(new Intent(this, ListTeamsActivity.class));
	}
	
	private class AddTeam extends AsyncTask<String, Void, String> {
		ArrayList<NameValuePair> nvpair = new ArrayList<NameValuePair>();
		
		private ProgressDialog dialog = new ProgressDialog(TeamActivity.this);
		
		@Override
		protected void onPreExecute()
		{
			dialog.setMessage("Sending team to server...");
			dialog.show();
		}
		
		@Override
		protected String doInBackground(String... params)
		{
			String response = null;
			
			if(params[0].compareTo("ADDTEAM")==0) {
				nvpair.add(new BasicNameValuePair("op", params[0]));
				nvpair.add(new BasicNameValuePair("name", params[1]));
				nvpair.add(new BasicNameValuePair("city", params[2]));
				nvpair.add(new BasicNameValuePair("tfy", params[3]));
			}
			
			try {
				HttpClient httpc = new DefaultHttpClient();
				HttpPost hpost = new HttpPost("http://www.carlosserrao.net/test/soccerleague/team.php"); //The URL of the service to invoke!
				hpost.setEntity(new UrlEncodedFormEntity(nvpair));
				response = httpc.execute(hpost, new BasicResponseHandler());
				
				Log.d("debug_msg", "Response = " + response);
				
			} catch(Exception e) {
				Log.e("mylogtag", "An exception has occured in the connection - " + e.toString());
			}
			
			return response;
		}
		
		@Override
		protected void onPostExecute(String result)
		{
			dialog.dismiss();
			
			try {
				JSONObject json_response = new JSONObject(result);
				
				if(json_response.get("result").toString().compareTo("ERR")==0) {
					AlertDialog.Builder adig = new AlertDialog.Builder(TeamActivity.this);
					adig.setMessage(json_response.get("status").toString());
					adig.setCancelable(true);
					AlertDialog diag = adig.create();
					diag.show();
				} else {
					Toast.makeText(getApplicationContext(), "Team was added with success!", Toast.LENGTH_LONG).show();
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
