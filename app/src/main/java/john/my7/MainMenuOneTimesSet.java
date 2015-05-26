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
        final View rowView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textViewItem);
        ImageView rightView = (ImageView) rowView.findViewById(R.id.right_view);
        final TimeInterval timeInterval = day.getTimeInterval(position);
//        final View images = inflater.inflate(R.layout.activity_main, parent, false);
//        final ImageButton edit = (ImageButton) images.findViewById(R.id.imageButtonEdit);
//        final ImageButton delete = (ImageButton) images.findViewById(R.id.imageButtonDelete);
        textView.setText(day.getStringInterval(position));
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (World.IS_EDIT_MODE) {
                    int[] locat = new int[2];
                    v.getLocationOnScreen(locat);
                    locat[0] += v.getWidth() / 2;
                    locat[1] -= v.getHeight() / 2;
                    int height = World.editImageButton.getHeight();
                    int width = World.editImageButton.getWidth();
                    int padding = 5;
                    World.editImageButton.setBottom(locat[1]);
                    World.editImageButton.setLeft(locat[0]);
                    World.editImageButton.setTop(locat[1] - height);
                    World.editImageButton.setRight(locat[0] + width);
                    World.editImageButton.setVisibility(View.VISIBLE);

                    World.deleteImageButton.setBottom(locat[1]);
                    World.deleteImageButton.setLeft(locat[0] + width);
                    World.deleteImageButton.setTop(locat[1] - height);
                    World.deleteImageButton.setRight(locat[0] + width * 2 + padding);
                    World.deleteImageButton.setVisibility(View.VISIBLE);
                    World.selected_time_interval = timeInterval;
                }
            }
        });
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

    public void onClickItem(int position, View view){

    }
}
