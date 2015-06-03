package john.my7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by John on 17.05.2015.
 */
public class MainMenuOneTimesSet extends BaseAdapter {
    private final Context context;
    TimeTable.Day day;
    boolean isEditMode;
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
        if (isEditMode){
            if (day.getNumberIntervals() < World.MAX_INTERVALS)
                return day.getNumberIntervals()+1;
            else return day.getNumberIntervals();
        }
        else return day.getNumberIntervals();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textViewItem);
        ImageView rightView = (ImageView) rowView.findViewById(R.id.right_view);
        if (day.getIntervalType(position) == IntervalType.DAY) {
            rightView.setImageResource(R.drawable.sun1);
        } else {
            rightView.setImageResource(R.drawable.moon);
        }
        if(day.getNumber()==World.CURRENT_DAY){
            if (day.getPositionByTime(World.CURRENT_TIME) == position) {
                rowView.setBackgroundResource(R.drawable.list_item_shape_red);
            }
        }
        if (isEditMode && position == day.getNumberIntervals()){
            ImageView leftView = (ImageView) rowView.findViewById(R.id.view_clock);
            rightView.setVisibility(View.INVISIBLE);
            leftView.setVisibility(View.INVISIBLE);
            textView.setText("add new interval...");
            World.selected_time_interval = new TimeInterval(0,0,0,0);
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    World.SELECTED_DAY = day;
                    World.mainActivity.onClickEditTimeInterval(v);
                }
            });
        }
        else {

            final TimeInterval timeInterval = day.getTimeInterval(position);
            textView.setText(day.getStringInterval(position));
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isEditMode) {
                        World.SELECTED_DAY = day;
                        World.selected_time_interval = timeInterval;
                        if (day.getNumberIntervals() == 1) {
                            World.SELECTED_DAY.firstIsNight = !World.SELECTED_DAY.firstIsNight;
                            ((BaseAdapter) World.mainActivity.mainListView.getAdapter()).notifyDataSetChanged();
                            Toast.makeText(World.mainActivity, "Without intervals you can only change temperature type", Toast.LENGTH_LONG).show();
                        }
                        else {
                            World.mainActivity.openPopupEditDelete(day, position, rowView);
                        }
                    }
                }
            });
        }
        return rowView;
    }

    public void enterToEditMode(){
        isEditMode = true;
    }
    public void outFromEditMode(){
        isEditMode = false;
    }

}
