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
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListTeamsActivity extends Activity {
	ListView teamsListView;
	ArrayList<Teams> teamsList;
	TeamsBaseAdapter teamsAdapter;
	
	int currPos = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_teams);
		
		teamsList = new ArrayList<Teams>();
		
		getTeamslist();
		
		teamsListView = (ListView) findViewById(R.id.listViewTeams);
		teamsAdapter = new TeamsBaseAdapter(this, teamsList);
		
		teamsListView.setAdapter(teamsAdapter);
		
		teamsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				Context ctx = getApplicationContext();
				Intent i = new Intent(ctx, TeamDetailActivity.class); 
				
				currPos = position;
				
				Teams n = (Teams) teamsAdapter.getItem(position);

				i.putExtra("team_id", ""+n.getTeamId());
				i.putExtra("team_name", ""+n.getTeamName());
				i.putExtra("team_city", ""+n.getTeamcity());
				i.putExtra("team_fy", ""+n.getTeamFY());

				startActivity(i);

			}
		});
		
	}
	
	private void getTeamslist() {	
		ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
		if(netInfo != null && netInfo.isConnected()){
			//ok the network is running, so please go ahead and do your stuff
			new ListTeams().execute();
		} else {
			Toast.makeText(getApplicationContext(), "Problems with the network connection.", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_teams, menu);
		return true;
	}
	
	private class ListTeams extends AsyncTask<String, Void, String> {
		private ProgressDialog dialog = new ProgressDialog(ListTeamsActivity.this);
		ArrayList<NameValuePair> nvpair = new ArrayList<NameValuePair>();
		
		@Override
		protected void onPreExecute()
		{
			dialog.setMessage("Getting list of teams from server...");
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
					
					//teams = new JSONArray(json_response.getJSONArray("teams"));
					teams = json_response.getJSONArray("teams");
					
					int size = teams.length();
					
					Log.d("debug_msg", "Size = " + size);
					Log.d("debug_msg", "1st = " + teams.getJSONObject(0).getString("tname"));
					Log.d("debug_msg", "2nd = " + teams.getJSONObject(1).getString("tname"));
					
					for(int n = 0; n < size; n++) {
						String teamId = teams.getJSONObject(n).getString("id");
						String teamName = teams.getJSONObject(n).getString("tname");
						String teamCity = teams.getJSONObject(n).getString("tcity");
						String teamFY = teams.getJSONObject(n).getString("tfy");
						Log.d("debug_msg", "Team = " + teams.getJSONObject(n).getString("tname"));
						
						teamsList.add(new Teams(teamId, teamName, teamCity, teamFY));
					}
					
					teamsAdapter.notifyDataSetChanged();
					
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}