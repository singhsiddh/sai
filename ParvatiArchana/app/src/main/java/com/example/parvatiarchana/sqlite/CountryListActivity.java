package com.example.parvatiarchana.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
//import android.support.v4.widget.SimpleCursorAdapter;
//import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.FragmentActivity;

import com.example.parvatiarchana.R;

public class CountryListActivity extends /*ActionBarActivity */FragmentActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{DatabaseHelper._ID,
            DatabaseHelper.SUBJECT, DatabaseHelper.DESC};

    final int[] to = new int[]{R.id.id, R.id.title, R.id.desc};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_emp_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);
                TextView descTextView = (TextView) view.findViewById(R.id.desc);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyCountryActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddCountryActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {

        // Table Name
        public static final String TABLE_NAME = "COUNTRIES";

        // Table columns
        public static final String _ID = "_id";
        public static final String SUBJECT = "subject";
        public static final String DESC = "description";

        // Database Information
        static final String DB_NAME = "JOURNALDEV_COUNTRIES.DB";

        // database version
        static final int DB_VERSION = 1;

        // Creating table query
        private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT NOT NULL, " + DESC + " TEXT);";

        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

        public static class DBManager {

            private DatabaseHelper dbHelper;

            private Context context;

            private SQLiteDatabase database;

            public DBManager(Context c) {
                context = c;
            }

            public DBManager open() throws SQLException {
                dbHelper = new DatabaseHelper(context);
                database = dbHelper.getWritableDatabase();
                return this;
            }

            public void close() {
                dbHelper.close();
            }

            public void insert(String name, String desc) {
                ContentValues contentValue = new ContentValues();
                contentValue.put(SUBJECT, name);
                contentValue.put(DESC, desc);
                database.insert(TABLE_NAME, null, contentValue);
            }

            public Cursor fetch() {
                String[] columns = new String[]{_ID, SUBJECT, DESC};
                Cursor cursor = database.query(TABLE_NAME, columns, null, null, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                }
                return cursor;
            }

            public int update(long _id, String name, String desc) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(SUBJECT, name);
                contentValues.put(DESC, desc);
                int i = database.update(TABLE_NAME, contentValues, _ID + " = " + _id, null);
                return i;
            }

            public void delete(long _id) {
                database.delete(TABLE_NAME, _ID + "=" + _id, null);
            }

            public static class ModifyCountryActivity extends Activity implements View.OnClickListener {

                private EditText titleText;
                private Button updateBtn, deleteBtn;
                private EditText descText;

                private long _id;

                private DBManager dbManager;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);

                    setTitle("Modify Record");

                    setContentView(R.layout.activity_modify_record);

                    dbManager = new DBManager(this);
                    dbManager.open();

                    titleText = (EditText) findViewById(R.id.subject_edittext);
                    descText = (EditText) findViewById(R.id.description_edittext);

                    updateBtn = (Button) findViewById(R.id.btn_update);
                    deleteBtn = (Button) findViewById(R.id.btn_delete);

                    Intent intent = getIntent();
                    String id = intent.getStringExtra("id");
                    String name = intent.getStringExtra("title");
                    String desc = intent.getStringExtra("desc");

                    _id = Long.parseLong(id);

                    titleText.setText(name);
                    descText.setText(desc);

                    updateBtn.setOnClickListener(this);
                    deleteBtn.setOnClickListener(this);
                }

                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.btn_update:
                            String title = titleText.getText().toString();
                            String desc = descText.getText().toString();

                            dbManager.update(_id, title, desc);
                            this.returnHome();
                            break;

                        case R.id.btn_delete:
                            dbManager.delete(_id);
                            this.returnHome();
                            break;
                    }
                }

                public void returnHome() {
                    Intent home_intent = new Intent(getApplicationContext(), CountryListActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(home_intent);
                }
            }
        }
    }
}