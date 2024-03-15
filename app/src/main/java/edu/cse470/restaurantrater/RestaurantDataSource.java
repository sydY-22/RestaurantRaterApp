package edu.cse470.restaurantrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class RestaurantDataSource {
    private SQLiteDatabase database;
    private RestaurantDBHelper dbHelper;

    public RestaurantDataSource(Context context) {
        dbHelper = new RestaurantDBHelper(context);
    }

    public void open() throws SQLException {
        // open the database connection.
        database = dbHelper.getWritableDatabase();

    }

    public void close() {dbHelper.close();} // close the database connection.

    public boolean insertRestaurant(Restaurant r) {
        // inserts restaurant data.
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("restaurantName", r.getrestaurantName());
            initialValues.put("restaurantAddress", r.getRestaurantAddress());
            initialValues.put("city", r.getCity());
            initialValues.put("state", r.getState());
            initialValues.put("zip", r.getZip());

            didSucceed = database.insert("restaurants", null, initialValues) > 0;

        }
        catch (Exception e) {

        }
        return didSucceed;
    }

    public boolean updateRestaurant(Restaurant r) {
        // uses Restaurant ID to overwrite the values in the Restaurant table.
        boolean didSucceed = false;
        try {
            Long rowId = (long) r.getRestaurantId();
            ContentValues updateValues = new ContentValues();

            updateValues.put("restaurantName", r.getrestaurantName());
            updateValues.put("restaurantAddress", r.getRestaurantAddress());
            updateValues.put("city", r.getCity());
            updateValues.put("state", r.getState());
            updateValues.put("zip", r.getZip());

            didSucceed = database.update("restaurants", updateValues, "_id=" + rowId, null) > 0;
        }
        catch (Exception e) {

        }
        return  didSucceed;
    }

    public int getLastRestaurantID() {
        int lastID = -1;
        try {
            String query = "Select MAX(_id) from restaurants";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastID = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastID = -1;
        }
        return lastID;
    }

    public ArrayList<Restaurant> getRestaurants() { // get all the attributes in restaurants table in ArrayList.
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        try {
            String query = "SELECT * FROM restaurants";
            Cursor cursor = database.rawQuery(query, null);

            Restaurant newRestaurant;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newRestaurant = new Restaurant();
                newRestaurant.setRestaurantId(cursor.getInt(0));
                newRestaurant.setRestaurantName(cursor.getString(1));
                newRestaurant.setRestaurantAddress(cursor.getString(2));
                newRestaurant.setCity(cursor.getString(3));
                newRestaurant.setState(cursor.getString(4));
                newRestaurant.setZip(cursor.getString(5));
                restaurants.add(newRestaurant);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            restaurants = new ArrayList<Restaurant>();
        }
        return restaurants;
    }

}
