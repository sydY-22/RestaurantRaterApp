//package edu.cse470.restaurantrater;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//public class DishDBHelper extends SQLiteOpenHelper{
//    private static final String DATABASE_NAME_2 = "mydish.db";
//    private static final int DATABASE_VERSION = 1;
//
//    private static final String CREATE_TABLE_DISH = "create table dishes (_id integer primary key autoincrement, "
//            + "dishName text not null, type text," + "rating text," + "foreign key (restaurantId) references restaurants(_id));";
//
//    public DishDBHelper(Context context) {
//        super(context, DATABASE_NAME_2, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase database) {database.execSQL(CREATE_TABLE_DISH);}
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        Log.w(DishDBHelper.class.getName(), "Upgrading database from version: " + oldVersion
//                + " to " + newVersion + ", which will destroy all old data");
//        db.execSQL("DROP TABLE IF EXISTS dishes");
//        onCreate(db);
//    }
//
//}
