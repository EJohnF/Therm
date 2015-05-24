package john.my7;

/**
 * Created by John on 25.05.2015.
 */
public class Time {
    private int hour;
    private int minute;

    public Time(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return intToString(hour)+":"+intToString(minute);
    }
    private String intToString(int i){
        if (i>=10){
            return Integer.toString(i);
        }
        else {
            return "0"+Integer.toString(i);
        }
    }
}
