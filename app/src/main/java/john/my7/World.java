package john.my7;

import android.widget.ImageButton;

/**
 * Created by John on 20.05.2015.
 */
public class World {
    public static int MAX_INTERVALS = 10;
    // the int from 0 to 6 correspond the days of week
    public static int CURRENT_DAY = 2;
    //NOW 1min in app = 1 sec in real
    public static Time CURRENT_TIME = new Time(0,0);
    public static Temperature CURRENT_TEMPERATURE = new Temperature(23,5);
    public static TimeInterval selected_time_interval;
    public static boolean IS_EDIT_MODE = false;
    public static TimeTable.Day SELECTED_DAY;
    public static MainActivity mainActivity;
    public static void startTime(){
        Thread ticker = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //NOW 1min in app = 1 sec in real
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CURRENT_TIME.tick();
                    mainActivity.refreshTime();
                }
            }
        });
    }
    //temporary//
    public static ImageButton editImageButton;
    public static ImageButton deleteImageButton;
}
