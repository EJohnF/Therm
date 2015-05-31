package john.my7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by John on 20.05.2015.
 */
public class MainMenuAllTimeSet extends BaseAdapter {
    MainMenuOneTimesSet[] lists;
    Context context;
    TimeTable timeTable;
    boolean isEditMode;
    int width, height;

    public MainMenuAllTimeSet(Context context, TimeTable table){
        this.context = context;
        timeTable = table;
        lists = new MainMenuOneTimesSet[7];
        for (int i = 0; i<7;i++){
            lists[i] = new MainMenuOneTimesSet(context, timeTable.getDay(i));
        }
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return lists[(position+World.CURRENT_DAY)%7];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.row_of_list, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textViewDayOfWeek);
        ListView listView = (ListView) rowView.findViewById(R.id.DataListViewer);

        int cur = (position+World.CURRENT_DAY)%7;
        ViewGroup.LayoutParams lp = listView.getLayoutParams();
        int scale = timeTable.getDay(cur).getNumberIntervals();
        if (isEditMode)
            scale++;
        /*TODO вот это число 80  надо как-то пересчитывать в зависимости от размеров экрана
         например  потмоу что на планшете 80 - это много
        */
        System.out.println("height row: " + rowView.getHeight());
        //lp.height =  82*scale;
        lp.height = ((int)World.mainActivity.getResources().getDimension(R.dimen.row_height)) * scale;
        listView.setLayoutParams(lp);
//        ListView.LayoutParams normalParams = new ListView.LayoutParams(parent.getWidth(), 60*timeTable.getDay(cur).getNumberIntervals());
//        listView.setLayoutParams(normalParams);
        listView.setAdapter(lists[cur]);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        System.out.println("heightlist: " + listView.getHeight());
        String s = "";
        if (position ==0){
            s+="Today:";
        }
        s+=timeTable.getDay(cur).getName();
        textView.setText(s);
        return rowView;
    }
    public void enterToEditMode(){
        isEditMode = true;
        for (int i = 0; i<7;i++){
            lists[i].enterToEditMode();
        }
    }
    public void outFromEditMode(){
        isEditMode = false;
        for (int i = 0; i<7;i++){
            lists[i].outFromEditMode();
        }
    }

    public void setTimeTable(TimeTable table){
        timeTable = table;
    }
    int lastPosition = 0;
    int lastDay = World.CURRENT_DAY;

    /**
     *
     * @return if was change time interval
     */
    public boolean timeTick() {
        boolean res = false;
        int pos = timeTable.getDay(World.CURRENT_DAY).getPositionByTime(World.CURRENT_TIME);
        if (pos != lastPosition){
            lastPosition = pos;
            notifyDataSetChanged();
            res = true;
        }
        if (lastDay != World.CURRENT_DAY){
            lastDay = World.CURRENT_DAY;
            res = true;
            notifyDataSetChanged();
        }
        return res;
    }

    public void setDemision(int width, int height) {
        this.width = width;
        this.height = height;
    }
}