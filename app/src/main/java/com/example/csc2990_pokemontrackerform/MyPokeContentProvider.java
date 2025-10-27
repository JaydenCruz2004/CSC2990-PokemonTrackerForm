package com.example.csc2990_pokemontrackerform;
//com.example.csc2990_pokemontrackerform

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyPokeContentProvider extends ContentProvider {
    public static final String TABLE_NAME ="PokeTable";
    public static final String DB_NAME = "PokeDB";
    public static final String COL_NATNUM = "NATIONALNUM";
    public static final String COL_NAME = "NAME";
    public static final String COL_SPEC = "SPECIES";
    public static final String COL_GENDER = "GENDER";
    public static final String COL_HEIGHT = "HEIGHT_M";
    public static final String COL_WEIGHT = "WEIGHT_KG";
    public static final String COL_LEVEL = "LEVEL";
    public static final String COL_HP = "HP";
    public static final String COL_ATTACK = "ATTACK";
    public static final String COL_DEFENSE = "DEFENSE";

    public static final Uri CONTENT_URI = Uri.parse("content://com.example.csc2990_pokemontrackerform");

    SQLiteOpenHelper mHelper;
    public final class MainDatabaseHelper extends SQLiteOpenHelper{
        public MainDatabaseHelper(Context context){
            super(context,DB_NAME,null,1);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "_ID INTEGER PRIMARY KEY, " +
                    COL_NATNUM + " INTEGER NOT NULL, " +
                    COL_NAME + " TEXT NOT NULL, " +
                    COL_SPEC + " TEXT NOT NULL, " +
                    COL_GENDER + " TEXT NOT NULL, " +
                    COL_HEIGHT + " REAL NOT NULL, " +
                    COL_WEIGHT + " REAL NOT NULL, " +
                    COL_LEVEL + " INTEGER NOT NULL, " +
                    COL_HP + " INTEGER NOT NULL, " +
                    COL_ATTACK + " INTEGER NOT NULL, " +
                    COL_DEFENSE + " INTEGER NOT NULL, " +
                    // prevent exact duplicates
                    "UNIQUE (" +
                    COL_NATNUM + ", " +
                    COL_NAME + ", " +
                    COL_SPEC + ", " +
                    COL_GENDER + ", " +
                    COL_HEIGHT + ", " +
                    COL_WEIGHT + ", " +
                    COL_LEVEL + ", " +
                    COL_HP + ", " +
                    COL_ATTACK + ", " +
                    COL_DEFENSE +
                    ") ON CONFLICT IGNORE" +
                    ");";

    public MyPokeContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}