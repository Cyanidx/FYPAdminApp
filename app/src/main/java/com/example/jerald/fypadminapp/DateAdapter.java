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
 * Created by 15017292 on 24/6/2017.
 */

public class DateAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Date> timelist = null;
    private ArrayList<Date> arraylist;

    public DateAdapter(Context context, List<Date> timelist) {
        mContext = context;
        this.timelist = timelist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Date>();
        this.arraylist.addAll(timelist);
    }

    public class ViewHolder {
        TextView date;


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
            view = inflater.inflate(R.layout.daterow, null);
            // Locate the TextViews in listview_item.xml
            holder.date = (TextView) view.findViewById(R.id.tvDate);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.date.setText(timelist.get(position).getDate());


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                open(timelist.get(position).getDate(),timelist.get(position).getGateID());

            }
        });


        return view;
    }

    private void open(String...details){
        Intent i = new Intent(mContext, SingleDateActivity.class);
        i.putExtra("date",details[0]);
        i.putExtra("gateID",details[1]);


        mContext.startActivity(i);
    }


}

