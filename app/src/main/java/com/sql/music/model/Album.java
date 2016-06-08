package com.sql.music.model;

/**
 * Created by giro on 28/05/16.
 */
public class Album {

    private int _id;
    private String name;
    private Artist a;


    public Album(int id,Artist a){
        this._id=id;
        this.a=a;
    }

    public Album() {

    }

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public Artist getComposer() {
        return a;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComposer(Artist a) {
        this.a = a;
    }


}
