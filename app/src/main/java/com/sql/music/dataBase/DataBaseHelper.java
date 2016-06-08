package com.sql.music.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by giro on 28/05/16.
 *
 * Create Tables and operations of open, close  of DB *
 *
 */
public class DataBaseHelper   extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME= "music_data";
    private static DataBaseHelper instance;

     //Create table  artist
    public static final String CREATE_ARTIST_TABLE= " CREATE TABLE "
            + Columns.TABLE_NAME_ARTIST + "( "
            + Columns.COLUMN_ARTIST_ID + " INTEGER PRIMARY KEY , "
            + Columns.COLUMN_ARTIST_NAME + " TEXT  " + " ) ";

    //Create table  album
    public static final String CREATE_ALBUM_TABLE= "CREATE TABLE "
            + Columns.TABLE_NAME_ALBUM + "("
            + Columns.COLUMN_ALBUM_ID + "INTEGER PRIMARY KEY, "
            + Columns.COLUMN_ALBUM_NAME + " TEXT , "
            + Columns.COLUMN_ALBUM_REFart_ID + " INT , "
            + "FOREIGN KEY(" + Columns.COLUMN_ALBUM_REFart_ID + ") REFERENCES "
            + Columns.TABLE_NAME_ARTIST + "(artist_id) " + ")";




    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DataBaseHelper(context);
        return instance;
       }


    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ARTIST_TABLE);
        db.execSQL(CREATE_ALBUM_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
