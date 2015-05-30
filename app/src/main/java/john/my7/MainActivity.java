package john.my7;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.eftimoff.androidplayer.Player;
import com.eftimoff.androidplayer.actions.property.PropertyAction;

/*
TODO: ����� ���������� ������ ��������� ������� ���������. ������ �� ������������ �� ������ onClickGeneral() ��� ������  ������ ����
 */

public class MainActivity extends ActionBarActivity {
    RelativeLayout editTimeIntervalLayout;
    TextView textViewEditStartTime;
    TextView textViewEditEndTime;

    private Temperature currentTemp;
    private Temperature goalTemp;
    private TextView fractionTextView;
    private TextView unitTextView;
    private TextView currentTempTextView;
    private TextView pointTextView;
    private TextView zeroTextView;
    private RelativeLayout blueLayout;
    private RelativeLayout editLayout;
    ListView mainListView;

    private TimeTable schedule;
    private Switch switcher;

    private TextView editNightTempTextView;
    private TextView editDayTempTextView;

    private TimeInterval helpful;

    private ImageButton bigPlus;
    private ImageButton bigMinus;
    private ImageButton calendarButton;

    boolean wasPaused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeFields();
        setInitialSettings();

        World.startTime();
        onClickGeneral();
        wasPaused = true;
        createAnimation();
        World.mainActivity = this;
    }

    private void setInitialSettings() {
        blueLayout.setVisibility(View.VISIBLE);
        editLayout.setVisibility(View.INVISIBLE);
        editTimeIntervalLayout.setVisibility(View.INVISIBLE);

        schedule = new TimeTable(this);
        schedule.setDayTemp(new Temperature(24, 5));
        schedule.setNightTemp(new Temperature(20, 0));

        MainMenuAllTimeSet adapter2 = new MainMenuAllTimeSet(this, schedule);
        mainListView.setAdapter(adapter2);
        currentTemp = new Temperature(21, 3);
        goalTemp = new Temperature(21, 3);

        editNightTempTextView.setText(schedule.getNightTemp().toString());
        editDayTempTextView.setText(schedule.getDayTemp().toString());
    }

    private void initializeFields() {
        fractionTextView = (TextView) findViewById(R.id.temperFraction);
        unitTextView = (TextView) findViewById(R.id.temperUnit);
        pointTextView = (TextView) findViewById(R.id.textPoint);
        zeroTextView = (TextView) findViewById(R.id.textO);
        currentTempTextView = (TextView) findViewById(R.id.textViewCurrTemp);
        blueLayout = (RelativeLayout) findViewById(R.id.BluePartScreen);
        editLayout = (RelativeLayout) findViewById(R.id.TopEditLayer);
        editTimeIntervalLayout = (RelativeLayout) findViewById(R.id.editTimeIntervalLayout);
        currentTempTextView.setVisibility(View.INVISIBLE);
        textViewEditEndTime = (TextView) findViewById(R.id.textViewEditEndTime);
        textViewEditStartTime = (TextView) findViewById(R.id.textViewEditStartTime);
        mainListView = (ListView) findViewById(R.id.MainScreeAllDays);
        editNightTempTextView = (TextView) findViewById(R.id.textViewEditNightTemp);
        World.editImageButton = (ImageButton) findViewById(R.id.imageButtonEdit);
        World.deleteImageButton = (ImageButton) findViewById(R.id.imageButtonDelete);
        editDayTempTextView = (TextView) findViewById(R.id.textViewEditDayTemp);
        switcher = (Switch) findViewById(R.id.switchDayNight);
        calendarButton = (ImageButton) findViewById(R.id.calendarButton);
        bigPlus = (ImageButton) findViewById(R.id.imagePlusView);
        bigMinus = (ImageButton) findViewById(R.id.imageMinusView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        createAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasPaused = true;
        schedule.saveData(this);
    }

    private void createAnimation() {
        if (wasPaused) {
            wasPaused = false;

            final PropertyAction unit = PropertyAction.newPropertyAction(unitTextView).
                    scaleX(0).scaleY(0).duration(50).
                    interpolator(new AccelerateDecelerateInterpolator()).build();
            final PropertyAction point = PropertyAction.newPropertyAction(pointTextView).
                    scaleX(0).scaleY(0).duration(10).
                    interpolator(new AccelerateDecelerateInterpolator()).build();
            final PropertyAction zero = PropertyAction.newPropertyAction(zeroTextView).
                    scaleX(0).scaleY(0).duration(20).
                    interpolator(new AccelerateDecelerateInterpolator()).build();
            final PropertyAction fraction = PropertyAction.newPropertyAction(fractionTextView).
                    scaleX(0).scaleY(0).duration(40).
                    interpolator(new AccelerateDecelerateInterpolator()).build();
            final PropertyAction minus = PropertyAction.newPropertyAction(bigMinus).
                    scaleX(0).scaleY(0).duration(50).
                    interpolator(new AccelerateDecelerateInterpolator()).build();
            final PropertyAction plus = PropertyAction.newPropertyAction(bigPlus).
                    scaleX(0).scaleY(0).duration(50).
                    interpolator(new AccelerateDecelerateInterpolator()).build();
            final PropertyAction current = PropertyAction.newPropertyAction(currentTempTextView).
                    scaleX(0).scaleY(0).duration(35).
                    interpolator(new AccelerateDecelerateInterpolator()).build();
            final PropertyAction swit = PropertyAction.newPropertyAction(switcher).
                    scaleX(0).scaleY(0).duration(50).
                    interpolator(new AccelerateDecelerateInterpolator()).build();
            final PropertyAction calendar = PropertyAction.newPropertyAction(calendarButton).
                    scaleX(0).scaleY(0).duration(400).
                    interpolator(new AccelerateDecelerateInterpolator()).build();
            final PropertyAction headerAction = PropertyAction.newPropertyAction(blueLayout).
                    interpolator(new DecelerateInterpolator()).
                    translationY(-200).duration(100).alpha(0.4f).build();
            final PropertyAction bottomAction = PropertyAction.newPropertyAction(mainListView).
                    translationY(500).duration(200).alpha(0f).build();

            Player.init().
                    animate(headerAction).
                    then().animate(minus).
                    then().animate(unit).
                    then().animate(point).
                    then().animate(fraction).
                    then().animate(zero).
                    then().animate(plus).
                    then().animate(current).
                    then().animate(swit).
                    then().animate(bottomAction).
                    then().animate(calendar).
                    play();
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

    public void onClickMinus(View v) {
        goalTemp.decremenTenth();
        fractionTextView.setText(goalTemp.getFractionString());
        unitTextView.setText(goalTemp.getUnitString());
        checkForChangeBlueSize();
    }

    public void onClickPlus(View v) {
        goalTemp.incremenTenth();
        fractionTextView.setText(goalTemp.getFractionString());
        unitTextView.setText(goalTemp.getUnitString());
        checkForChangeBlueSize();
    }

    private void checkForChangeBlueSize() {
        if (currentTemp.compareTo(goalTemp) == 0) {
            //        blueLayout.setLayoutParams(normalParams);
            currentTempTextView.setVisibility(View.INVISIBLE);
        } else {
            //   blueLayout.setLayoutParams(extendParams);
            currentTempTextView.setVisibility(View.VISIBLE);
        }
    }

    public void onClickCalendar(View v) {
        blueLayout.setVisibility(View.INVISIBLE);
        editLayout.setVisibility(View.VISIBLE);
        World.IS_EDIT_MODE = true;
        ((MainMenuAllTimeSet) mainListView.getAdapter()).enterToEditMode();
        ((BaseAdapter) mainListView.getAdapter()).notifyDataSetChanged();
        schedule.saveData(this, "tempFotUndo");
    }

    public void onClickOk(View v) {

        final Context context = this;
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        ((MainMenuAllTimeSet) mainListView.getAdapter()).outFromEditMode();
                        ((BaseAdapter) mainListView.getAdapter()).notifyDataSetChanged();
                        onClickGeneral();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        schedule.changeFromFile(context, "tempForUndo");
                        ((MainMenuAllTimeSet) mainListView.getAdapter()).outFromEditMode();
                        ((BaseAdapter) mainListView.getAdapter()).notifyDataSetChanged();
                        onClickGeneral();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Save Changes?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
        blueLayout.setVisibility(View.VISIBLE);
        editLayout.setVisibility(View.INVISIBLE);
        World.IS_EDIT_MODE = false;

    }

    public void onClickMinusNightTemp(View v) {
        schedule.getNightTemp().decremenTenth();
        editNightTempTextView.setText(schedule.getNightTemp().toString());
        onClickGeneral();
    }

    public void onClickMinusDayTemp(View v) {
        schedule.getDayTemp().decremenTenth();
        editDayTempTextView.setText(schedule.getDayTemp().toString());
        onClickGeneral();
    }

    public void onClickPlusDayTemp(View v) {
        schedule.getDayTemp().incremenTenth();
        editDayTempTextView.setText(schedule.getDayTemp().toString());
        onClickGeneral();
    }

    public void onClickPlusNightTemp(View v) {
        schedule.getNightTemp().incremenTenth();
        editNightTempTextView.setText(schedule.getNightTemp().toString());
        onClickGeneral();
    }

    private void onClickGeneral() {
        World.deleteImageButton.setVisibility(View.INVISIBLE);
        World.editImageButton.setVisibility(View.INVISIBLE);
        goalTemp = schedule.getCurrentGoal();
        fractionTextView.setText(goalTemp.getFractionString());
        unitTextView.setText(goalTemp.getUnitString());
        checkForChangeBlueSize();
    }

    public void onClickEditTimeInterval(View v) {
        editTimeIntervalLayout.setVisibility(View.VISIBLE);
        editTimeIntervalLayout.setEnabled(true);
        textViewEditStartTime.setText(World.selected_time_interval.getStart().toString());
        textViewEditEndTime.setText(World.selected_time_interval.getEnd().toString());
        helpful = World.selected_time_interval.copy();
        mainListView.setEnabled(false);
        editLayout.setEnabled(false);
        onClickGeneral();
    }

    public void onClickEditTimeIntervalSave(View v) {
        editTimeIntervalLayout.setVisibility(View.INVISIBLE);
        editTimeIntervalLayout.setEnabled(false);
        mainListView.setEnabled(true);
        editLayout.setEnabled(true);
        onClickGeneral();
        System.out.println(helpful.toString());
        if (helpful.isEmpty()) { // это значит что была нажата "добавить интервал"
            World.SELECTED_DAY.addInterval(World.selected_time_interval);
        }
        else {
            World.SELECTED_DAY.wasEditInterval(World.selected_time_interval);
        }
        ((BaseAdapter) mainListView.getAdapter()).notifyDataSetChanged();
    }

    public void onClickEditTimeIntervalCancel(View v) {
        mainListView.setEnabled(true);
        editLayout.setEnabled(true);
        onClickGeneral();
        World.selected_time_interval.setFrom(helpful);
        ValueAnimator animator = ObjectAnimator.ofFloat(editTimeIntervalLayout, "alpha", 1f, 0f);
        animator.setDuration(1000);
        animator.start();
        editTimeIntervalLayout.setVisibility(View.INVISIBLE);
        editTimeIntervalLayout.setEnabled(false);
    }

    public void onClickEditTimeIntervalStart(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        ((TimePickerFragment)newFragment).setTime(World.selected_time_interval.getStart());
        ((TimePickerFragment)newFragment).setActivity(this);
        newFragment.setCancelable(true);
        newFragment.show(getSupportFragmentManager(), "Set start time");

    }

    public void onClickEditTimeIntervalEnd(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        ((TimePickerFragment)newFragment).setTime(World.selected_time_interval.getEnd());
        ((TimePickerFragment)newFragment).setActivity(this);
        newFragment.setCancelable(true);
        newFragment.show(getSupportFragmentManager(), "Set end time");
        textViewEditStartTime.setText(World.selected_time_interval.getStart().toString());
        textViewEditEndTime.setText(World.selected_time_interval.getEnd().toString());
    }

    public void refreshTime(){

    }

    public void onClickDeleteTimeInterval(View v){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        World.SELECTED_DAY.deleteTimeInterval(World.selected_time_interval);
                        ((BaseAdapter) World.mainActivity.mainListView.getAdapter()).notifyDataSetChanged();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure want to delete this interval?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
        onClickGeneral();
    }

    public void onClickPlane(View view) {

    }
}
