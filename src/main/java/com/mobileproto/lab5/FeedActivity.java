package com.mobileproto.lab5;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends Activity {

    public static String myname = "student";
    public static List<FeedItem> allData;
//    public static CHAOSDbHelper dbHelper;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allData = new ArrayList<FeedItem>();
//        dbHelper = new CHAOSDbHelper(this);

        // Define view fragments
        FeedFragment feedFragment = new FeedFragment();
        MyProfileFragment myProfileFragment = new MyProfileFragment();
        SearchFragment searchFragment = new SearchFragment();
        //MessageFragment tweetFragment = new MessageFragment();

        /*
         *  The following code is used to set up the tabs used for navigation.
         *  You shouldn't need to touch the following code.
         */
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        ActionBar.Tab feedTab = actionBar.newTab().setText(R.string.tab1);
        feedTab.setTabListener(new NavTabListener(feedFragment));

        ActionBar.Tab searchTab = actionBar.newTab().setText(R.string.tab2);
        searchTab.setTabListener(new NavTabListener(searchFragment));

        ActionBar.Tab connectionTab = actionBar.newTab().setText(R.string.tab3);
        connectionTab.setTabListener(new NavTabListener(myProfileFragment));

        actionBar.addTab(feedTab);
        actionBar.addTab(searchTab);
        actionBar.addTab(connectionTab);

        actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.android_dark_blue)));

        updateDB();


    }

    public void updateDB(){
        //Getting JSON data
        //URL to GET all tweet data from the FEED
        String feedURL = "http://mysterious-lake-7154.herokuapp.com/";
        // Creating JSON Parser instance
        JSONParser jParser = new JSONParser();

        try {
            //Getting the array with all Tweets
            jParser.makeCHAOSList(feedURL);
            allData = jParser.getAllData();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("JPARSER CANNOT RETRIEVE");
        }

    }
}
