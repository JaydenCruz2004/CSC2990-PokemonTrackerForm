package com.example.csc2990_pokemontrackerform;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DBListActivity extends AppCompatActivity {
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_list);

        // Back button setup
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // ListView setup
        ListView listView = findViewById(R.id.listView);

        Cursor cursor = getContentResolver().query(
                MyPokeContentProvider.CONTENT_URI,
                new String[]{
                        "_ID AS _id",
                        "NATIONALNUM",
                        "NAME",
                        "SPECIES",
                        "GENDER",
                        "HEIGHT_M",
                        "WEIGHT_KG",
                        "LEVEL",
                        "HP",
                        "ATTACK",
                        "DEFENSE"
                },
                null,
                null,
                MyPokeContentProvider.COL_NATNUM + " ASC"
        );


        String[] columns = {
                MyPokeContentProvider.COL_NATNUM,
                MyPokeContentProvider.COL_NAME,
                MyPokeContentProvider.COL_SPEC
        };

        int[] views = {
                R.id.rowNatNum,
                R.id.rowName,
                R.id.rowType
        };

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.row, // Use row.xml for each list item
                cursor,
                columns,
                views,
                0
        );

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor c, int columnIndex) {
                if (view.getId() == R.id.rowNatNum) {
                    ((TextView) view).setText("#" + c.getString(columnIndex));
                    return true;
                }
                return false;
            }
        });

        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.listView) {
            menu.setHeaderTitle("Options");
            menu.add(0, 1, 0, "Delete");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Cursor cursor = (Cursor) adapter.getItem(info.position);

            int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            int rowsDeleted = getContentResolver().delete(
                    MyPokeContentProvider.CONTENT_URI,
                    "_id = ?",
                    new String[]{String.valueOf(id)}
            );

            if (rowsDeleted > 0) {
                Toast.makeText(this, "Pokémon deleted", Toast.LENGTH_SHORT).show();
                Cursor newCursor = getContentResolver().query(
                        MyPokeContentProvider.CONTENT_URI,
                        null,
                        null,
                        null,
                        MyPokeContentProvider.COL_NATNUM + " ASC"
                );
                adapter.changeCursor(newCursor);
            } else {
                Toast.makeText(this, "Failed to delete Pokémon", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
