package com.example.csc2990_pokemontrackerform;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DBListActivity extends Activity {

    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_list);

        listView = findViewById(R.id.listView);


        Uri uri = Uri.parse("content://com.example.csc2990_pokemontrackerform");

        Cursor cursor = getContentResolver().query(
                uri,
                null,
                null,
                null,
                null
        );

        // Ensure _id column exists for SimpleCursorAdapter
        if (cursor == null) return;

        // Map columns from database → TextViews in row.xml
        String[] from = {
                MyPokeContentProvider.COL_NATNUM,
                MyPokeContentProvider.COL_NAME,
                MyPokeContentProvider.COL_SPEC
        };

        int[] to = {
                R.id.rowNatNum,
                R.id.rowName,
                R.id.rowType
        };

        // Create adapter to fill rows
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.row,
                cursor,
                from,
                to,
                0
        );

        listView.setAdapter(adapter);
    }
}
