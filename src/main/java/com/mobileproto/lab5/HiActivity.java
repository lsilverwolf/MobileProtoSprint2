package com.mobileproto.lab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        //Pulling the ids in the "Hi sent" message layout
        final TextView username = (TextView) findViewById(R.id.hiName);
        final Button returnHome = (Button) findViewById(R.id.returnButton);

        //Setting the name of the user the Hi was sent to
        username.setText(user);

        //Setting the button to return to the home page (FeedActivity)
        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to the "Hi sent" view
                Intent in = new Intent(getApplicationContext(), FeedActivity.class);
                startActivity(in);
            }
        });

    }
}
