package pt.iscte.daam.helloworld;

import org.apache.cordova.*;
import android.os.Bundle;

public class HelloWorldAppActivity extends DroidGap {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        super.loadUrl("file:///android_asset/www/index.html");
    }
}