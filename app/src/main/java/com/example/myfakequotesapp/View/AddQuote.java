package com.example.myfakequotesapp.View;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfakequotesapp.ViewModel.AddQuoteViewModel;
import com.example.myfakequotesapp.databinding.ActivityAddQuoteBinding;

public class AddQuote extends AppCompatActivity {
    private AddQuoteViewModel addViewModel;
    private ActivityAddQuoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // биндим
        binding = ActivityAddQuoteBinding.inflate(getLayoutInflater());
        // получем View из биндинга (getRoot, возвращающий корневой LinearLayout)
        // https://habr.com/ru/post/467295/
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

        // VMP возвращает экземпляр класса AddAquoteViewModel, таким образом "связывая" this и AQVM
        addViewModel = new ViewModelProvider(this).get(AddQuoteViewModel.class);
    }

    private void saveNewQuote(String quoteText, String nameAuthor, String lastnameAuthor){
        addViewModel.AddQuoteThroughVM(quoteText,nameAuthor,lastnameAuthor);
        finish();
    }
}
