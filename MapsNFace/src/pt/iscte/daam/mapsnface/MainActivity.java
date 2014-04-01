package pt.iscte.daam.mapsnface;

import com.facebook.*;
import com.facebook.model.*;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// start Facebook Login
		  Session.openActiveSession(this, true, new Session.StatusCallback() {

		    // callback when session changes state
		    @Override
		    public void call(final Session session, SessionState state, Exception exception) {
		    	if (session.isOpened()) {
		    		// make request to the /me API
		    		Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
						
						@Override
						public void onCompleted(GraphUser user, Response response) {
							// TODO Auto-generated method stub
							if(session == Session.getActiveSession()) {
								if (user != null) {
									TextView wctxt = (TextView) findViewById(R.id.welcome);
									wctxt.setText("Hello " + user.getName() + "!");
				    			} else {
				    				TextView wctxt = (TextView) findViewById(R.id.welcome);
			    					wctxt.setText("No name?!? WTH????");
				    			}
							}
						}
					});
		    		request.executeAsync();
		    		
		    	}
		    }
		  });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}
	
	public void clickWhereAmI(View v){
		startActivity(new Intent(this, MapActivity.class));
	}

}
