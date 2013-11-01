package com.mobileproto.lab5;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

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
        final TextView help = (TextView) findViewById(R.id.listHelpTitle);
        final TextView answer = (TextView) findViewById(R.id.listAnswerTitle);
        final ListView helpList = (ListView) findViewById(R.id.listHelp);
        final ListView answerList = (ListView) findViewById(R.id.listAnswer);
        final Button hiButton = (Button) findViewById(R.id.hiButton);
        Button messageButton = (Button) findViewById(R.id.messageButton);

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to MessageFragment to make a message to the user
            }
        });

        hiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //log a "hi"
            }
        });

        username.setText(intent.getStringExtra("username"));
        tweet.setText(intent.getStringExtra("blurb"));
        year.setText(intent.getStringExtra("year"));
        help.setText("I am curious about:");
        answer.setText("I know about:");

    }

}
