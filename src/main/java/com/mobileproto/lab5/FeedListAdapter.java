package com.mobileproto.lab5;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by evan on 9/25/13.
 */
public class FeedListAdapter extends ArrayAdapter<FeedItem> {

    private final Context context;
    private final List<FeedItem> data;

    public FeedListAdapter(Context context,  List<FeedItem> data){
        super(context, R.layout.feed_item, data);
        this.context = context;
        this.data = data;
    }


    private class FeedItemHolder{

        TextView userName;
        TextView text;
        TextView classYear;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        FeedItemHolder holder;
        View feedRow = convertView;

        if(feedRow == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            feedRow = inflater.inflate(R.layout.feed_item, parent, false);
            holder = new FeedItemHolder();
            holder.userName = (TextView) feedRow.findViewById(R.id.feedItemUser);
            holder.text = (TextView) feedRow.findViewById(R.id.feedText);
            holder.classYear = (TextView) feedRow.findViewById(R.id.classYearText);

            feedRow.setTag(holder);
        } else {
            holder = (FeedItemHolder) feedRow.getTag();
        }

        FeedItem item = data.get(position);

        holder.userName.setText(item.userName);
        holder.text.setText(item.text);
        holder.classYear.setText(item.year);

        return feedRow;
    }

}
