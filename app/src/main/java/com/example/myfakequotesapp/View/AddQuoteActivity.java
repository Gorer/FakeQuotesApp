package com.example.myfakequotesapp.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfakequotesapp.R;
import com.squareup.picasso.Picasso;

import com.example.myfakequotesapp.ViewModel.AddQuoteViewModel;
import com.example.myfakequotesapp.databinding.ActivityAddQuoteBinding;


public class AddQuoteActivity extends AppCompatActivity {
    private AddQuoteViewModel addViewModel;
    private ActivityAddQuoteBinding binding;

    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // биндим
        binding = ActivityAddQuoteBinding.inflate(getLayoutInflater());
        // получем View из биндинга (getRoot, возвращающий корневой LinearLayout)
        // https://habr.com/ru/post/467295/
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();

        try {
            uri = Uri.parse(intent.getStringExtra("image"));
        }
        catch (NullPointerException e){

        }

        binding.addQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri != null) {
                    saveNewQuote(binding.quoteTextEdit.getText().toString(),
                            binding.nameAuthorTextEdit.getText().toString(),
                            binding.lastnameAuthorTextEdit.getText().toString(),

                            uri.toString());
                }
                else {
                    Toast.makeText(AddQuoteActivity.this,
                            "Вы ввели не все данные", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.searchFilmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddQuoteActivity.this, FilmResults.class);
                intent.putExtra("filmTitle", binding.filmTitleText.getText().toString());
                startActivity(intent);
            }
        });

        binding.imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"pickImage"),1);
            }
        });

        // VMP возвращает экземпляр класса AddAquoteViewModel, таким образом "связывая" this и AQVM
        addViewModel = new ViewModelProvider(this).get(AddQuoteViewModel.class);
    }

    private void saveNewQuote(String quoteText, String nameAuthor, String lastnameAuthor, String image){
        addViewModel.AddQuoteThroughVM(quoteText,nameAuthor,lastnameAuthor, image);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            uri = data.getData();
            Picasso.get().load(uri.toString()).into(binding.imageViewAdd);
            //binding.imageViewAdd.setImageURI(uri);
            // ContentResolver создаёт запрос в ContentProvider для установки флага доступа к объекту по uri
            getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
    }
}