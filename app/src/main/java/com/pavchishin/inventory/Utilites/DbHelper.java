package com.pavchishin.inventory.Utilites;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Inventory.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DbEntry.FieldEntry.TABLE_NAME + " (" +
                    DbEntry.FieldEntry._ID + " INTEGER PRIMARY KEY," +
                    DbEntry.FieldEntry.COLUMN_PART_NUMBER + " TEXT," +
                    DbEntry.FieldEntry.COLUMN_PART_NAME + " TEXT," +
                    DbEntry.FieldEntry.COLUMN_PART_LOCATION + " TEXT," +
                    DbEntry.FieldEntry.COLUMN_PART_EXIST + " REAL," +
                    DbEntry.FieldEntry.COLUMN_PART_FACT + " REAL)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DbEntry.FieldEntry.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }


}
