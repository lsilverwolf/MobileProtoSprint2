package com.mobileproto.lab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityTestCase;

/**
 * Created by kaustin on 11/3/13.
 */
public class MessageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_view);

        Intent intent = getIntent();
        String user = intent.getStringExtra("username");


    }
}
