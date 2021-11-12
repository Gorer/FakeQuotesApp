package com.example.myfakequotesapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfakequotesapp.Model.Quote;
import com.example.myfakequotesapp.R;
import com.example.myfakequotesapp.View.Adapters.FilmResultsAdapter;
import com.example.myfakequotesapp.ViewModel.FilmResultsViewModel;
import com.example.myfakequotesapp.api.ApiFactory;
import com.example.myfakequotesapp.api.ApiService;
import com.example.myfakequotesapp.databinding.ActivityFilmResultsBinding;
import com.example.myfakequotesapp.pojo.Example;
import com.example.myfakequotesapp.pojo.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class FilmResults extends AppCompatActivity {
    private ActivityFilmResultsBinding binding;
    private String filmTitle;
    private FilmResultsAdapter adapter;
    private RecyclerView recyclerViewPosters;
    private FilmResultsViewModel filmResultsViewModel;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // биндим и размещаем интерфейс в активити
        binding = ActivityFilmResultsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        filmTitle = intent.getStringExtra("filmTitle");

        initRecyclerView();
    }

    private void initRecyclerView(){
        recyclerViewPosters = findViewById(R.id.recyclerViewPosters);
        recyclerViewPosters.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new FilmResultsAdapter();
        recyclerViewPosters.setAdapter(adapter);
        filmResultsViewModel = new FilmResultsViewModel();

        filmResultsViewModel.getExample(filmTitle).observe(this, new Observer<Example>() {
            @Override
            public void onChanged(Example example) {
                adapter.setFilms(example.getResults());
            }
        });
    }
}
