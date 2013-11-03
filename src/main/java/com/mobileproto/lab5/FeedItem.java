package com.mobileproto.lab5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by evan on 9/25/13.
 */
public class FeedItem {

    public String name;
    public String year;
    public String blurb;
    public JSONArray inbox;
    public ArrayList<String> listwant;
    public ArrayList<String> listhelp;

    public FeedItem(JSONObject userData) throws JSONException{
        //Unpacking
        this.name = userData.get("name").toString();
        this.blurb = userData.get("blurb").toString();
        this.year = userData.get("year").toString();
        this.inbox = userData.getJSONArray("inbox");
        this.listwant = jsonTOArray(userData.getJSONArray("listwant"));
        this.listhelp = jsonTOArray(userData.getJSONArray("listhelp"));
    }
    private ArrayList<String> jsonTOArray(JSONArray json) throws JSONException{
        ArrayList<String> nue = new ArrayList<String>();
        for (int i=0; i < json.length(); i++){
            nue.add(json.get(i).toString());
        }
        return nue;
    }

}
