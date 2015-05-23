package john.my7;

/**
 * Created by John on 17.05.2015.
 */
public class Time {
    int startHour;
    int startMinute;
    int endHour;
    int endMinute;
    public Time(int startHour, int startMinute, int endHour, int endMinute){
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }
    @Override
    public String toString() {
        return intToString(startHour)+":" + intToString(startMinute)
                +" - " + intToString(endHour)+":"+intToString(endMinute);
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
