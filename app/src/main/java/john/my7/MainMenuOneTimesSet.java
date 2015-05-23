package john.my7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by John on 17.05.2015.
 */
public class MainMenuOneTimesSet extends BaseAdapter {
    private final Context context;
    TimeTable.Day day;

    public MainMenuOneTimesSet(Context parent) {
        super();
        this.context = parent;
    }

    public MainMenuOneTimesSet(Context parent, TimeTable.Day day){
        this(parent);
        this.day = day;
    }

    @Override
    public int getCount() {
        return day.getNumberIntervals();
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
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textViewItem);
        ImageView rightView = (ImageView) rowView.findViewById(R.id.right_view);
        textView.setText(day.getStringInterval(position));
        if (day.getIntervalType(position) == IntervalType.DAY) {
            rightView.setImageResource(R.drawable.sun);
        } else {
            rightView.setImageResource(R.drawable.moon);
        }
        if(position == 0 && day.getNumber()==World.CURRENT_DAY){
            rowView.setBackgroundResource(R.drawable.list_item_shape_red);
        }
        return rowView;
    }
}
