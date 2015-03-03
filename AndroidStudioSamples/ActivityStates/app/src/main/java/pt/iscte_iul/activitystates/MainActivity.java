package pt.iscte_iul.activitystates;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    public static String APP = "ACTIVITY_STATES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(APP, "-> onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(APP, "-> onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(APP, "-> onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(APP, "-> onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(APP, "-> onPause()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(APP, "-> onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(APP, "-> onDestroy()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
