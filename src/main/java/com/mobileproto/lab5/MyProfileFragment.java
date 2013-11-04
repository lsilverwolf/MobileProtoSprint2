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
import android.widget.ArrayAdapter;
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


        ArrayList<String> cursiouslist = new ArrayList<String>();
        cursiouslist.add("SCOPE");
        cursiouslist.add("ADE");
        cursiouslist.add("ECE after Olin");
        cursiouslist.add("E:C after Olin");

        ArrayList<String> canhelp = new ArrayList<String>();
        canhelp.add("Frankly Speaking");
        canhelp.add("Junior Year");
        canhelp.add("POE");
        canhelp.add("Olin Robotic Sailing Team");

        try {
            //For help stuff
            ArrayAdapter<String> helpadapter = new ArrayAdapter(getActivity(),
                    R.layout.list_view,
                    cursiouslist){
                public View getView(final int position, View convertView, ViewGroup parent) {
                    View view = View.inflate(getActivity(), R.layout.list_view, null);

                    final TextView textView = (TextView) view.findViewById(R.id.listItem);
                    textView.setText(getItem(position).toString());

                    return view;
                }
            };
            helpList.setAdapter(helpadapter);

            //For help stuff
            ArrayAdapter<String> answeradapter =
                    new ArrayAdapter(getActivity(), R.layout.list_view, canhelp){
                        @Override
                        public View getView(final int position, View convertView, ViewGroup parent) {
                            View view = View.inflate(getActivity(), R.layout.list_view, null);
                            final TextView textView = (TextView) view.findViewById(R.id.listItem);
                            textView.setText(getItem(position).toString());
                            return view;
                        }
                    };

            answerList.setAdapter(answeradapter);

        }catch (Exception E){
            System.out.println("ARRAY ADAPTER IS BROKEN");
        }

        return v;
    }
}
