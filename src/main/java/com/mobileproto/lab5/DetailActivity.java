package com.mobileproto.lab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kaustin on 10/6/13.
 */
public class DetailActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        Intent intent = getIntent();

        final TextView username = (TextView) findViewById(R.id.feedItemUser);
        final TextView tweet = (TextView) findViewById(R.id.feedText);
        final TextView year = (TextView) findViewById(R.id.classYearText);



        username.setText(intent.getStringExtra("username"));
        tweet.setText(intent.getStringExtra("blurb"));
        year.setText(intent.getStringExtra("year"));

    }

}
