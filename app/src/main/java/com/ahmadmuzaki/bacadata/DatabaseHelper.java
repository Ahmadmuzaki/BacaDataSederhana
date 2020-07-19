package com.ahmadmuzaki.bacadata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    final public static String NAMA_DATABASE = "apotek24.db";

    final public static int VERSION = 1;

    public DatabaseHelper(Context context) {

        super(context, NAMA_DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE user (Kode_member INTEGER PRIMARY KEY AUTOINCREMENT, Nama TEXT, Tanggal_lahir Text, Alamat Text, Jenis_kelamin Text, Username Text UNIQUE, Password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");

        onCreate(sqLiteDatabase);

    }

    //Membuat Insert Data pada Database

    public boolean insertData(String nama, String tanggal_lahir, String alamat, String jenis_kelamin, String username, String password){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Nama", nama );
        contentValues.put("Tanggal_lahir", tanggal_lahir );
        contentValues.put("Alamat", alamat);
        contentValues.put("Jenis_kelamin", jenis_kelamin );
        contentValues.put("Username", username );
        contentValues.put("Password", password );

        long result = sqLiteDatabase.insert("user", null, contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }

    }

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM user", null);
        return result;
    }
}
