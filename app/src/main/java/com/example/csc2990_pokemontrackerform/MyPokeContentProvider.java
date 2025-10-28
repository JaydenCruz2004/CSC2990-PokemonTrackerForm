package com.example.csc2990_pokemontrackerform;
//com.example.csc2990_pokemontrackerform

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.NonNull;

public class MyPokeContentProvider extends ContentProvider {
    public static final String TABLE_NAME = "PokeTable";
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

    public static final Uri CONTENT_URI =
            Uri.parse("content://com.example.csc2990_pokemontrackerform");

    MainDatabaseHelper mHelper;

    public final class MainDatabaseHelper extends SQLiteOpenHelper {
        public MainDatabaseHelper(Context context) {
            super(context, DB_NAME, null, 1);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
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
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int rows = db.delete(TABLE_NAME, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return rows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        String nationalNum = values.getAsString(COL_NATNUM);
        String name = values.getAsString(COL_NAME);
        String species = values.getAsString(COL_SPEC);
        String gender = values.getAsString(COL_GENDER);
        String height = values.getAsString(COL_HEIGHT);
        String weight = values.getAsString(COL_WEIGHT);
        String level = values.getAsString(COL_LEVEL);
        String hp = values.getAsString(COL_HP);
        String attack = values.getAsString(COL_ATTACK);
        String defense = values.getAsString(COL_DEFENSE);

        String[] required = {
                COL_NATNUM,
                COL_NAME,
                COL_SPEC,
                COL_GENDER,
                COL_HEIGHT,
                COL_WEIGHT,
                COL_LEVEL,
                COL_HP,
                COL_ATTACK,
                COL_DEFENSE
        };

        for (String col : required) {
            String val = values.getAsString(col);
            if (val == null || val.trim().isEmpty()) {
                throw new IllegalArgumentException("Missing or empty value for: " + col);
            }
        }


        mHelper.getWritableDatabase().insert(TABLE_NAME, null, values);
        return uri;

    }

    @Override
    public boolean onCreate() {
        mHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        return mHelper.getReadableDatabase().query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}