package com.mobileproto.lab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by kaustin on 11/3/13.
 */
public class HiActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hi_view);

        Intent intent = getIntent();
        String user = intent.getStringExtra("username");

        final TextView username = (TextView) findViewById(R.id.feedItemUser);

    }
}
