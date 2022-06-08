package com.example.myfakequotesapp.View

import android.content.ContentValues
import com.example.myfakequotesapp.R
import android.widget.TextView
import com.squareup.picasso.Picasso
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import java.lang.NullPointerException

class QuoteDetailActivity : AppCompatActivity() {
    private var imageView: ImageView? = null
    private var author: TextView? = null
    private var quoteText: TextView? = null
    private var title: TextView? = null
    private val share: Button? = null
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_detail)
        Log.d(ContentValues.TAG, "onCreate: called")
        imageView = findViewById(R.id.imageViewDetail)
        quoteText = findViewById(R.id.textViewQuoteText)
        author = findViewById(R.id.textViewAuthor)
        title = findViewById(R.id.textViewTitleText)
        //Получаем данные из Intent'а и проверяем на наличие id
        val intent = intent
        if (intent != null && intent.hasExtra("id")) {
            id = intent.getIntExtra("id", -1)
        } else {
            finish()
        }

        //
        id = intent!!.getIntExtra("id", -1)
        val nameAuthor: String?
        val lastnameAuthor: String?
        nameAuthor = intent.getStringExtra("nameAuthor")
        lastnameAuthor = intent.getStringExtra("lastnameAuthor")
        author?.setText("$nameAuthor $lastnameAuthor")
        quoteText?.setText(intent.getStringExtra("quoteText"))
        title?.setText(intent.getStringExtra("title"))
        try {
            Picasso.get().load(intent.getStringExtra("image")).into(imageView)
            //imageView.setImageURI(Uri.parse(intent.getStringExtra("image")));
        } catch (e: NullPointerException) {
        }
    }
}