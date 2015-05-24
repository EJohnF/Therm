package john.my7;

/**
 * Created by John on 20.05.2015.
 */
public class TimeTable {
    Day[] days;
    private Temperature day;
    private Temperature night;
    public static final String[] dayName
            = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public Temperature getDay() {
        return day;
    }

    public void setDay(Temperature day) {
        this.day = day;
    }

    public Temperature getNight() {
        return night;
    }

    public void setNight(Temperature night) {
        this.night = night;
    }

    public class Day {
        public String name;
        public int number;
        TimeInterval[] intervals;
        int settedIntervals;

        public Day(String s) {
            name = s;
            intervals = new TimeInterval[10];
            settedIntervals = 0;
        }

        public Day(String s,int number) {
            this(s);
            this.number = number;
        }

        public void addInterval(TimeInterval interval) {
            intervals[settedIntervals] = interval;
            settedIntervals++;
        }

        public int getNumberIntervals() {
            return settedIntervals;
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
}

