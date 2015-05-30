package john.my7;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

/**
 * Created by John on 27.05.2015.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    Time time;
    public void setTime(Time time){
        this.time = time;
    }
    MainActivity activity;
    public void setActivity(MainActivity ac){
        activity = ac;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        int hour = time.getHour();
        int minute = time.getMinute();
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time.setHour(hourOfDay);
        time.setMinute(minute);
        if (activity != null){
            activity.editTimeIntervalLayout.requestFocus();
            activity.textViewEditStartTime.setText(World.selected_time_interval.getStart().toString());
            if (World.selected_time_interval.getStart().compareTo(World.selected_time_interval.getEnd()) > 0){
                World.selected_time_interval.setEnd(World.selected_time_interval.getStart());
            }
            activity.textViewEditEndTime.setText(World.selected_time_interval.getEnd().toString());
        }

    }
}
