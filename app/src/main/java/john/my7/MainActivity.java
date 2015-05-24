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
    RelativeLayout blueLay;
    RelativeLayout topEditLay;
    RelativeLayout.LayoutParams normalParams;
    RelativeLayout.LayoutParams extendParams;
    ListView mainListView;
    TimeTable schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textFract = (TextView) findViewById(R.id.temperFraction);
        textUnit = (TextView) findViewById(R.id.temperUnit);

        textCurrTemp = (TextView) findViewById(R.id.textViewCurrTemp);
        textCurrTemp.setVisibility(View.INVISIBLE);

        blueLay = (RelativeLayout) findViewById(R.id.BluePartScreen);
        blueLay.setVisibility(View.VISIBLE);
        topEditLay = (RelativeLayout) findViewById(R.id.TopEditLayer);
        topEditLay.setVisibility(View.INVISIBLE);
        normalParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
        extendParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 660);
        blueLay.setLayoutParams(normalParams);
        mainListView = (ListView) findViewById(R.id.MainScreeAllDays);

        schedule = new TimeTable();
        schedule.setDay(new Temperature(24,5));
        schedule.setNight(new Temperature(20,0));

        MainMenuAllTimeSet adapter2 = new MainMenuAllTimeSet(this, schedule);
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
        }
        else {
         //   blueLay.setLayoutParams(extendParams);
            textCurrTemp.setVisibility(View.VISIBLE);
        }
    }
    public void onClickCalendar(View v){
        blueLay.setVisibility(View.INVISIBLE);
        topEditLay.setVisibility(View.VISIBLE);
    }
    public void onClickOk(View v){
        blueLay.setVisibility(View.VISIBLE);
        topEditLay.setVisibility(View.INVISIBLE);
    }
    public void onClickMinusNightTemp(View v){

    }
}
