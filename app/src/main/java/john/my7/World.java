package john.my7;

import android.widget.ImageButton;

/**
 * Created by John on 20.05.2015.
 */
public class World {
    public static int MAX_INTERVALS = 10;
    // the int from 0 to 6 correspond the days of week
    public static int CURRENT_DAY = 0;
    //NOW 1min in app = 1 sec in real
    public static Time CURRENT_TIME = new Time(0,0);
    public static Temperature CURRENT_TEMPERATURE = new Temperature(23,5);
    public static TimeInterval selected_time_interval;
    public static boolean IS_EDIT_MODE = false;
    public static TimeTable.Day SELECTED_DAY;
    public static MainActivity mainActivity;
    public static Temperature vacation_goal_temp = new Temperature(22,5);
    public static void setSetting(){
        android.text.format.Time today = new android.text.format.Time(android.text.format.Time.getCurrentTimezone());
        today.setToNow();
        if (today.weekDay == 0)
            CURRENT_DAY = 6;
        else CURRENT_DAY = today.weekDay-1;
        CURRENT_TIME = new Time(today.hour,today.minute);
        System.out.println("CURRENT TIME: "+CURRENT_TIME.toString());
    }
    public static void startTime(){
        Thread ticker = new Thread(new Runnable() {
            @Override
            public void run() {
                Time zeroTime = new Time(0,0);
                int i = 9;
                while (true) {
                    try {
                        //NOW 1min in app = 1 sec in real
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CURRENT_TIME.tick();
                    i++;
                    if (i % 10 == 0) {
                        if (CURRENT_TIME.compareTo(zeroTime) == 0) {
                            CURRENT_DAY = (CURRENT_DAY + 1) % 7;
                        }
                        mainActivity.refreshTime();
                    }
                    if (CURRENT_TEMPERATURE.compareTo(mainActivity.schedule.getCurrentGoal()) > 0) {
                        if (i % 10 == 0)
                            CURRENT_TEMPERATURE.decremenTenth();
                    } else if (CURRENT_TEMPERATURE.compareTo(mainActivity.schedule.getCurrentGoal()) < 0) {
                        if (i % 20 == 0)
                            CURRENT_TEMPERATURE.incremenTenth();
                    }
                    i%=10001;
                }
            }
        });
        ticker.start();
    }
    //temporary//
    public static ImageButton editImageButton;
    public static ImageButton deleteImageButton;
}
