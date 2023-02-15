package com.example.parvatiarchana.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//https://www.tutorialspoint.com/android/android_sqlite_database.htm#
public class DBHelperSai extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ParvatiMyntra.db";
    public DBHelperSai(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    public DBHelperSai(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        creteTables(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        dropTables(sqLiteDatabase);
    }

    private void creteTables(SQLiteDatabase  db){
        db.execSQL(
                "create table contacts " +
                        "(id integer primary key, name text,phone text,email text, street text,place text)"
        );

    }
    private void dropTables(SQLiteDatabase  db){
        db.execSQL("DROP TABLE IF EXISTS contacts");
       // onCreate(db);
    }
    public SQLiteDatabase getDatabase(){
      return  this.getWritableDatabase();
    }
    public void insertData(Map<String,String> dataMap, String tableNmae){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (Map.Entry<String, String> e1 : dataMap.entrySet()) {
            String s = e1.getValue();
            for (Map.Entry<String, String> e2 : dataMap.entrySet()) {
                s = s.replace(e2.getKey(), e2.getValue().toString());
            }
            contentValues.put(e1.getKey(), s);
        }
        /*
        dataMap.forEach((key,value)->{

        });
*/ db.insert(tableNmae, null, contentValues);
      /*
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.insert("contacts", null, contentValues);
*/
    }
    
}
