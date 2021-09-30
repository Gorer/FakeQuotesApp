package com.example.myfakequotesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private QuoteListViewModel listViewModel;
    private Button button;
    private QuoteListAdapter quoteListAdapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Repository.initDataBase(getApplication());

        button=findViewById(R.id.toAddQuoteButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddQuote.class),100);
            }
        });

        listViewModel=new ViewModelProvider(this).get(QuoteListViewModel.class);

        initRecyclerView();
        loadQuoteList();


    }
    private void loadQuoteList(){
        //Не рабочий вариант почему то
        LiveData<List<Quote>> liveQuote;

        liveQuote =listViewModel.getListFromViewModel();
        Log.d(TAG,"Переменная живойдаты для загрузки списка"+liveQuote.getValue());
        liveQuote.observe(this, new Observer<List<Quote>>() {
            @Override
            public void onChanged(List<Quote> quotes) {
                Log.d(TAG,"Переменная живойдаты после обзервера in loadQuoteList "+liveQuote.getValue());

                quoteListAdapter.setQuoteList(quotes);
            }
        });

    }
    private void initRecyclerView(){
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);//декорации
        recyclerView.addItemDecoration(dividerItemDecoration);

        quoteListAdapter =new QuoteListAdapter(getApplicationContext());
        recyclerView.setAdapter(quoteListAdapter);
        //почему то это говно заработало...(а нет,нихуя)...уже не актуально,но надо спросить у препода,здесь была ошибка нуллпоинтерэкс
        Log.d(TAG,"Переменная живойдаты in initRecycler"+listViewModel.getListFromViewModel().getValue());
        listViewModel.getListFromViewModel().observe(this, new Observer<List<Quote>>() {
            @Override
            public void onChanged(List<Quote> quotes) {
                Log.d(TAG,"Переменная живойдаты in initRecycler после обзервера "+listViewModel.getListFromViewModel().getValue());

                quoteListAdapter.setQuoteList(quotes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                listViewModel.deleteQuote(((QuoteListAdapter)recyclerView.getAdapter()).quoteList.get(position));
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==100){
            loadQuoteList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*private Button button;
    private QuoteListAdapter quoteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.toAddQuoteButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddQuote.class),100);
            }
        });

        initRecyclerView();
        loadQuoteList();

    }
    private void loadQuoteList(){
        QuoteRoomDatabase database = QuoteRoomDatabase.getInstance(this.getApplicationContext());
        List<Quote> quoteList = database.quoteDao().getAllQuote();
        quoteListAdapter.setQuoteList(quoteList);

    }
    private void initRecyclerView(){
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        quoteListAdapter = new QuoteListAdapter(getApplicationContext());
        recyclerView.setAdapter(quoteListAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==100){
            loadQuoteList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/
}