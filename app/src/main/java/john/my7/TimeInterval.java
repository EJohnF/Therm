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
    public TimeInterval(){
        this.start = new Time(0,0);
        this.end = new Time(0,0);
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

    public TimeInterval copy() {
        return new TimeInterval(start.getHour(),start.getMinute(),end.getHour(),end.getMinute());
    }

    public void setFrom(TimeInterval interval) {
        start.setHour(interval.start.getHour());
        start.setMinute(interval.start.getMinute());
        end.setHour(interval.end.getHour());
        end.setMinute(interval.end.getMinute());
    }

    public boolean isEmpty() {
        if (start.getHour() == 0 && start.getMinute() == 0 && end.getHour() == 0 && end.getMinute() == 0){
            return true;
        }
        return false;
    }
}
