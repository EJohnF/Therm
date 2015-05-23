package john.my7;

/**
 * Created by John on 20.05.2015.
 */
public class TimeTable {
    Day[] days;
    public static final String[] dayName
            = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public class Day {
        public String name;
        public int number;
        Time[] intervals;
        int settedIntervals;

        public Day(String s) {
            name = s;
            intervals = new Time[10];
            settedIntervals = 0;
        }

        public Day(String s,int number) {
            this(s);
            this.number = number;
        }

        public void addInterval(Time interval) {
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
        days[0].addInterval(new Time(0, 0, 6, 0));
        days[0].addInterval(new Time(6, 0, 8, 0));
        days[0].addInterval(new Time(8, 0, 12, 15));
        days[0].addInterval(new Time(12, 15, 13, 15));

        days[1].addInterval(new Time(0, 0, 6, 0));
        days[1].addInterval(new Time(6, 0, 8, 0));
        days[1].addInterval(new Time(8, 0, 12, 15));
        days[1].addInterval(new Time(12, 15, 13, 15));

        days[2].addInterval(new Time(0, 0, 6, 0));
        days[2].addInterval(new Time(6, 0, 8, 0));
        days[2].addInterval(new Time(8, 0, 12, 15));
        days[2].addInterval(new Time(12, 15, 13, 15));

        days[3].addInterval(new Time(0, 0, 6, 0));

        days[4].addInterval(new Time(0, 0, 6, 0));
        days[4].addInterval(new Time(6, 0, 10, 0));
    }

    public Day getDay(int position) {
        return days[position];
    }
}

