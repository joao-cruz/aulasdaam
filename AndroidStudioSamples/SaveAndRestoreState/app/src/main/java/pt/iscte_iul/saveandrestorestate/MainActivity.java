package pt.iscte_iul.saveandrestorestate;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    protected TextView countLettersTV;
    protected EditText nameET;
    protected Button askBtn;

    protected String textDisplay;

    protected static String APP_NAME = "SaveAndRestoreState";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countLettersTV = (TextView) findViewById(R.id.textViewCountLetters);
        nameET = (EditText) findViewById(R.id.editTextName);
        askBtn = (Button) findViewById(R.id.buttonAsk);

        askBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textDisplay = "# letters ->" + nameET.getText().toString().length();
                countLettersTV.setText(textDisplay);
            }
        });

        Log.i(APP_NAME, "-> onCreate");

    }

    @Override
    protected void onSaveInstanceState(Bundle toBeSaved) {
        toBeSaved.putString("NUMBER_LETTERS", textDisplay);

        Log.i(APP_NAME, "-> onSaveInstanceState");
        super.onSaveInstanceState(toBeSaved);
    }

    @Override
    protected void onRestoreInstanceState(Bundle toBeLoaded) {
        super.onRestoreInstanceState(toBeLoaded);

        Log.i(APP_NAME, "-> onRestoreInstanceState");
        textDisplay = toBeLoaded.getString("NUMBER_LETTERS");
        countLettersTV.setText(textDisplay);
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
