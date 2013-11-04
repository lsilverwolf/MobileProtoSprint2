package com.mobileproto.lab5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kaustin on 11/3/13.
 */
public class InboxListAdapter extends ArrayAdapter<InboxItem> {

    private final Activity activity;
    private final List<InboxItem> data;

    public InboxListAdapter(Activity activity,  List<InboxItem> data){
        super(activity, R.layout.inbox_item, data);
        this.activity = activity;
        this.data = data;
    }

    private class InboxItemHolder{

        TextView userName;
        TextView message;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        System.out.println("GETTING SEARCH LIST VIEW");

        InboxItemHolder holder;
        View inboxRow = convertView;

        if(inboxRow == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inboxRow = inflater.inflate(R.layout.inbox_item, parent, false);

            //Passing data of Feed Item to min-profile
            holder = new InboxItemHolder();
            holder.userName = (TextView) inboxRow.findViewById(R.id.inboxSender);
            holder.message = (TextView) inboxRow.findViewById(R.id.inboxMessage);

            inboxRow.setTag(holder);
        } else {
            holder = (InboxItemHolder) inboxRow.getTag();
        }

        InboxItem item = data.get(position);

        holder.userName.setText(item.name);
        holder.message.setText(item.message);

        return inboxRow;
    }
}