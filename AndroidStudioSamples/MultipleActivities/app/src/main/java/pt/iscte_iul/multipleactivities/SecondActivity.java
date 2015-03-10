package pt.iscte_iul.multipleactivities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.net.URLConnection;


public class SecondActivity extends ActionBarActivity {

    EditText telNumber, URLtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        telNumber = (EditText) findViewById(R.id.telephoneNumberTxt);
        URLtxt = (EditText) findViewById(R.id.URLtxt);
    }

    public void clickCallTelephone(View v) {
        Intent tel = new Intent(Intent.ACTION_DIAL);
        tel.setData(Uri.parse("tel:"+telNumber.getText().toString()));
        startActivity(tel);
    }

    public void clickOpenURL(View v) {
        Intent uri = new Intent(Intent.ACTION_VIEW);
        uri.setData(Uri.parse(URLtxt.getText().toString()));
        startActivity(uri);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
