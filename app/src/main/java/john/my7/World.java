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
    public static void startTime(){
        Thread ticker = new Thread(new Runnable() {
            @Override
            public void run() {
                Time zeroTime = new Time(0,0);
                while (true) {
                    try {
                        //NOW 1min in app = 1 sec in real
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i<21;i++) {
                        CURRENT_TIME.tick();
                        if (CURRENT_TIME.compareTo(zeroTime) == 0){
                            CURRENT_DAY = (CURRENT_DAY+1)%7;
                            mainActivity.refreshTime();
                            System.out.println("Change day to "+ CURRENT_DAY);
                        }
                        System.out.println("current " + CURRENT_TEMPERATURE.toString() + " goal " + mainActivity.schedule.getCurrentGoal());
                        if (CURRENT_TEMPERATURE.compareTo(mainActivity.schedule.getCurrentGoal()) > 0){
                            if (i%5 == 0)
                                System.out.println("decrement");
                                CURRENT_TEMPERATURE.decremenTenth();
                        }
                        else if (CURRENT_TEMPERATURE.compareTo(mainActivity.schedule.getCurrentGoal()) < 0){
                            if (i%10 == 0)
                                System.out.println("increment");
                            CURRENT_TEMPERATURE.incremenTenth();
                        }
                    }
                    mainActivity.refreshTime();
                }
            }
        });
        ticker.start();
    }
    //temporary//
    public static ImageButton editImageButton;
    public static ImageButton deleteImageButton;
}
