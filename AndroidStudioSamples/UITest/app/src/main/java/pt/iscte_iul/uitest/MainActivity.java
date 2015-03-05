package pt.iscte_iul.uitest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    protected static String APP_NAME = "UITEST";

    protected Button myButton01, myButton02, votarButton;
    protected RatingBar myRate;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton01 = (Button) findViewById(R.id.dialog1OpenButton);
        myButton02 = (Button) findViewById(R.id.dialog2OpenButton);
        votarButton = (Button) findViewById(R.id.votarButton);
        myRate = (RatingBar) findViewById(R.id.ratingBar);

        myButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(APP_NAME, "-> button dialog pressed!...");

                AlertDialog.Builder builder =   new AlertDialog.Builder(context);
                builder.setMessage("Tem mesmo a certeza?").
                        setCancelable(true).
                        setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).
                        setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        myButton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Log.i(APP_NAME, "-> button dialog 2 pressed!...");

                final CharSequence[] items = {"Red", "Green", "Blue"};

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Escolha uma cor...");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, items[i], Toast.LENGTH_SHORT).show();

                        if(i==0) myButton02.setBackgroundColor(0xffff3428);
                        if(i==1) myButton02.setBackgroundColor(0xFF00FF11);
                        if(i==2) myButton02.setBackgroundColor(0xFF0077FF);

                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });


        votarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog pdialog = new ProgressDialog(context);
                pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pdialog.setTitle("Voting pool");
                pdialog.setMessage("Casting your vote...");
                pdialog.setCancelable(true);
                pdialog.show();

                pdialog.setProgress(50);

                Toast.makeText(context, "Your vote was: " + myRate.getRating(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void clickButton(View v) {
        // do something here
        Log.i(APP_NAME, "-> button pressed!...");

        ProgressDialog progress = ProgressDialog.show(context, "Adding values...", "Please be patient...", true, true, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(context, "You have canceled the dialog", Toast.LENGTH_SHORT).show();
            }
        });
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
