package com.mobileproto.lab5;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evan on 9/25/13.
 */
public class MyProfileFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.detail_view, null);

        final TextView username = (TextView) getActivity().findViewById(R.id.feedItemUser);
        final TextView tweet = (TextView) getActivity().findViewById(R.id.feedText);
        final TextView year = (TextView) getActivity().findViewById(R.id.classYearText);
        final TextView help = (TextView) v.findViewById(R.id.listHelpTitle);
        final TextView answer = (TextView) v.findViewById(R.id.listAnswerTitle);
        final ListView helpList = (ListView) v.findViewById(R.id.listHelp);
        final ListView answerList = (ListView) v.findViewById(R.id.listAnswer);
        final Button inboxButton = (Button) v.findViewById(R.id.inboxButton);

        inboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to the "Hi sent" view
                Intent in = new Intent(getActivity(), InboxActivity.class);
                startActivity(in);
            }
        });

        return v;
    }
}
