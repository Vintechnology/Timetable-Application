package com.example.android.androiddev;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.androiddev.Day_fragments.FridayFragment;
import com.example.android.androiddev.Day_fragments.MondayFragment;
import com.example.android.androiddev.Day_fragments.ThursdayFragment;
import com.example.android.androiddev.Day_fragments.TuesdayFragment;
import com.example.android.androiddev.Day_fragments.WednesdayFragment;

import java.util.ArrayList;

/**
 * Created by Admin on 4/3/2017.
 */

public class SubjectDatabaseControl extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "MyTimetable.db";
    private static final String KEY_ID = "id";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_BEGIN_TIME = "begin_tine";
    private static final String KEY_END_TIME = "end_time";
    private static final String KEY_TIME_IN_DAY = "time_in_day";
    public static final String TIME_IN_DAY_MORNING = "morning";
    public static final String TIME_IN_DAY_AFTERNOON = "afternoon";
    private final String TABLE_NAME;
    private static final String[] ALL_TABLE_NAME =
            {MondayFragment.TABLE_NAME,
                    TuesdayFragment.TABLE_NAME,
                    WednesdayFragment.TABLE_NAME,
                    ThursdayFragment.TABLE_NAME,
                    FridayFragment.TABLE_NAME};

    public SubjectDatabaseControl(Context context, String TABLE_NAME) {
        super(context, DATABASE_NAME, null, VERSION);
        this.TABLE_NAME = TABLE_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (String TABLE_NAME : ALL_TABLE_NAME) {
            String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_SUBJECT + " TEXT," + KEY_BEGIN_TIME + " TEXT,"
                    + KEY_END_TIME + " TEXT," + KEY_TIME_IN_DAY + " TEXT )";

            sqLiteDatabase.execSQL(CREATE_TABLE);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Subject addSubject(Subject o, String timeInDay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUBJECT, o.getName());
        values.put(KEY_BEGIN_TIME, o.getTimeFrom());
        values.put(KEY_END_TIME, o.getTimeTo());
        values.put(KEY_TIME_IN_DAY, timeInDay);


        int id = (int) db.insert(TABLE_NAME, null, values);
        db.close();
        o.setId(id);
        return o;
    }

    public void addSubject(String name,String timeFrom,String timeTo, String timeInDay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUBJECT, name);
        values.put(KEY_BEGIN_TIME, timeFrom);
        values.put(KEY_END_TIME, timeTo);
        values.put(KEY_TIME_IN_DAY, timeInDay);


        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteSubject(Subject o) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(o.getId())});
        db.close();
    }


    public Subject updateSubject(Subject o) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUBJECT, o.getName());
        values.put(KEY_BEGIN_TIME, o.getTimeFrom());
        values.put(KEY_END_TIME, o.getTimeTo());
        db.update(TABLE_NAME, values, KEY_ID + "=?", new String[]{String.valueOf(o.getId())});
        db.close();
        return o;
    }

    public ArrayList<Subject> getArrayListOfSubject(String timeInDay) {
        ArrayList<Subject> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, new String[]{KEY_SUBJECT, KEY_BEGIN_TIME, KEY_END_TIME, KEY_ID}, KEY_TIME_IN_DAY + "=?", new String[]{timeInDay}, null, null, null);
        if (c.moveToFirst()) {
            do {
                list.add(new Subject(c.getString(0), c.getString(1), c.getString(2), Integer.parseInt(c.getString(3))));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public Subject getSubject(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{KEY_SUBJECT, KEY_BEGIN_TIME, KEY_END_TIME}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        Subject subject = null;
        if (c.moveToFirst()) {
            subject = new Subject(c.getString(0), c.getString(1), c.getString(2));
        }
        c.close();
        return subject;
    }


}
