package pt.iscte.daam;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

public class DialogsTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        //ProgressDialog progress = ProgressDialog.show(this, "", "Loading, plase wait....", true);
        
        /*
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(DialogsTestActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        */
        
        
    }
}