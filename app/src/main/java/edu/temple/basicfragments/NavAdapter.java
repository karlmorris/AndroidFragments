package edu.temple.basicfragments;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NavAdapter extends BaseAdapter{

    Context context;
    String[] data;

    public NavAdapter (Context context, String[] data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setId(position);
        label.setText(data[position]);
        label.setTextSize(20);
        label.setPadding(16, 25, 16, 25);

        return label;
    }
}
