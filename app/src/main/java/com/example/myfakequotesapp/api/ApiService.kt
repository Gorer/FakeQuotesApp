package com.example.myfakequotesapp.api

import com.example.myfakequotesapp.pojo.Example
import com.example.myfakequotesapp.pojo.Result
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{searchtitle}")
    fun getExample(@Path("searchtitle") searchTitle: String?): Call<Example?>
}