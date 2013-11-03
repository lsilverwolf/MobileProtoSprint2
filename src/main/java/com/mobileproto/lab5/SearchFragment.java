package com.mobileproto.lab5;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evan on 9/26/13.
 */
public class SearchFragment extends Fragment {

    public static List<FeedItem> allSearchData;
    Button search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allSearchData = new ArrayList<FeedItem>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.search_fragment, null);

        //SEARCH button setup
        Button search = (Button) v.findViewById(R.id.searchButton);
        final Context context = this.getActivity();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Gets the words entered by the user
                final TextView enteredSearch = (TextView) getActivity().findViewById(R.id.searchField);
                String searchWords = enteredSearch.getText().toString();

                // Set up the ArrayAdapter for the results of the search
                SearchListAdapter searchListAdapter = new SearchListAdapter(context, searchData(searchWords));
                ListView searchList = (ListView) v.findViewById(R.id.searchResults);
                searchList.setAdapter(searchListAdapter);

                searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        System.out.print("CLICKED ME");
                        //Getting title (id) of what is clicked

                        final TextView textView = (TextView) view.findViewById(R.id.feedUserName);
                        final TextView blurbText = (TextView) view.findViewById(R.id.blurbText);
                        final TextView yearText = (TextView) view.findViewById(R.id.classYearTextView);

                        String getUser = textView.getText().toString();

                        //Creating intent to pass information
                        Intent in = new Intent(getActivity(), DetailActivity.class);

                        in.putExtra("username", getUser);

                        //Going to new display of the note
                        startActivity(in);
                    }
                });

            }

        });

        search.setFocusable(false);

        return v;
    }

    private ArrayList<FeedItem> searchData(String searchfor){
        //Searches the main data of the activity for the specified string
        ArrayList<FeedItem> results = new ArrayList<FeedItem>();

        for (int i=0; i < FeedActivity.allData.size(); i++){

            //Making variables for ease of use
            FeedItem userdata = FeedActivity.allData.get(i);
            ArrayList<String> listhelp = userdata.listhelp;

            //Check username first
            if (userdata.name.equalsIgnoreCase(searchfor)){
                results.add(userdata);
            }
            else {
                //Checking listhelp if there is any match in the search
                for (int n=0; n<listhelp.size(); n++){
                    if (listhelp.get(n).equalsIgnoreCase(searchfor) ){
                        results.add(userdata);
                        break;
                    }
                }
            }

        }

        return results;
    }

}
