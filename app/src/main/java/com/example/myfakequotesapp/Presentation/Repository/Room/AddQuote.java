package com.example.myfakequotesapp.Presentation.Repository.Room;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfakequotesapp.Domain.Model.Quote;
import com.example.myfakequotesapp.Presentation.Repository.Room.QuoteRoomDatabase;
import com.example.myfakequotesapp.databinding.ActivityAddQuoteBinding;

public class AddQuote extends AppCompatActivity {
    private ActivityAddQuoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_new_quote);

        binding = ActivityAddQuoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.addQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewQuote(binding.quoteTextEdit.getText().toString(),
                        binding.nameAuthorTextEdit.getText().toString(),
                        binding.lastnameAuthorTextEdit.getText().toString());
            }
        });

    }

    private void saveNewQuote(String quoteText, String nameAuthor, String lastnameAuthor){
        QuoteRoomDatabase database = QuoteRoomDatabase.getInstance(this.getApplicationContext());
        Quote quote = new Quote();
        quote.quoteText = quoteText;
        quote.nameAuthor = nameAuthor;
        quote.lastnameAuthor = lastnameAuthor;
        database.quoteDao().insertQuote(quote);

        finish();
    }
}
