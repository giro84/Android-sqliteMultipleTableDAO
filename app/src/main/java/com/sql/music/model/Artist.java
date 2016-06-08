package com.sql.music.model;

/**
 * Created by giro on 28/05/16.
 */
public class Artist   {


    private int _id;
    private String name;



    public Artist(int id,String name){
        this._id=id;
        this.name=name;
    }

    public Artist() {

    }

    public int get_id() {
        return _id;
    }

    public String getNome() {
        return name;
    }

    public void set_id(int _id) {
        this._id = _id;
    }


    public void setNome(String name) {
        this.name = name;
    }
}
