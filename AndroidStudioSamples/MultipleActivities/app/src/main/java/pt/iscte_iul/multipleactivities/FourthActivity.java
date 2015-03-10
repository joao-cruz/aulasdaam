package pt.iscte_iul.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class FourthActivity extends ActionBarActivity {

    protected int soma;
    protected TextView itemA, itemB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        itemA = (TextView) findViewById(R.id.itemATxt);
        itemB = (TextView) findViewById(R.id.itemBTxt);

        Intent calc = getIntent();
        String iA = calc.getStringExtra("ITEMA");
        String iB = calc.getStringExtra("ITEMB");
        int a = Integer.parseInt(iA);
        int b = Integer.parseInt(iB);

        itemA.setText(iA);
        itemB.setText(iB);

        soma = a + b;
    }

    public void clickShowMeButton(View v) {
        Intent result = new Intent();
        result.putExtra("RESULT", "" + soma);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fourth, menu);
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
