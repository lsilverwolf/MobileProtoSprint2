package com.mobileproto.lab5;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaustin on 10/6/13.
 */
public class DetailActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_fragment);

        Intent intent = getIntent();

        final TextView username = (TextView) findViewById(R.id.feedItemUser);
        final TextView blurb = (TextView) findViewById(R.id.feedText);
        final TextView year = (TextView) findViewById(R.id.classYearText);
        final TextView help = (TextView) findViewById(R.id.listHelpTitle);
        final TextView answer = (TextView) findViewById(R.id.listAnswerTitle);
        final ListView helpList = (ListView) findViewById(R.id.listHelp);
        final ListView answerList = (ListView) findViewById(R.id.listAnswer);
        final Button hiButton = (Button) findViewById(R.id.hiButton);
        final Button messageButton = (Button) findViewById(R.id.messageButton);

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to the message View
                Intent in = new Intent(getApplicationContext(), MessageActivity.class);
                in.putExtra("username", username.toString());
                startActivity(in);
            }
        });

        hiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to the "Hi sent" view
                Intent in = new Intent(getApplicationContext(), HiActivity.class);
                in.putExtra("username", username.toString());
                startActivity(in);
            }
        });

        //Getting user-object from stored Data
        String user = intent.getStringExtra("username");
        FeedItem userthis = FeedActivity.allData.get(0);
        for (int i=0; i < FeedActivity.allData.size(); i++){
            if (FeedActivity.allData.get(i).name == user){
                userthis = FeedActivity.allData.get(i);
            }
        }

        //Setting the values in the view
        username.setText(userthis.name);
        blurb.setText(userthis.blurb);
        year.setText(userthis.year);
        help.setText("I am curious about:"); //Static string
        answer.setText("I know about:"); // Static String

        try {
            //For help stuff
            ArrayAdapter<String> helpadapter = new ArrayAdapter(this,
                    R.layout.list_view,
                    userthis.listwant){
                public View getView(final int position, View convertView, ViewGroup parent) {
                    View view = View.inflate(DetailActivity.this, R.layout.list_view, null);
                    final TextView textView = (TextView) view.findViewById(R.id.listItem);
                    textView.setText(getItem(position).toString());
                    return view;
                }
            };
            helpList.setAdapter(helpadapter);

            //For help stuff
            ArrayAdapter<String> answeradapter =
                    new ArrayAdapter(this,R.layout.list_view, userthis.listhelp){
                    @Override
                    public View getView(final int position, View convertView, ViewGroup parent) {
                        View view = View.inflate(DetailActivity.this, R.layout.list_view, null);
                        final TextView textView = (TextView) view.findViewById(R.id.listItem);
                        textView.setText(getItem(position).toString());
                        return view;
                    }
                };

            answerList.setAdapter(answeradapter);

        }catch (Exception E){
            System.out.println("ARRAY ADAPTER IS BROKEN");
        }

    }

}
