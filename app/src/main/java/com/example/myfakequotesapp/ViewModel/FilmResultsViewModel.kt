package com.example.myfakequotesapp.ViewModel

import androidx.lifecycle.LiveData
import com.example.myfakequotesapp.pojo.Example
import com.example.myfakequotesapp.api.ApiFactory
import androidx.lifecycle.ViewModel

class FilmResultsViewModel : ViewModel() {
    fun getExample(searchTitle: String?): LiveData<Example?> {
        return ApiFactory.Companion.instance!!.getExample(searchTitle)

    //.getExample(searchTitle)
    }
}