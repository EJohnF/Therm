package john.my7;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class vacation_mode extends ActionBarActivity {

    TextView textUnit;
    TextView textFraction;
    TextView textCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_mode);
        textCurrent = (TextView) findViewById(R.id.textViewCurrTemp);
        textFraction = (TextView) findViewById(R.id.temperFraction);
        textUnit = (TextView) findViewById(R.id.temperUnit);
        World.vacationMode = this;
        World.currentGoalTemp.setTemp(World.vacation_goal_temp);
        refreshTemps();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vacation_mode, menu);
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

    public void onClickPlus(View view) {
        World.vacation_goal_temp.incremenTenth();
        refreshTemps();
    }

    public void onClickMinus(View view) {
        World.vacation_goal_temp.decremenTenth();
        refreshTemps();
    }

    public void refreshTemps() {
        textCurrent.setText("current " + World.CURRENT_TEMPERATURE.toString());
        textUnit.setText(World.vacation_goal_temp.getUnitString());
        textFraction.setText(World.vacation_goal_temp.getFractionString());
        World.currentGoalTemp.setTemp(World.vacation_goal_temp);
        setVisibilityCurrent();

    }

    public void onClickBack(View view) {
        World.isVacation = false;
        finish();
    }

    public void refreshTime() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textCurrent.setText("current " + World.CURRENT_TEMPERATURE.toString());
                setVisibilityCurrent();
            }
        });
    }

    private void setVisibilityCurrent() {
        if (World.CURRENT_TEMPERATURE.compareTo(World.vacation_goal_temp) == 0) {
            textCurrent.setVisibility(View.INVISIBLE);
        } else {
            textCurrent.setVisibility(View.VISIBLE);
        }
    }
}
