package john.my7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_of_list, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textViewDayOfWeek);
        ListView listView = (ListView) rowView.findViewById(R.id.DataListViewer);
        int cur = (position+World.CURRENT_DAY)%7;
        ViewGroup.LayoutParams lp = listView.getLayoutParams();
        lp.height = 80*timeTable.getDay(cur).getNumberIntervals();
        listView.setLayoutParams(lp);
//        ListView.LayoutParams normalParams = new ListView.LayoutParams(parent.getWidth(), 60*timeTable.getDay(cur).getNumberIntervals());
//        listView.setLayoutParams(normalParams);
        listView.setAdapter(lists[cur]);
        String s = "";
        if (position ==0){
            s+="Today:";
        }
        s+=timeTable.getDay(cur).getName();
        textView.setText(s);
        return rowView;
    }
}