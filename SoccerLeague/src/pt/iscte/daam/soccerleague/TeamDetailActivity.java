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
import android.widget.TextView;
import android.widget.Toast;

public class TeamDetailActivity extends Activity {
	
	TextView teamName, teamCity, teamFY;
	String team_id, team_name, team_city, team_fy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_detail);
		
		teamName = (TextView) findViewById(R.id.teamNameTextView);
		teamCity = (TextView) findViewById(R.id.teamCityTextView);
		teamFY = (TextView) findViewById(R.id.teamFYTextView);
		
		Intent i = getIntent();
		team_id = i.getStringExtra("team_id");
		team_name = i.getStringExtra("team_name");
		team_city = i.getStringExtra("team_city");
		team_fy = i.getStringExtra("team_fy");
		
		teamName.setText(team_name);
		teamCity.setText(team_city);
		teamFY.setText(team_fy);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.team_detail, menu);
		return true;
	}
	
	public void clickRemoveButton(View v)
	{
		ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
		if(netInfo != null && netInfo.isConnected()){
			//ok the network is running, so please go ahead and do your stuff
			new RemoveTeam().execute(team_id);
		} else {
			Toast.makeText(getApplicationContext(), "Problems with the network connection.", Toast.LENGTH_LONG).show();
		}
	}
	
	private class RemoveTeam extends AsyncTask<String, Void, String> {
		private ProgressDialog dialog = new ProgressDialog(TeamDetailActivity.this);
		ArrayList<NameValuePair> nvpair = new ArrayList<NameValuePair>();
		
		@Override
		protected void onPreExecute()
		{
			dialog.setMessage("Removing team...");
			dialog.show();
		}
		
		@Override
		protected String doInBackground(String... params)
		{
			String response = null;
			
			nvpair.add(new BasicNameValuePair("op", "REMOVETEAM"));
			nvpair.add(new BasicNameValuePair("id", params[0]));
			
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
					AlertDialog.Builder adig = new AlertDialog.Builder(TeamDetailActivity.this);
					adig.setMessage(json_response.get("status").toString());
					adig.setCancelable(true);
					AlertDialog diag = adig.create();
					diag.show();
				} else {
					AlertDialog.Builder adig = new AlertDialog.Builder(TeamDetailActivity.this);
					adig.setMessage("Team removed!!!");
					adig.setCancelable(true);
					AlertDialog diag = adig.create();
					diag.show();				
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
