package com.sql.music.dataBase;

/**
 * Created by giro on 28/05/16.
 */
public interface Columns {

   //Table Artist
   String TABLE_NAME_ARTIST ="table_artist" ;
    String COLUMN_ARTIST_ID="artist_id";
    String COLUMN_ARTIST_NAME="artist_name";

   //Table album
    String COLUMN_ALBUM_ID="album_id";
    String COLUMN_ALBUM_NAME="album_name";
    String COLUMN_ALBUM_REFart_ID="referArt_id";
    String TABLE_NAME_ALBUM ="table_album" ;
}
