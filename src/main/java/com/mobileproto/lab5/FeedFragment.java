package com.mobileproto.lab5;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by evan on 9/25/13.
 */
public class FeedFragment extends Fragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.feed_fragment, null);


        // Set up the ArrayAdapter for the feedList
        FeedListAdapter feedListAdapter = new FeedListAdapter(this.getActivity(), FeedActivity.allData);
        ListView feedList = (ListView) v.findViewById(R.id.miniProfileList);
        feedList.setAdapter(feedListAdapter);


        feedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.print("CLICKED ME");
                //Getting title (id) of what is clicked

                final TextView textView = (TextView) view.findViewById(R.id.feedUserName);
                final TextView blurbText = (TextView) view.findViewById(R.id.blurbText);
                final TextView yearText = (TextView) view.findViewById(R.id.classYearTextView);

                String getUser = textView.getText().toString();
                String blurb = blurbText.getText().toString();
                String year = yearText.getText().toString();

                //Creating intent to pass information
                Intent in = new Intent(getActivity(), DetailActivity.class);

                in.putExtra("username", getUser);
                in.putExtra("blurb", blurb);
                in.putExtra("year", year);

                //Going to new display of the note
                startActivity(in);
            }
        });

        return v;

    }
}
