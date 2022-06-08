package com.example.myfakequotesapp.View

import android.widget.Toast
import com.squareup.picasso.Picasso
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.example.myfakequotesapp.ViewModel.AddQuoteViewModel
import android.net.Uri
import android.view.View
import com.example.myfakequotesapp.databinding.ActivityAddQuoteBinding
import java.lang.NullPointerException

class AddQuoteActivity : AppCompatActivity() {
    private var addViewModel: AddQuoteViewModel? = null
    private var binding: ActivityAddQuoteBinding? = null
    private var uri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // биндим
        binding = ActivityAddQuoteBinding.inflate(
            layoutInflater
        )
        // получем View из биндинга (getRoot, возвращающий корневой LinearLayout)
        // https://habr.com/ru/post/467295/
        val view: View = binding!!.root
        setContentView(view)
        val intent = intent
        binding!!.filmTitleText.setText(intent.getStringExtra("title"))
        try {
            uri = Uri.parse(intent.getStringExtra("image"))
        } catch (e: NullPointerException) {
        }
        binding!!.addQuoteButton.setOnClickListener {
            if (uri != null) {
                saveNewQuote(
                    binding!!.quoteTextEdit.text.toString(),
                    binding!!.nameAuthorTextEdit.text.toString(),
                    binding!!.lastnameAuthorTextEdit.text.toString(),
                    uri.toString(),
                    binding!!.filmTitleText.text.toString()
                )
            } else {
                Toast.makeText(
                    this@AddQuoteActivity,
                    "Вы ввели не все данные", Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding!!.searchFilmButton.setOnClickListener {
            val intent = Intent(this@AddQuoteActivity, FilmResults::class.java)
            intent.putExtra("filmTitle", binding!!.filmTitleText.text.toString())
            startActivity(intent)
        }
        binding!!.imageViewAdd.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "pickImage"), 1)
        }

        // VMP возвращает экземпляр класса AddAquoteViewModel, таким образом "связывая" this и AQVM
        addViewModel = ViewModelProvider(this).get(AddQuoteViewModel::class.java)
    }

    private fun saveNewQuote(
        quoteText: String,
        nameAuthor: String,
        lastnameAuthor: String,
        image: String,
        title: String
    ) {
        addViewModel!!.AddQuoteThroughVM(quoteText, nameAuthor, lastnameAuthor, image, title)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1) {
            uri = data!!.data
            Picasso.get().load(uri.toString()).into(binding!!.imageViewAdd)
            //binding.imageViewAdd.setImageURI(uri);
            // ContentResolver создаёт запрос в ContentProvider для установки флага доступа к объекту по uri
            contentResolver.takePersistableUriPermission(
                uri!!,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
    }
}