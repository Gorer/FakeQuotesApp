package com.example.myfakequotesapp.api

import android.app.DownloadManager
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myfakequotesapp.BuildConfig
import com.example.myfakequotesapp.pojo.Example
import com.example.myfakequotesapp.pojo.Result
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.stream.Collectors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.ContentValues.TAG


//import com.example.myfakequotesapp.BuildConfig;
class ApiFactory private constructor() {
    fun getExample(searchTitle: String?): LiveData<Example?> {
        val liveExamplesData = MutableLiveData<Example?>()
        //List
        apiFactory!!.apiService.getExample(searchTitle).enqueue(object : Callback<Example?> {
            override fun onResponse(call: Call<Example?>, response: Response<Example?>) {
                if (response.isSuccessful && response.body() != null) {
                    liveExamplesData.value = response.body()
                    Log.d(ContentValues.TAG, "INFO: $response")
                }
            }

            override fun onFailure(call: Call<Example?>, t: Throwable) {
                Log.d(ContentValues.TAG, "ERROR: " + t.message)
            }
        })
        return liveExamplesData
    }

    val apiService: ApiService
        get() = retrofit.create(ApiService::class.java)

    companion object {
        private var apiFactory: ApiFactory? = null
        private lateinit var retrofit: Retrofit
        private const val BASE_URL = "https://imdb-api.com/en/API/SearchTitle/k_w1rp6vvu/"
        val instance: ApiFactory?
            get() {
                if (apiFactory == null) {
                    apiFactory = ApiFactory()
                }
                return apiFactory
            }
    }

    init {
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
}