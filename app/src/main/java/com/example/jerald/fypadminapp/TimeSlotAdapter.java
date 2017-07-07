package com.example.jerald.fypadminapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15017292 on 16/6/2017.
 */

public class TimeSlotAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<TimeSlot> timelist = null;
    private ArrayList<TimeSlot> arraylist;

    public TimeSlotAdapter(Context context, List<TimeSlot> timelist) {
        mContext = context;
        this.timelist = timelist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<TimeSlot>();
        this.arraylist.addAll(timelist);
    }

    public class ViewHolder {
        TextView date;
        TextView time;
        TextView flight;
        TextView plane;
        TextView direction;

    }


    @Override
    public int getCount() {
        return timelist.size();
    }

    @Override
    public Object getItem(int position) {
        return timelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.timerow, null);
            // Locate the TextViews in listview_item.xml
            holder.date = (TextView) view.findViewById(R.id.tvDate);
            holder.time = (TextView) view.findViewById(R.id.tvTime);
            holder.flight = (TextView)view.findViewById(R.id.tvFlight);
            holder.plane = (TextView)view.findViewById(R.id.tvPlane);
            holder.direction = (TextView)view.findViewById(R.id.tvDirection);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.date.setText(timelist.get(position).getDate());
        holder.time.setText(timelist.get(position).getTime());
        holder.flight.setText(timelist.get(position).getFlightNo());
        holder.plane.setText(timelist.get(position).getPlaneID());
        holder.direction.setText(timelist.get(position).getDirection());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                open(timelist.get(position).getDate(),timelist.get(position).getTime(),timelist.get(position).getFlightNo(),timelist.get(position).getPlaneID(),timelist.get(position).getGateID());

            }
        });


        return view;
    }

    private void open(String...details){
        Intent i = new Intent(mContext, TimeSingleActivity.class);
        i.putExtra("date",details[0]);
        i.putExtra("time",details[1]);
        i.putExtra("flight",details[2]);
        i.putExtra("plane",details[3]);
        i.putExtra("gateID",details[4]);

        mContext.startActivity(i);
    }


}
