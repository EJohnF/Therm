package john.my7;

import java.util.LinkedList;

/**
 * Created by John on 20.05.2015.
 */

/*

ОЧЕНЬ ВАЖНО!!!
 TODO: сейчас в реализации заложено то, что первый интервал времени - всегда ночь, и далее они чередуются с днём
 */
public class TimeTable {
    Day[] days;
    private Temperature day;
    private Temperature night;
    public static final String[] dayName
            = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public Temperature getDayTemp() {
        return day;
    }

    public void setDayTemp(Temperature day) {
        this.day = day;
    }

    public Temperature getNightTemp() {
        return night;
    }

    public void setNightTemp(Temperature night) {
        this.night = night;
    }

    public class Day {
        public String name;
        public int number;
        LinkedList<TimeInterval> intervals;
        int settedIntervals;
        boolean firstIsNight = true;

        public Day(String s) {
            name = s;
            intervals = new LinkedList<>();
            settedIntervals = 0;
        }

        public Day(String s,int number) {
            this(s);
            this.number = number;
        }

        public void addInterval(TimeInterval interval) {
            if (settedIntervals < World.MAX_INTERVALS) {
                int i = 0;
                while (i<settedIntervals && intervals [i].getStart().compareTo(interval.getStart()) < 0)
                    i++;
                if (i == settedIntervals)
                    intervals[settedIntervals] = interval;
                else {
                    i--;
                    intervals[i].setEnd(interval.getStart());
                    int j = settedIntervals;
                    while (j > i+1){
                        intervals[j] = intervals[j-1];
                        j--;
                    }
                    intervals[i+1] = interval;
                    j = i+2;
                    int k = 0;
                    while (j<settedIntervals && intervals[j].getEnd().compareTo(intervals[i+1].getEnd())<0){
                        intervals[j] = intervals[j+k+1];
                        k++;
                    }
                    int p = 0;
                    while (j+k+p <= settedIntervals){
                        intervals[j+p] = intervals[j+k+p];
                        p++;
                    }
                    intervals[i+2].setStart(intervals[i+1].getEnd());
                    settedIntervals -= k;
                }
                settedIntervals++;
            }
        }
        public int getNumberIntervals() {
            return intervals.size();
        }

        public String getStringInterval(int position) {
            return intervals[position].toString();
        }

        public IntervalType getIntervalType(int position) {
            if (position % 2 == 0)
                return IntervalType.NIGHT;
            else return IntervalType.DAY;
        }
        public String getName(){
            return name;
        }
        public int getNumber(){
            return number;
        }
        public Temperature getCurrentTemp(){
            for (int i = 0; i < settedIntervals; i++) {
                if (intervals[i].isInInterval(World.CURRENT_TIME)){
                    // TODO: re-implement here, for correctly work when the first temperature is not night
                    if (i % 2 == 0){
                        return day;
                    }
                    else return night;
                }
            }
            return day;
        }

        public TimeInterval getTimeInterval(int position){
            return intervals[position];
        }
    }

    public TimeTable() {
        days = new Day[7];
        for (int i = 0; i < 7; i++) {
            days[i] = new Day(dayName[i],i);
        }
        days[0].addInterval(new TimeInterval(0, 0, 6, 0));
        days[0].addInterval(new TimeInterval(6, 0, 8, 0));
        days[0].addInterval(new TimeInterval(8, 0, 12, 15));
        days[0].addInterval(new TimeInterval(12, 15, 13, 15));

        days[1].addInterval(new TimeInterval(0, 0, 6, 0));
        days[1].addInterval(new TimeInterval(6, 0, 8, 0));
        days[1].addInterval(new TimeInterval(8, 0, 12, 15));
        days[1].addInterval(new TimeInterval(12, 15, 13, 15));

        days[2].addInterval(new TimeInterval(0, 0, 6, 0));
        days[2].addInterval(new TimeInterval(6, 0, 8, 0));
        days[2].addInterval(new TimeInterval(8, 0, 12, 15));
        days[2].addInterval(new TimeInterval(12, 15, 13, 15));

        days[3].addInterval(new TimeInterval(0, 0, 6, 0));

        days[4].addInterval(new TimeInterval(0, 0, 6, 0));
        days[4].addInterval(new TimeInterval(6, 0, 10, 0));
    }

    public Day getDay(int position) {
        return days[position];
    }

    public Temperature getCurrentGoal(){
        return days[World.CURRENT_DAY].getCurrentTemp();
    }
}

