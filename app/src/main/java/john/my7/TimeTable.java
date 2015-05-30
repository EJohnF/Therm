package john.my7;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by John on 20.05.2015.
 */

public class TimeTable  implements Serializable {
    public static final String fileName = "time_table";
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

    public class Day implements Serializable{
        public String name;
        public int number;
        ArrayList<TimeInterval> intervals;
        boolean firstIsNight = true;

        public Day(String s) {
            name = s;
            intervals = new ArrayList<>();
        }

        public Day(String s, int number) {
            this(s);
            this.number = number;
        }

        public void addInterval(TimeInterval interval) {
            if (interval.getStart().compareTo(new Time(0, 0)) == 0) {
                firstIsNight = !firstIsNight;
            }
            if (intervals.size() < World.MAX_INTERVALS) {
                int i = 0;
                while (i < intervals.size() && intervals.get(i).compareTo(interval) < 0) {
                    i++;
                }
                if (i == intervals.size() || i == 0) {
                    intervals.add(interval);
                } else {
                    intervals.add(i, interval);
                }
                wasEditInterval(interval);
            }
        }

        public void sortIntervals() {
            Collections.sort(intervals);
        }

        public void wasEditInterval(TimeInterval interval) {
            sortIntervals();
            int i = intervals.indexOf(interval);
            if (i > 0) {
                if (intervals.get(i-1).getStart().compareTo(interval.getStart()) == 0){
                    // если с другим интервалом одинаковое время начала, то сдвигаю его вперёд
                    intervals.get(i-1).setStart(interval.getEnd());
                }
                else {
                    //иначе предыдёщий конец сдвигается в начало нового
                    intervals.get(i - 1).setEnd(interval.getStart());
                }
            }
            sortIntervals();
            if (i < intervals.size() - 1) {
                int p = 1;
                // удаление интервалов, которы новый перегрывает
                while (i + p < intervals.size() && intervals.get(i + p).getEnd().compareTo(intervals.get(i).getEnd()) <= 0)
                    intervals.remove(i + p);
            }
            if (i < intervals.size() - 1) {
                intervals.get(i + 1).setStart(intervals.get(i).getEnd());
            }
            correctIntervals();
        }

        public void deleteTimeInterval(TimeInterval interval){
            if (interval.getStart().compareTo(new Time(0,0)) == 0)
                firstIsNight = !firstIsNight;
            if (intervals.size() == 1)
                return;
            intervals.remove(interval);
            correctIntervals();
        }
        public void correctIntervals(){
            if (intervals.size() == 0)
                return;
            for (int i = 0; i < intervals.size();i++) {
                if (intervals.get(i).getStart().compareTo(intervals.get(i).getEnd()) == 0) {
                    intervals.remove(i);
                    //удаление нулевых интервалов
                }
            }
            for (int i = 0; i < intervals.size()-1;i++){
                intervals.get(i).setEnd(intervals.get(i+1).getStart());
            }
            int i = intervals.size()-1;
            if (intervals.get(i).getStart().compareTo(intervals.get(i).getEnd()) == 0) {
                intervals.remove(i);
            }
            intervals.get(0).setStart(new Time(0, 0));
            if (intervals.get(intervals.size()-1).getEnd().compareTo(new Time(23, 59)) < 0){
                intervals.add(new TimeInterval(intervals.get(intervals.size()-1).getEnd().getHour(),intervals.get(intervals.size()-1).getEnd().getMinute(), 23, 59));
            }
        }

        public int getNumberIntervals() {
            return intervals.size();
        }

        public String getStringInterval(int position) {
            return intervals.get(position).toString();
        }

        public IntervalType getIntervalType(int position) {
            int t = firstIsNight ? 0 : 1;
            if ((position + t) % 2 == 0)
                return IntervalType.NIGHT;
            else return IntervalType.DAY;
        }

        public String getName() {
            return name;
        }

        public int getNumber() {
            return number;
        }

        public Temperature getCurrentTemp() {
            for (int i = 0; i < intervals.size(); i++) {
                if (intervals.get(i).isInInterval(World.CURRENT_TIME)) {
                    if (getIntervalType(i) == IntervalType.DAY)
                        return day;
                    else return night;
                }
            }
            return day;
        }

        public TimeInterval getTimeInterval(int position) {
            return intervals.get(position);
        }
    }

    public TimeTable(Context context) {
        this(context,fileName);
    }

    public TimeTable(Context context, String fileName) {
        days = new Day[7];
        for (int i = 0; i < 7; i++) {
            days[i] = new Day(dayName[i], i);
            days[i].addInterval(new TimeInterval(0, 0, 23, 59));
            days[i].firstIsNight = true;
        }
        try {
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            TimeTable upload = (TimeTable) is.readObject();
            this.days = upload.days;
            this.day = upload.day;
            this.night = upload.night;
            is.close();
            fis.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Day getDay(int position) {
        return days[position];
    }

    public Temperature getCurrentGoal() {
        return days[World.CURRENT_DAY].getCurrentTemp();
    }

    public void saveData(Context context){
        saveData(context,fileName);
    }
    // TODO: пока не работет. А именно: не сохраняется/загружается корректно. FileNotFoundException
    public void saveData(Context context, String fileName) {
        try {
            Log.d("hey", "save data with file " + fileName);
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(this);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void changeFromFile(Context context, String fileName) {
        try {
            Log.d("hey", "change from file with file " + fileName);
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            TimeTable upload = (TimeTable) is.readObject();
            this.days = upload.days;
            this.day = upload.day;
            this.night = upload.night;
            is.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

