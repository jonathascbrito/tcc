package gestrans.jonathasbrito.gestrans20182.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;

import gestrans.jonathasbrito.gestrans20182.R;

public class CorridasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corridas);

        /*Add in Oncreate() funtion after setContentView()*/
        Switch simpleSwitch = (Switch) findViewById(R.id.switch1); // initiate Switch

        simpleSwitch.setTextOn("On"); // displayed text of the Switch whenever it is in checked or on state
        simpleSwitch.setTextOff("Off"); // displayed text of the Switch whenever it is in unchecked i.e. off state
    }
}
