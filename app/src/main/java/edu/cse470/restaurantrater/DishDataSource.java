package edu.cse470.restaurantrater;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DishDataSource{
    private SQLiteDatabase database;
    private RestaurantDBHelper dbHelper;

    public DishDataSource(Context context) {dbHelper = new RestaurantDBHelper(context);}

    public void open() throws SQLException {
        // open the database connection.
        database = dbHelper.getWritableDatabase();

    }

    public void close() {dbHelper.close();} // close the database connection.

    public boolean insertDish(Dish d) {
        // inserts dish data.
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("dishName", d.getDishName());
            initialValues.put("type", d.getType());
            initialValues.put("rating", d.getRating());
            initialValues.put("restaurantsID", d.getRestaurantID());

            didSucceed = database.insert("dishes", null, initialValues) > 0;
        }
        catch(Exception e) {

        }
        return didSucceed;

    }

    public boolean updateDish(Dish d) {
        boolean didSucceed = false;
        try {
            Long rowId = (long) d.getDishID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("dishName", d.getDishName());
            updateValues.put("type", d.getType());
            updateValues.put("rating", d.getRating());
            updateValues.put("restaurantsID", d.getRestaurantID());

            didSucceed = database.update("dishes", updateValues, "_id=" + rowId,  null) > 0;
        }
        catch(Exception e) {

        }
        return didSucceed;
    }

    public int getLastDishID() {
        int LastID = -1;
        try{
            String query = "Select MAX(_id) from dishes";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            LastID = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            LastID = -1;
        }
        return LastID;
    }

    public ArrayList<String> getDishName() {
        ArrayList<String> dishData = new ArrayList<>();
        try {
            String query = "Select dishName, type, rating from dishes";
            Cursor cursor = database.rawQuery(query, null);
            Dish newDish;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                @SuppressLint("Range") String dishNames = cursor.getString(cursor.getColumnIndex("dishName"));
                @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("type"));
                @SuppressLint("Range") String rating = cursor.getString(cursor.getColumnIndex("rating"));
                String data = dishNames + "\t" + type + "\t" + rating;
                dishData.add(data);
                newDish = new Dish();
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            dishData = new ArrayList<String>();
        }
        return dishData;
    }

    public Dish getSpecificDish(int dishID) {
        Dish dish = new Dish();
        String query = "SELECT * FROM dishes WHERE _id =" + dishID;
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            dish.setDishID(cursor.getInt(0));
            dish.setDishName(cursor.getString(1));
            dish.setType(cursor.getString(2));
            dish.setRating(cursor.getString(3));
            dish.setRestaurantId(cursor.getInt(4));
            cursor.close();
        }
        return dish;
    }

    public boolean deleteDish(int dishID) { // returns true if delete success or false if unsuccessful.
        boolean didDelete = false;
        try {
            didDelete = database.delete("dishes", "_id=" + dishID, null) > 0 &&
                    database.delete("restaurants", "_id=" + dishID, null) > 0;

        }
        catch (Exception e) {

        }
        return didDelete;
    }

    public ArrayList<Dish> getAllDishes() { // gets and sets all dishes values.
        ArrayList<Dish> dish = new ArrayList<Dish>();
        try {
            String query = "SELECT  * FROM dishes";
            Cursor cursor = database.rawQuery(query, null);

            Dish newDish;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newDish = new Dish();
                newDish.setDishID(cursor.getInt(0));
                newDish.setDishName(cursor.getString(1));
                newDish.setType(cursor.getString(2));
                newDish.setRating(cursor.getString(3));
                newDish.setRestaurantId(cursor.getInt(4));
                dish.add(newDish);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            dish = new ArrayList<Dish>();
        }
        return dish;
    }

}
