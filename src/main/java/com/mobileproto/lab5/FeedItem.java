package com.mobileproto.lab5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by evan on 9/25/13.
 */
public class FeedItem {

    public String name;
    public String year;
    public String blurb;
    public JSONArray inbox;
    public JSONArray listwant;
    public JSONArray listhelp;

    public FeedItem(JSONObject userData) throws JSONException{
        //Unpacking
        this.name = userData.get("name").toString();
        this.blurb = userData.get("blurb").toString();
        this.year = userData.get("year").toString();
        this.inbox = userData.getJSONArray("inbox");
        this.listwant = userData.getJSONArray("listwant");
        this.listhelp = userData.getJSONArray("listhelp");
    }

}
