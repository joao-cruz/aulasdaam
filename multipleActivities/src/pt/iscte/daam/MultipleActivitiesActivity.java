package pt.iscte.daam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MultipleActivitiesActivity extends Activity {
	static int INPUT_REQUEST = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void clickButton(View view)
    {
    	switch(view.getId())
    	{
    	case R.id.button1:
    		//startActivity(new Intent(this, mySecondActivity.class));
    		startActivityForResult(new Intent(this, mySecondActivity.class), INPUT_REQUEST);
    		break;
    	}
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	super.onActivityResult(requestCode, resultCode, data); 
    	if (resultCode == Activity.RESULT_CANCELED) 
			Toast.makeText(this, "activity canceled", Toast.LENGTH_SHORT).show(); 
		else if (resultCode == Activity.RESULT_OK) 
		{
			Toast.makeText(this, "activity ok", Toast.LENGTH_SHORT).show(); 
			Toast.makeText(this, data.getStringExtra("mydata"), Toast.LENGTH_LONG).show();
		}
    }
}