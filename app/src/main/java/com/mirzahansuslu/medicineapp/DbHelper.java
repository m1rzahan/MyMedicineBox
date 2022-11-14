package com.mirzahansuslu.medicineapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Medicine.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "medicineList";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MEDICINE_NAME = "medicineName";
    private static final String COLUMN_MEDICINE_TYPE  = "medicineType";
    private static final String COLUMN_MEDICINE_COUNT = "medicineCount";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MEDICINE_NAME + " TEXT, " +
                COLUMN_MEDICINE_TYPE + " TEXT," +
                COLUMN_MEDICINE_COUNT + " INTEGER);";

        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    void addMedicine(String name, String type, int count) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MEDICINE_NAME,name);
        contentValues.put(COLUMN_MEDICINE_TYPE,type);
        contentValues.put(COLUMN_MEDICINE_COUNT,count);
        long result =  db.insert(TABLE_NAME,null, contentValues);

        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(context, "Added Succesfully", Toast.LENGTH_SHORT).show();
        }

    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


}
