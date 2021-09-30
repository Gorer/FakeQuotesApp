package com.example.myfakequotesapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Quote {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name="quote_text")
    public String quoteText;
    @ColumnInfo(name = "name_author")
    public String nameAuthor;
    @ColumnInfo(name = "lastname_author")
    public String lastnameAuthor;
}