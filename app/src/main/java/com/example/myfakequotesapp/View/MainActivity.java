package com.example.myfakequotesapp.View;

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
import android.view.View;
import android.widget.Button;

import com.example.myfakequotesapp.Model.Quote;
import com.example.myfakequotesapp.View.Adapters.QuoteListAdapter;
import com.example.myfakequotesapp.ViewModel.QuoteListViewModel;
import com.example.myfakequotesapp.R;
import com.example.myfakequotesapp.Room.Repository;

import java.util.List;

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

        button = findViewById(R.id.toAddQuoteButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this,
                        AddQuoteActivity.class),100);
            }
        });
        listViewModel=new ViewModelProvider(this).get(QuoteListViewModel.class);

        initRecyclerView();
        loadQuoteList();
    }
    private void loadQuoteList() {
        LiveData<List<Quote>> liveQuote;

        liveQuote = listViewModel.getListFromViewModel();
        liveQuote.observe(this, new Observer<List<Quote>>() {
            @Override
            public void onChanged(List<Quote> quotes) {
                quoteListAdapter.setQuoteList(quotes);
            }
        });

    }
    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);//декорации
        recyclerView.addItemDecoration(dividerItemDecoration);

        quoteListAdapter = new QuoteListAdapter(getApplicationContext());
        recyclerView.setAdapter(quoteListAdapter);
        listViewModel.getListFromViewModel().observe(this, new Observer<List<Quote>>() {
            @Override
            public void onChanged(List<Quote> quotes) {
                quoteListAdapter.setQuoteList(quotes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                listViewModel.deleteQuote(((QuoteListAdapter)recyclerView.getAdapter()).getQuoteList().get(position));
            }
        }).attachToRecyclerView(recyclerView);

        quoteListAdapter.setOnItemClickListener(new QuoteListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Quote quote) {
                Intent intent = new Intent(MainActivity.this, QuoteDetailActivity.class);
                intent.putExtra("id", quote.id);
                intent.putExtra("quoteText", quote.quoteText);
                intent.putExtra("nameAuthor", quote.nameAuthor);
                intent.putExtra("lastnameAuthor", quote.lastnameAuthor);
                intent.putExtra("image", quote.image);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==100){
            loadQuoteList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}