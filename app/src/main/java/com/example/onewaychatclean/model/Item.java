package com.example.onewaychatclean.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.onewaychatclean.database.UriConverters;

@Entity
public class Item {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String time;

    @TypeConverters({UriConverters.class})
    public String path;

    public String type;

    @ColumnInfo(name = "xml_id")
    public int idOfXML;

    @ColumnInfo(name = "view_id")
    public int idOfView;

}