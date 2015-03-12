package pt.iscte_iul.informationstorage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    static int INPUT_REQUEST = 0;
    public static final String PREFS_NAME = "UserData";

    Button loginButton, regButton;
    EditText username_txt, password_txt;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.buttonLogin);
        regButton = (Button) findViewById(R.id.buttonRegistration);
        username_txt = (EditText) findViewById(R.id.username);
        password_txt = (EditText) findViewById(R.id.password);

        /* validate if we already have a user registered */
        SharedPreferences userData = getSharedPreferences(PREFS_NAME, 0);
        String username = userData.getString("username", "");
        String password = userData.getString("password", "");
        if(username == "") {
            loginButton.setEnabled(false);
            username_txt.setEnabled(false);
            password_txt.setEnabled(false);
        } else {
            loginButton.setEnabled(true);
            regButton.setEnabled(false);
        }
    }

    public void registrationDo(View v) {
        startActivityForResult(new Intent(this, Registrationtivity.class), INPUT_REQUEST);
    }

    public void loginDo(View v) {
        SharedPreferences userData = getSharedPreferences(PREFS_NAME, 0);
        String username = userData.getString("username", "");
        String password = userData.getString("password", "");

        if(username_txt.getText().toString().compareTo(username) == 0 && password_txt.getText().toString().compareTo(password) == 0 ) {
            startActivity(new Intent(this, UserDetailsActivity.class));
        } else {
            AlertDialog.Builder builder =   new AlertDialog.Builder(this);
            builder.setMessage("Login has failled!!!").
                    setCancelable(true).
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {
            loginButton.setEnabled(true);
            regButton.setEnabled(false);
            username_txt.setEnabled(true);
            password_txt.setEnabled(true);
            Toast.makeText(this, "Registration successful! Go ahead and login!", Toast.LENGTH_SHORT).show();
        } else if(resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "User has canceled the registration!", Toast.LENGTH_SHORT).show();
        }
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
