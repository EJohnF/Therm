package john.my7;

import java.io.Serializable;

/**
 * Created by John on 17.05.2015.
 */
public class TimeInterval implements Comparable<TimeInterval>, Serializable {
    private Time start;
    private Time end;

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start.setHour(start.getHour());
        this.start.setMinute(start.getMinute());
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        if (start.compareTo(end)>0){
            setStart(end);
        }
        this.end.setHour(end.getHour());
        this.end.setMinute(end.getMinute());
    }

    public TimeInterval(int startHour, int startMinute, int endHour, int endMinute){
        this.start = new Time(startHour,startMinute);
        this.end = new Time(endHour,endMinute);
    }
    public TimeInterval(Time s, Time e){
        this.start = new Time(s.getHour(),s.getMinute());
        this.end = new Time(s.getHour(),s.getMinute());
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

    @Override
    public int compareTo(TimeInterval another) {
        return start.compareTo(another.getStart());
    }
}
