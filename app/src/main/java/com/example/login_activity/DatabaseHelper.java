package com.example.login_activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "user_data.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(username text PRIMARY KEY, password text, email text)");
        db.execSQL("INSERT INTO user(username, password, email) VALUES('rasyif', '123', 'rasyif@gmail.com')");
        db.execSQL("INSERT INTO user(username, password, email) VALUES('user', '123', 'user@gmail.com')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS user");
//        onCreate(db);
    }
    public Boolean Login(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND password = ?", new String[]{username, password});
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean Register(String username, String password, String email){
        if (checkUsername(username) == false){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", username);
            contentValues.put("password", password);
            contentValues.put("email", email);
            db.insert("user", null, contentValues);
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ?", new String[]{username});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
    public Cursor getUserdata(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ?", new String[]{username});
        cursor.moveToFirst();
        return cursor;
    }
    public Boolean forgotChangepass(String username, String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND email = ?", new String[]{username, email});
        if (cursor.getCount() > 0){
            db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("password", password);
            db.update("user", contentValues, "username = ?", new String[]{username});
            return true;
        }else {
            return false;
        }
    }
    public Boolean Changepass(String username, String oldPass, String newPass){
        Boolean check = Login(username, oldPass);
        if (check == true){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("password", newPass);
            db.update("user", contentValues, "username = ?", new String[]{username});
            return true;
        }else {
            return false;
        }
    }

}
