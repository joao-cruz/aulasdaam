package pt.iscte_iul.informationstorage;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;


public class UserDetailsActivity extends ActionBarActivity {
    Bitmap bitmap;
    ImageView imageProfile;
    public static final String PREFS_NAME = "UserData";

    TextView usernametxt, emailtxt;
    EditText imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        imageProfile = (ImageView) findViewById(R.id.imageUserProfile);
        usernametxt = (TextView) findViewById(R.id.nametxt);
        emailtxt = (TextView) findViewById(R.id.emailtxt);
        imageURL = (EditText) findViewById(R.id.imageURL);

        SharedPreferences userData = getSharedPreferences(PREFS_NAME, 0);
        String username = userData.getString("username", "");
        String email = userData.getString("email", "");

        usernametxt.setText(username);
        emailtxt.setText(email);
    }

    public void fetchImage(View v) {
        new LoadImage().execute(imageURL.getText().toString());
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        ProgressDialog pDialog;

        String fileName = "tempImage.png";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(UserDetailsActivity.this);
            pDialog.setMessage("Loading Image ....");
            pDialog.show();
        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();

                FileOutputStream fileOutStream = openFileOutput(fileName, MODE_PRIVATE);
                fileOutStream.write(b);
                fileOutStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                imageProfile.setImageBitmap(image);
                pDialog.dismiss();
            }else{
                pDialog.dismiss();
                Toast.makeText(UserDetailsActivity.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_details, menu);
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
