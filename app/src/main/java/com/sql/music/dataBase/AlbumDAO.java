package com.sql.music.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;

import com.sql.music.model.Album;
import com.sql.music.model.Artist;

import java.util.ArrayList;

/**
 * Created by giro on 28/05/16.
 */
public class AlbumDAO extends ObjectsDBDAO{


    public static final String MEASURE_ID_WITH_PREFIX = "table_id";

    public AlbumDAO(Context context) {
        super(context);
    }

       public long addAlbum(Album a){


        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.COLUMN_ALBUM_NAME,a.getName());
        contentValues.put(Columns.COLUMN_ALBUM_REFart_ID, a.getComposer().get_id());

        return database.insert(Columns.TABLE_NAME_ALBUM,null,contentValues);
    }


    /**
     * GetAll Artist's albums
     * @param idArtist
     *
     * @return
     */

    public ArrayList<Album> getAlbumWithIDArtist(int idArtist) {

        ArrayList<Album> Albums = new ArrayList<Album>();

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder
                .setTables(Columns.TABLE_NAME_ALBUM
                        + " INNER JOIN "
                        + Columns.TABLE_NAME_ARTIST
                        + " ON "
                        + Columns.COLUMN_ALBUM_REFart_ID
                        + " = "
                        + (Columns.TABLE_NAME_ARTIST + "." + Columns.COLUMN_ARTIST_ID));


       /*
       public Cursor getAccount(long id){
  SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
  qb.setTables(ACCOUNT_TABLE_NAME);
  String orderBy=Account.DEFAULT_SORT_ORDER;
  String where=Account._ID + " = " + id;
  openDataBase();
  Cursor c=qb.query(database,null,where,null,null,null,orderBy);
  return c;
}
String where=Account._ID + " = " + id;
       */

        // Get cursor

        String where=Columns.COLUMN_ALBUM_REFart_ID+ " = " + idArtist ;

        Cursor cursor = queryBuilder.query(database, new String[] {
                        MEASURE_ID_WITH_PREFIX,
                        Columns.TABLE_NAME_ALBUM + "."
                                + Columns.COLUMN_ALBUM_ID,
                        Columns.COLUMN_ALBUM_NAME,

                        Columns.TABLE_NAME_ARTIST + "."
                                + Columns.COLUMN_ARTIST_ID }, where, null, null, null,
                null);


        while (cursor.moveToNext()) {
            Album a = new Album();
            a.set_id(cursor.getInt(0));
            a.setName(cursor.getString(1));
            Artist ar = new Artist();
            ar.setNome(cursor.getString(2));
            a.setComposer(ar);
           Albums.add(a);
        }
        return Albums;
    }


}
