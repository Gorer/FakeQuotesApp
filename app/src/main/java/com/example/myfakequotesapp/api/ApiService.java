package com.example.myfakequotesapp.api;

import com.example.myfakequotesapp.pojo.Example;
import com.example.myfakequotesapp.pojo.Result;

import java.util.List;
import io.reactivex.Observable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    //@GET()
    @GET("{searchtitle}")
    Call<Example> getExample(@Path("searchtitle") String searchTitle);
    //Observable<Example> getExample(@Path("searchtitle") String searchTitle);
    //Observable<List<Result>> getResults();
}
