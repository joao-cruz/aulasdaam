package pt.iscte_iul.customlistviewsample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ElementAdapter adapter = new ElementAdapter(this, populateData());

        ListView lv = (ListView) findViewById(R.id.lvElements);

        lv.setAdapter(adapter);
    }

    private ArrayList<Element> populateData() {
        ArrayList<Element> elements = new ArrayList<Element>();

        elements.add(new Element("H", "Hydrogen"));
        elements.add(new Element("Li", "Lithium"));
        elements.add(new Element("Na", "Sodium"));
        elements.add(new Element("K", "Potassium"));
        elements.add(new Element("Rb", "Rubidium"));
        elements.add(new Element("Cs", "Caesium"));
        elements.add(new Element("Fr", "Francium"));
        elements.add(new Element("Be", "Beryllium"));
        elements.add(new Element("Mg", "Magnesium"));
        elements.add(new Element("Ca", "Calcium"));
        elements.add(new Element("Sr", "Strontium"));
        elements.add(new Element("Ba", "Barium"));
        elements.add(new Element("Ra", "Radium"));

        return elements;
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
