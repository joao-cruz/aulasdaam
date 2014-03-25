package pt.iscte.daam.soccerleague;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
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
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListTeamsActivity extends Activity {
	ListView lv;
	String  arrTeams[]={};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_teams);
		
		ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
		if(netInfo != null && netInfo.isConnected()){
			//ok the network is running, so please go ahead and do your stuff
			new ListTeams().execute();
		} else {
			Toast.makeText(getApplicationContext(), "Problems with the network connection.", Toast.LENGTH_LONG).show();
		}
		
		
		lv = (ListView) findViewById(R.id.teamsLView);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_teams, menu);
		return true;
	}
	
	private class ListTeams extends AsyncTask<String, Void, String> {
		ArrayList<NameValuePair> nvpair = new ArrayList<NameValuePair>();
		
		private ProgressDialog dialog = new ProgressDialog(ListTeamsActivity.this);
		
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
			
			nvpair.add(new BasicNameValuePair("op", "LISTTEAMS"));
			
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
				JSONArray teams = null;
				JSONObject json_response = new JSONObject(result);
				
				if(json_response.get("result").toString().compareTo("ERR")==0) {
					AlertDialog.Builder adig = new AlertDialog.Builder(ListTeamsActivity.this);
					adig.setMessage(json_response.get("status").toString());
					adig.setCancelable(true);
					AlertDialog diag = adig.create();
					diag.show();
				} else {
					// process the answer
					Log.d("debug_msg", "Teams = " + json_response.get("teams").toString());
					
					teams = new JSONArray(json_response.getJSONArray("teams"));
					
					for(int n=0; n< teams.length(); n++) {
						JSONObject t = teams.getJSONObject(n);
						arrTeams[n] = t.getString("tname");
						Log.d("debug_msg", "Team = " + t.getString("tname"));
					}
					
					ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.activity_list_item, arrTeams);
					lv.setAdapter(adapter);
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
