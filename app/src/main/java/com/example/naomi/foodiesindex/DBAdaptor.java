package com.example.naomi.foodiesindex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class DBAdaptor {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_UNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String TAG = "DBAdaptor";

    private static final String DATABASE_NAME = "MAPP_DB";
    private static final String DATABASE_TABLE = "users";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table Users (_id integer primary key autoincrement," +
                    "username text not null, email text not null, password text not null)";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public DBAdaptor(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS users");
        }
    }

    //open the database
    public DBAdaptor open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //close the database
    public void close() {
        DBHelper.close();
    }

    //validate user at login
    public boolean validateUser(String email, String password) {
        String[] columns = { KEY_ROWID };
        String selection = KEY_EMAIL + " = ?" + " AND " + KEY_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(DATABASE_TABLE, columns,
                selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        return cursorCount > 0;
    }

    //check if a user already has an account when registering
    public boolean checkIfUserExists(String username, String email, String password) {
        String[] columns = { KEY_ROWID };
        String selection = KEY_UNAME + " = ?" + " AND " + KEY_EMAIL + " = ?" + " AND " + KEY_PASSWORD + " = ?";
        String[] selectionArgs = {username, email, password};

        Cursor cursor = db.query(DATABASE_TABLE, columns,
                selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        return cursorCount > 0;
    }

    //insert a user into the database
    public void insertUser(String username, String email, String password) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_UNAME, username);
        initialValues.put(KEY_EMAIL, email);
        initialValues.put(KEY_PASSWORD, password);

        db.insert(DATABASE_TABLE, null, initialValues);
        db.close();
    }

    //update user password
    public void updateUser(String email, String newPW) {
        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD, newPW);

        db.update(DATABASE_TABLE, values, KEY_EMAIL + " = ?", new String[] {email});
        db.close();
    }

    /*
    //get row ID of user record
    public int getRowID(String email) throws SQLException {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID},KEY_EMAIL + "=" + email,
                        null, null, null, null, null);
        int rowId = mCursor.getInt(mCursor.getColumnIndex("_id"));
        return rowId;
    }
    */

    /*
    //retrieve all users
    public Cursor getAllUsers() {
        Cursor c = db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_UNAME, KEY_EMAIL, KEY_PASSWORD},
                null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    //retrieve a particular user
    public Cursor getUser(long id) throws SQLException {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID},KEY_ROWID + "=" + id,
                    null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //deletes a particular user
    public boolean deleteUser(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
    */
}