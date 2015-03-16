package pt.iscte_iul.informationstorage;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class ContactsManagerActivity extends ActionBarActivity {

    protected EditText _nameTxt, _emailTxt, _phoneTxt;
    ListView contactsLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        _nameTxt = (EditText) findViewById(R.id.nameTxt);
        _emailTxt = (EditText) findViewById(R.id.emailTxt);
        _phoneTxt = (EditText) findViewById(R.id.phoneTxt);

        contactsLV = (ListView) findViewById(R.id.contactsListView);


        contactsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                String  itemValue = (String) contactsLV.getItemAtPosition(i);
                Contact c = db.getContact(itemValue);
                Toast.makeText(getApplicationContext(),
                        "Position :" + i + "  ListItem : " + itemValue + " Email: " + c.getEmail() + " Telephone: " + c.getTelephone(), Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public void addContact(View v) {
        DatabaseHandler db = new DatabaseHandler(this);
        db.addContact(new Contact(_nameTxt.getText().toString(), _emailTxt.getText().toString(), _phoneTxt.getText().toString()));

        List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getTelephone();
            Log.d("Name: ", log);
        }

        _nameTxt.setText("");
        _emailTxt.setText("");
        _phoneTxt.setText("");
    }

    public void listAllContactsButton(View v) {
        DatabaseHandler db = new DatabaseHandler(this);
        int count = db.getContactsCount();
        String [] values = new String[count];

        List<Contact> contacts = db.getAllContacts();
        int i=0;
        for (Contact cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Email: " + cn.getEmail() + " ,Phone: " + cn.getTelephone();
            Log.d("Name: ", log);
            values[i] = cn.getName();
            i++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values);

        contactsLV.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts_manager, menu);
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
