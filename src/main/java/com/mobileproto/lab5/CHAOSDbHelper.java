package com.mobileproto.lab5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kaustin on 10/5/13.
 */
public class CHAOSDbHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_CHAOS_USERS = "chaosdb";
        public static final String _ID = "_id";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_BLURB = "tweet";
        public static final String COLUMN_NAME_DATE = "date";

        public static final String TABLE_USERS = "usertb";
        public static final String COLUMN_NAME_FOLLOWERS = "followers";
        public static final String COLUMN_NAME_FOLLOWING = "following";
    }

    //Methods to maintain database and tables
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String CREATE_TABLE_TWEETFEED =
            "CREATE TABLE " + FeedEntry.TABLE_CHAOS_USERS + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_BLURB + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_DATE + TEXT_TYPE + " )";

    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + FeedEntry.TABLE_USERS + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_FOLLOWERS + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_FOLLOWING + TEXT_TYPE + " )";

    private static final String DELETE_TWEETFEED_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_CHAOS_USERS;
    private static final String DELETE_USERS_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_USERS;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tweeters.db";

    public CHAOSDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TWEETFEED);
        db.execSQL(CREATE_TABLE_USERS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DELETE_TWEETFEED_ENTRIES);
        db.execSQL(DELETE_USERS_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /*
    * Creating TABLE_CHAOS_USERS
    */
    public void addtoTweetFeedDB(JSONObject objInfo) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_USERNAME, objInfo.getString("username"));
        values.put(FeedEntry.COLUMN_NAME_BLURB, objInfo.getString("tweet"));
        values.put(FeedEntry.COLUMN_NAME_DATE, objInfo.getString("date"));

        // insert row
        long newRowId = db.insert(FeedEntry.TABLE_CHAOS_USERS, null, values);
        Log.v("ROW ID: ", ""+newRowId);

    }
    public Cursor getCHAOSFeedDB(String date){
        //Getting the query of the Main tweet feed
        String[] allCols = {FeedEntry._ID,
                FeedEntry.COLUMN_NAME_USERNAME,
                FeedEntry.COLUMN_NAME_BLURB,
                FeedEntry.COLUMN_NAME_DATE};
        //Turning DB into string array, getting specific title of what is clicked
        return this.getReadableDatabase().query(FeedEntry.TABLE_CHAOS_USERS,
                allCols, "date=" + "\"" + date + "\"", null, null, null, null);//"date=" + "\"" + date + "\"", null, null, null, null);

//        tweetsdb.moveToFirst();

//        return tweetsdb;
    }
    public boolean isInTweetsDB(String id) {
        //Checks if a tweet is stored in the TWEETFEED database
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedEntry.TABLE_CHAOS_USERS + " WHERE " + "tweetid" + "= '" + id + "'", null);

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public void addtoUsersDB(String myname, JSONArray followers, JSONArray following) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_USERNAME, myname);
        values.put(FeedEntry.COLUMN_NAME_FOLLOWERS, followers.toString());
        values.put(FeedEntry.COLUMN_NAME_FOLLOWING, following.toString());

        // insert row
        long newRowId = db.insert(FeedEntry.TABLE_USERS, null, values);
        Log.v("ROW ID: ", ""+newRowId);
    }

    public Cursor getUsersDB(String username){
        //Getting the query of the Main tweet feed
        String[] allCols = {FeedEntry._ID,
                FeedEntry.COLUMN_NAME_USERNAME,
                FeedEntry.COLUMN_NAME_FOLLOWERS,
                FeedEntry.COLUMN_NAME_FOLLOWING};
        //Turning DB into string array, getting specific title of what is clicked
        Cursor usersdb = this.getReadableDatabase().query(FeedEntry.TABLE_USERS,
                allCols, "username=" + "\"" + username + "\"", null, null, null, null);

        return usersdb;
    }
    public boolean isInUsersDB(String username) {
        //Checks if a tweet is stored in the TWEETFEED database
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedEntry.TABLE_USERS + " WHERE " + "username" + "= '" + username + "'", null);

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
    public void updateUsersDB(String myname, JSONArray followers, JSONArray following){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_USERNAME, myname);
        values.put(FeedEntry.COLUMN_NAME_FOLLOWERS, followers.toString());
        values.put(FeedEntry.COLUMN_NAME_FOLLOWING, following.toString());

        // updating row
        db.update(FeedEntry.TABLE_USERS, values, FeedEntry.COLUMN_NAME_USERNAME + " = ?",
                new String[] { myname });
    }
}
