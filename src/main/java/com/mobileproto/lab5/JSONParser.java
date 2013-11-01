package com.mobileproto.lab5;

//COPIED FROM http://www.androidhive.info/2012/01/android-json-parsing-tutorial/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    public static List<FeedItem> allMainFeedData; //All the data of the main feed
    public static BackgroundTask urlJSON;

    // constructor
    public JSONParser() {
        allMainFeedData = new ArrayList<FeedItem>();
        urlJSON = new BackgroundTask();
    }

    public JSONArray getJSONFromUrl(String url, String minijson) {

        //Passing URL to Asynced task, retrieving JSON string from internet
        BackgroundTask urlJSON = new BackgroundTask();
        urlJSON.execute(url);

        try {
            //Converting String => JSONObject => JSONArray
            JSONObject json = new JSONObject(urlJSON.get());
            JSONArray jsonarray = json.getJSONArray(minijson);
            return jsonarray;
        }
        catch (Exception e){
            Log.v("json err", e.getMessage());
            System.out.println("UNABLE TO CONVERT JSON to usable format");
        }

        return null;
    }

    public void makeCHAOSList(String feedURL) throws JSONException, Exception {

        //Extracting Data and putting it in the feed
        JSONArray allUserData = getJSONFromUrl(feedURL, "userdata");
        System.out.println(allUserData);

        for (int i=0; i < allUserData.length(); i++){
            try{
                JSONObject userData = allUserData.getJSONObject(i);

                //Adding to the main list
                FeedItem feedTweet = new FeedItem(userData);
                allMainFeedData.add(feedTweet);
            }
            catch (JSONException E){
                //Will throw if the JSONArray is not valid or null
                System.out.print("JSON DATA NOT FOUND IN URLDATA");
            }
        }

        System.out.println("ALL TWEETS RETRIEVED");
    }

    public List<FeedItem> getAllData(){
        return allMainFeedData;
    }


    public List<FeedItem> dataToFeedItem (String feedURL) throws JSONException, Exception {

        //Extracting Data and putting it in the search list
        List<FeedItem> allSearchData = new ArrayList<FeedItem>();
        JSONArray allDataFromURL = getJSONFromUrl(feedURL, "userdata");

        for (int i=0; i < allDataFromURL.length(); i++){
            try{
                JSONObject userInfo = allDataFromURL.getJSONObject(i);

                FeedItem feedProfile = new FeedItem(userInfo);
                allSearchData.add(feedProfile);
            }
            catch (JSONException E){
                //Will throw if the JSONArray is not valid or null
                System.out.print("JSON NOT FOUND");
            }
        }

        System.out.println("RETURNING: " + allSearchData);

        return allSearchData;
    }


    private class BackgroundTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String...url){

            // Making HTTP request
            try {
                // defaultHttpClient

                DefaultHttpClient httpClient = new DefaultHttpClient();
                //Calling GET to specified URL
                HttpGet httpGet = new HttpGet(url[0]);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } catch (UnsupportedEncodingException e) {
                System.out.print("UNSUPPORTED ENCODING EXCEPTION YOU INCOMPETENT BLOCKHEAD");
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                System.out.print("CLIENT PROTOCOL YOU BLOODY IDIOT");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.print("Cheese");
                e.printStackTrace();
            }


            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                json = sb.toString();
            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }

            return json;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

}