package pt.iscte_iul.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


    public class MainActivity extends ActionBarActivity {

        protected Button secondActivityButton;
        protected Button thirdActivityButton;
        protected Button calculatorButton;

        protected EditText txtName;

        protected EditText itemA, itemB, itemCalc;

        protected static String APP_NAME = "MultipleActivities";

        static int INPUT_REQUEST = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            secondActivityButton = (Button) findViewById(R.id.buttonSecondActivity);
            thirdActivityButton = (Button) findViewById(R.id.buttonThirdActivity);
            calculatorButton = (Button) findViewById(R.id.calculateButton);

            txtName = (EditText) findViewById(R.id.txtName);
            itemA = (EditText) findViewById(R.id.itemATxt);
            itemB = (EditText) findViewById(R.id.itemBTxt);
            itemCalc = (EditText) findViewById(R.id.calcTxt);


            secondActivityButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(view.getContext(), SecondActivity.class));
                }
            });

            thirdActivityButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent third = new Intent(view.getContext(), ThirdActivity.class);
                    String tobesent = txtName.getText().toString();

                    Log.i(APP_NAME, "To be sent = " + tobesent);

                    third.putExtra("NAME", tobesent);
                    startActivity(third);
                }
            });
        }

        public void clickCalculateButton(View v)
        {
            Intent calc = new Intent(this, FourthActivity.class);
            calc.putExtra("ITEMA", itemA.getText().toString());
            calc.putExtra("ITEMB", itemB.getText().toString());
            startActivityForResult(calc, INPUT_REQUEST);
        }

        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if(resultCode == Activity.RESULT_OK) {
                itemCalc.setText(data.getStringExtra("RESULT"));
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
