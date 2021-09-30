package com.example.myfakequotesapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfakequotesapp.databinding.ActivityAddQuoteBinding;

public class AddQuote extends AppCompatActivity {
    private AddQuoteViewModel addViewModel;
    private ActivityAddQuoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        addViewModel = new ViewModelProvider(this).get(AddQuoteViewModel.class);
    }

    private void saveNewQuote(String quoteText, String nameAuthor, String lastnameAuthor){
        addViewModel.AddQuoteThroughVM(quoteText,nameAuthor,lastnameAuthor);
        finish();
    }
}
