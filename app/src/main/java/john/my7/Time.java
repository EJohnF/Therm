package john.my7;

/**
 * Created by John on 25.05.2015.
 */
public class Time implements Comparable<Time>{
    private int hour;
    private int minute;

    public Time(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    public Time(Time time) {
        hour = time.getHour();
        minute = time.getMinute();
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

    public void tick(){
        minute++;
        if (minute > 59){
            minute = 0;
            hour++;
            if (hour > 23){
                hour=0;
            }
        }
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

    @Override
    public int compareTo(Time another) {
        if (hour < another.hour){
            return -1;
        }
        if (hour > another.hour){
            return 1;
        }
        if (minute < another.minute) {
            return -1;
        }
        if (minute > another.minute){
            return 1;
        }
        return 0;
    }
}
