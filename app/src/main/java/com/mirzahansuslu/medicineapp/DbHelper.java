package com.mirzahansuslu.medicineapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Medicine.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "medicine_list";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MEDICINE_NAME = "medicine_name";
    private static final String COLUMN_MEDICINE_TYPE  = "medicine_type";
    private static final String COLUMN_MEDICINE_COUNT = "medicine_count";

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


    void addMedicine(String medicineName, String medicineType, int medicineCount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("COLUMN_MEDICINE_NAME",medicineName);
        contentValues.put("COLUMN_MEDICINE_TYPE",medicineType);
        contentValues.put("COLUMN_MEDICINE_COUNT",medicineCount);
        long result =  db.insert(TABLE_NAME,null, contentValues);

        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added Succesfully", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
