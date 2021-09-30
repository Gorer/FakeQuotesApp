package com.example.myfakequotesapp.Presentation.Repository.Room;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myfakequotesapp.Domain.Model.Quote;
import com.example.myfakequotesapp.Presentation.View.Adapters.QuoteListAdapter;
import com.example.myfakequotesapp.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
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
    }
}