package john.my7;

/**
 * Created by John on 17.05.2015.
 */
public class TimeInterval {
    Time start;
    Time end;
    public TimeInterval(int startHour, int startMinute, int endHour, int endMinute){
        this.start = new Time(startHour,startMinute);
        this.end = new Time(endHour,endMinute);
    }
    public TimeInterval(Time start, Time end){
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return start.toString()+" - " + end.toString();
    }

    public boolean isInInterval(Time currentTime) {
        if (currentTime.compareTo(end) <= 0 && currentTime.compareTo(start) >= 0){
            return true;
        }
        else return false;
    }

}
