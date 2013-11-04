package com.mobileproto.lab5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kaustin on 11/3/13.
 */
public class InboxItem {

    public String name;
    public String date;
    public String message;

    public InboxItem(String name, String date, String message) {
        //Unpacking
        this.name = name;
        this.date = date;
        this.message = message;
    }

}
