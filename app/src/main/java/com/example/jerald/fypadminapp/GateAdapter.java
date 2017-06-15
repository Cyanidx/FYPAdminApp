package com.example.jerald.fypadminapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by 15017292 on 4/6/2017.
 */

public class GateAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Gate> gatelist = null;
    private ArrayList<Gate> arraylist;

    public GateAdapter(Context context, List<Gate> gatelist) {
        mContext = context;
        this.gatelist = gatelist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Gate>();
        this.arraylist.addAll(gatelist);
    }

    public class ViewHolder {
        TextView gate;
        TextView terminal;

    }


    @Override
    public int getCount() {
        return gatelist.size();
    }

    @Override
    public Object getItem(int position) {
        return gatelist.get(position);
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
            view = inflater.inflate(R.layout.row, null);
            // Locate the TextViews in listview_item.xml
            holder.gate = (TextView) view.findViewById(R.id.gate);
            holder.terminal = (TextView) view.findViewById(R.id.terminal);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.gate.setText(gatelist.get(position).getGateName());
        holder.terminal.setText(gatelist.get(position).getTerminalName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                open(gatelist.get(position).getGateName(),gatelist.get(position).getTerminalName(),gatelist.get(position).getId());

            }
        });


        return view;
    }

    private void open(String...details){
        Intent i = new Intent(mContext, GateSingleActivity.class);
        i.putExtra("gateName",details[0]);
        i.putExtra("terminalName",details[1]);
        i.putExtra("id",details[2]);

        mContext.startActivity(i);
    }

}
