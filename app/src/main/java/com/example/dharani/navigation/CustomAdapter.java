package com.example.dharani.navigation;


import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<OrderData> implements View.OnClickListener{

    private ArrayList<OrderData> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView service_name;
        TextView date;
        TextView status;
    }

    public CustomAdapter(ArrayList<OrderData> data, Context context) {
        super(context, R.layout.list_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        OrderData dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.service_name = (TextView) convertView.findViewById(R.id.service_name);
            viewHolder.date = (TextView) convertView.findViewById(R.id.complaint_date);
            viewHolder.status = (TextView) convertView.findViewById(R.id.status);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.service_name.setText(dataModel.getService_name());
        viewHolder.date.setText(dataModel.getComplaint_date());
        viewHolder.status.setText(dataModel.getStatus());

        // Return the completed view to render on screen
        return convertView;
    }
}

