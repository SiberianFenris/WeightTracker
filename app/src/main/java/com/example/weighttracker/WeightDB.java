package com.example.weighttracker;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.os.Build;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

import java.util.List;

public class WeightDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "weight tracker.db";
    private static final int VERSION=3;
    private static WeightDB database;
    public enum SortOrder { ALPHABETIC, UPDATE_DESC, UPDATE_ASC };
    public static void initialize(Context context){
        if(database == null){
            database = new WeightDB(context);
        }
    }
    public static WeightDB getInstance() {
        if (database == null) {
            throw new IllegalStateException("Must initialize first");
        }
        return database;
    }
    private WeightDB(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final class LoginTable {
        private static final String TABLE = "logins";
        private static final String COL_ID = "_id";
        private static final String COL_USERNAME = "username";
        private static final String COL_PASSWORD = "password";
    }
    private static final class DailyTable {
        private static final String TABLE = "daily_weights";
        private static final String COL_ID = "_id";
        private static final String COL_DATE = "date";
        private static final String COL_WEIGHT = "weight";
        private static final String COL_USERNAME = "user_name"; // Add this line
    }
    private static final class GoalWeightTable {
        private static final String TABLE = "goal_weight";
        private static final String COL_ID = "_id";
        private static final String COL_GOAL = "goal";
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + LoginTable.TABLE + " (" +
                LoginTable.COL_ID + " integer primary key autoincrement, " +
                LoginTable.COL_USERNAME + " text, " +
                LoginTable.COL_PASSWORD + " text)");
        db.execSQL("create table " + DailyTable.TABLE + " (" +
                DailyTable.COL_ID + " integer primary key autoincrement, " +
                DailyTable.COL_DATE + " text, " +
                DailyTable.COL_WEIGHT + " text, " +
                DailyTable.COL_USERNAME + " string )");
        db.execSQL("create table " + GoalWeightTable.TABLE + " ( " +
                GoalWeightTable.COL_ID + " integer primary key autoincrement, " +
                GoalWeightTable.COL_GOAL + " text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 3) {

        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // enable foreign key constraints
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                // For earlier versions, execute the PRAGMA foreign_keys command
                db.execSQL("PRAGMA foreign_keys=ON;");
            }
        }
    }
    // add user function
    public boolean addUser (com.example.weighttracker.User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LoginTable.COL_USERNAME, user.getUser());
        values.put(LoginTable.COL_PASSWORD, user.getPassword());
        long id = db.insert(LoginTable.TABLE, null, values);
        user.setId(id);
        return id != -1;
    }
    // validate user
    public boolean validateUser (String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + LoginTable.TABLE + " where " + LoginTable.COL_USERNAME + " = ? and "
                + LoginTable.COL_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password});
        return cursor.moveToFirst();
    }

    public boolean checkUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(LoginTable.TABLE, new String[]{LoginTable.COL_ID, LoginTable.COL_USERNAME, LoginTable.COL_PASSWORD}, LoginTable.COL_USERNAME + "=?", new String[]{username}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // add weight
    public boolean addGoal (GoalWeight goal) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GoalWeightTable.COL_GOAL, goal.getWeight());
        long id = db.insert(GoalWeightTable.TABLE, null, values);
        return id != -1;
    }
    // update weight
    public void updateGoal(GoalWeight goal) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GoalWeightTable.COL_GOAL, goal.getWeight());
        db.update(GoalWeightTable.TABLE, values, GoalWeightTable.COL_ID +
                " = ?", new String[] { Long.toString(goal.getId())});
    }
    public GoalWeight getGoalWeight() {
        GoalWeight goalWeight = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + GoalWeightTable.TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            goalWeight = new GoalWeight(cursor.getString(1), cursor.getInt(0));
        }
        return goalWeight;
    }

    // add record
    public boolean addDailyWeight(DailyWeight daily, User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DailyTable.COL_WEIGHT, daily.getWeight());
        values.put(DailyTable.COL_DATE, daily.getDate());
        values.put(DailyTable.COL_USERNAME, user.getId()); // set foreign key to user ID
        long id = db.insert(DailyTable.TABLE, null, values);
        daily.setId(id);
        return id != -1;
    }

    // update db record
    public void updateDailyWeight(DailyWeight daily) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DailyTable.COL_WEIGHT, daily.getWeight());
        db.update(DailyTable.TABLE, values, DailyTable.COL_ID +
                " = ?", new String[] { Long.toString(daily.getId())});
    }

    public DailyWeight getSingleDailyWeight() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM dd, yyyy");
        DailyWeight dailyWeight = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + DailyTable.TABLE + " order by " + DailyTable.COL_DATE + " DESC Limit 1";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            dailyWeight = new DailyWeight();
            dailyWeight.setId(cursor.getInt(0));
            dailyWeight.setWeight(cursor.getString(1));
            dailyWeight.setWeight(cursor.getString(2));
        }
        return dailyWeight;
    }
    public List<DailyWeight> getDailyWeights() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM dd, yyyy");
        List<DailyWeight> dailyWeights = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + DailyTable.TABLE + " order by " + DailyTable.COL_DATE + " DESC";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                DailyWeight dailyWeight = new DailyWeight();
                dailyWeight.setDate(cursor.getString(1));
                dailyWeight.setWeight(cursor.getString(2));
                dailyWeights.add(dailyWeight);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dailyWeights;
    }

    // delete record from db
    public void deleteDailyWeight(DailyWeight daily) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DailyTable.TABLE, DailyTable.COL_ID +
                " = ?", new String[] { Long.toString(daily.getId()) });
    }

    public void updateDailyWeight(String date, String weight) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DailyTable.COL_WEIGHT, weight);
        db.update(DailyTable.TABLE, values, DailyTable.COL_DATE +
                " = ?", new String[] { date });
    }

    public void deleteDailyWeight(String date, String weight) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DailyTable.TABLE, DailyTable.COL_DATE +
                " = ?", new String[] { date });
    }

}