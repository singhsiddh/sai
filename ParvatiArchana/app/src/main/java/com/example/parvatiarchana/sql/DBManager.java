package com.example.parvatiarchana.sql;

//https://www.journaldev.com/9438/android-sqlite-database-example-tutorialn

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private SaiSQLHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new SaiSQLHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(SaiSQLHelper.SUBJECT, name);
        contentValue.put(SaiSQLHelper.DESC, desc);
        database.insert(SaiSQLHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[]{SaiSQLHelper._ID, SaiSQLHelper.SUBJECT, SaiSQLHelper.DESC};
        Cursor cursor = database.query(SaiSQLHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SaiSQLHelper.SUBJECT, name);
        contentValues.put(SaiSQLHelper.DESC, desc);
        int i = database.update(SaiSQLHelper.TABLE_NAME, contentValues, SaiSQLHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(SaiSQLHelper.TABLE_NAME, SaiSQLHelper._ID + "=" + _id, null);
    }

}

