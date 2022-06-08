package com.example.myfakequotesapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Example {
    @SerializedName("searchType")
    @Expose
    var searchType: String? = null

    @SerializedName("expression")
    @Expose
    var expression: String? = null

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null

    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String? = null
}