package com.example.myfakequotesapp.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Quote {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "quote_text")
    var quoteText: String? = null

    @ColumnInfo(name = "name_author")
    var nameAuthor: String? = null

    @ColumnInfo(name = "lastname_author")
    var lastnameAuthor: String? = null

    @ColumnInfo(name = "image")
    var image: String? = null

    @ColumnInfo(name = "title")
    var title: String? = null
}