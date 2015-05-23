package john.my7;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    public static final String heating = "heating";
    public static final String cooling = "cooling";
    Temperature currentTemp;
    Temperature goalTemp;
    TextView textFract;
    TextView textUnit;
    TextView textCurrTemp;
    TextView textCoolHeat;
    RelativeLayout blueLay;
    RelativeLayout.LayoutParams normalParams;
    RelativeLayout.LayoutParams extendParams;
    ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textFract = (TextView) findViewById(R.id.temperFraction);
        textUnit = (TextView) findViewById(R.id.temperUnit);

        textCurrTemp = (TextView) findViewById(R.id.textViewCurrTemp);
        textCurrTemp.setVisibility(View.INVISIBLE);

        textCoolHeat = (TextView) findViewById(R.id.textViewCoolHeat);
        textCoolHeat.setVisibility(View.INVISIBLE);

        blueLay = (RelativeLayout) findViewById(R.id.BluePartScreen);
        normalParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
        extendParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 660);
        blueLay.setLayoutParams(normalParams);
        mainListView = (ListView) findViewById(R.id.MainScreeAllDays);

        MainMenuAllTimeSet adapter2 = new MainMenuAllTimeSet(this, new TimeTable());
        mainListView.setAdapter(adapter2);

        currentTemp = new Temperature(21,3);
        goalTemp = new Temperature(21,3);
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

    public void onClickMinus(View v) {
        goalTemp.decremenTenth();
        textFract.setText(goalTemp.getFractionString());
        textUnit.setText(goalTemp.getUnitString());
        checkForChangeBlueSize();
    }

    public void onClickPlus(View v) {
        goalTemp.incremenTenth();
        textFract.setText(goalTemp.getFractionString());
        textUnit.setText(goalTemp.getUnitString());
        checkForChangeBlueSize();
    }

    private void checkForChangeBlueSize() {
        if (currentTemp.compareTo(goalTemp) == 0) {
            blueLay.setLayoutParams(normalParams);
            textCurrTemp.setVisibility(View.INVISIBLE);
            textCoolHeat.setVisibility(View.INVISIBLE);
        }
        else {
            blueLay.setLayoutParams(extendParams);
            textCurrTemp.setVisibility(View.VISIBLE);
            textCoolHeat.setVisibility(View.VISIBLE);
            if (currentTemp.compareTo(goalTemp) < 0) {
                textCoolHeat.setText(heating);
            }
            else{
                textCoolHeat.setText(cooling);
            }
        }
    }
}
