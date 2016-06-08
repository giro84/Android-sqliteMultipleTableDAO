package com.sql.music.dataBase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by giro on 28/05/16.
 */
public class ObjectsDBDAO  {

    public SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;

    public ObjectsDBDAO(Context context) {
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(mContext);
        open();
    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DataBaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }
    public SQLiteDatabase getSQliteDataBase(){

        return database;
    }
}
