package com.mobileproto.lab5;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kaustin on 11/3/13.
 */
public class InboxActivity extends Activity {

    ArrayList<InboxItem> inboxItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inbox_view);

        Intent intent = getIntent();
        final ListView inbox = (ListView) findViewById(R.id.inboxMessageList);

        inboxItem = new ArrayList<InboxItem>();

        InboxItem inA = new InboxItem("Axel Vicas", "Lyra! I’m really interested in getting involved with the Olin Robotic Sailing Team, and I was wondering if you could tell me a little bit about it put me in touch with the relevant person. The concept of a robotic sailboat seems really neat to me.");
        InboxItem inB = new InboxItem("Tim Ryan", "says HI!");
        InboxItem inC = new InboxItem("Finn Anders", "Hi Lyra, I’m really interested in Frankly Speaking this year, and I was wondering if I could submit an article about my adventures on Mt. Everest.");
        InboxItem inD = new InboxItem("Batman", "says HI!");
        InboxItem inE = new InboxItem("Zach Homans", "says HI!");

        inboxItem.add(inA);
        inboxItem.add(inB);
        inboxItem.add(inC);
        inboxItem.add(inD);
        inboxItem.add(inE);

        System.out.println(inboxItem);

        InboxListAdapter inboxListAdapter = new InboxListAdapter(this, inboxItem);
        inbox.setAdapter(inboxListAdapter);
    }
}
