package edu.cse470.restaurantrater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class RestaurantDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME_1 = "newrestaurant.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_RESTAURANT = "create table restaurants (_id integer primary key autoincrement, "
            + "restaurantName text not null, restaurantAddress text, " + "city text, " + "state text, " + " zip text);";
    private static final String CREATE_TABLE_DISH = "create table dishes (_id integer primary key autoincrement, "
            + "dishName text not null, type text, " + "rating text, " + "restaurantsID integer references restaurants (_id)" + ")";

    public RestaurantDBHelper(Context context) {
        super(context, DATABASE_NAME_1, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_RESTAURANT);
        database.execSQL(CREATE_TABLE_DISH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(RestaurantDBHelper.class.getName(), "Upgrading database from version: " + oldVersion
                + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS restaurants");
        db.execSQL("DROP TABLE IF EXISTS dishes");
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // Enable foreign key constraints
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

}
