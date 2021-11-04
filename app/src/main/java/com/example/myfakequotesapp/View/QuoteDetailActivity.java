package com.example.myfakequotesapp.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfakequotesapp.R;
import com.example.myfakequotesapp.ViewModel.AddQuoteViewModel;
import com.example.myfakequotesapp.ViewModel.MainViewModel;

import static android.content.ContentValues.TAG;

public class QuoteDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView author, quoteText;
    private Button share;
    private MainViewModel mainViewModel;
    private int id;
    //private Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_detail);
        Log.d(TAG,"onCreate: called");

        imageView = findViewById(R.id.imageView);
        quoteText = findViewById(R.id.textViewQuoteText);
        author = findViewById(R.id.textViewAuthor);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("id")) {
            id = intent.getIntExtra("id", -1);
        }
        else {
            finish();
        }

        id=intent.getIntExtra("id",-1);
        String nameAuthor;
        String lastnameAuthor;
        nameAuthor = intent.getStringExtra("nameAuthor");
        lastnameAuthor = intent.getStringExtra("lastnameAuthor");
        author.setText(nameAuthor + " " + lastnameAuthor);

        quoteText.setText(intent.getStringExtra("quoteText"));



        //mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        //updateClick();
        //chooseImage();

    }
}
