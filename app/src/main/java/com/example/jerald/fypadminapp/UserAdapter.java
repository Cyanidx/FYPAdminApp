package com.example.jerald.fypadminapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15017292 on 19/6/2017.
 */

public class UserAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<User> userlist = null;
    private ArrayList<User> arraylist;

    public UserAdapter(Context context, List<User> userlist) {
        mContext = context;
        this.userlist = userlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<User>();
        this.arraylist.addAll(userlist);
    }

    public class ViewHolder {
        TextView name;
        TextView role;

    }

    @Override
    public int getCount() {
        return userlist.size();
    }

    @Override
    public Object getItem(int position) {
        return userlist.get(position);
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
            view = inflater.inflate(R.layout.userrow, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.role = (TextView) view.findViewById(R.id.role);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(userlist.get(position).getName());
        holder.role.setText(userlist.get(position).getRole());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        return view;
    }

}
