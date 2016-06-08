package com.sql.music.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.sql.music.model.Artist;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by giro on 28/05/16.
 */
public class ArtistDAO  extends  ObjectsDBDAO {

    public static final String MEASURE_ID_WITH_PREFIX = "table_id";

    private static final String WHERE_ID_EQUALS =
            Columns.COLUMN_ARTIST_ID + " =?";


    public ArtistDAO(Context context) {
        super(context);
    }


    public long addArtist(Artist a) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.COLUMN_ARTIST_NAME, a.getNome());
        return database.insert(Columns.TABLE_NAME_ARTIST, null,
                contentValues);

    }

    public long update(Artist a) {
        ContentValues values = new ContentValues();
        values.put(Columns.COLUMN_ARTIST_NAME, a.getNome());
          long result = database.update(Columns.TABLE_NAME_ARTIST, values,
                WHERE_ID_EQUALS,
                new String[]{String.valueOf(a.get_id())});
     //   Log.d("Update Result:", "=" + result);
        return result;

    }

    public int delete(Artist a) {

        return database.delete(Columns.TABLE_NAME_ARTIST, WHERE_ID_EQUALS,
                new String[]{a.get_id() + ""});
    }



    public ArrayList<Artist> getArtists() {
        ArrayList<Artist> artists = new ArrayList<Artist>();

        Cursor cursor = database.query(Columns.TABLE_NAME_ARTIST,
                new String[] { Columns.COLUMN_ARTIST_ID,
                       Columns.COLUMN_ARTIST_NAME
                                          }, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            Artist artist = new Artist();
            artist.set_id(cursor.getInt(0));
            artist.setNome(cursor.getString(1));
            artists.add(artist);
        }
        return artists;
    }

}
