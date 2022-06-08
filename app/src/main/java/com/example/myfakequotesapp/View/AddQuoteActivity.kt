package com.example.myfakequotesapp.View

import androidx.lifecycle.LiveData
import com.example.myfakequotesapp.pojo.Example
import androidx.lifecycle.MutableLiveData
import com.example.myfakequotesapp.api.ApiFactory
import android.content.ContentValues
import com.example.myfakequotesapp.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.example.myfakequotesapp.Room.RepositoryTasks
import com.example.myfakequotesapp.Room.QuoteRepository
import android.widget.Toast
import com.example.myfakequotesapp.Model.QuoteDao
import com.example.myfakequotesapp.Model.Quote
import com.example.myfakequotesapp.Room.QuoteRoomDatabase
import androidx.room.Database
import androidx.room.RoomDatabase
import kotlin.jvm.Volatile
import androidx.room.Room
import androidx.recyclerview.widget.RecyclerView
import com.example.myfakequotesapp.View.Adapters.QuoteListAdapter.QuoteViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.myfakequotesapp.R
import android.widget.TextView
import com.example.myfakequotesapp.View.Adapters.FilmResultsAdapter.FilmResultsViewHolder
import com.example.myfakequotesapp.View.Adapters.FilmResultsAdapter.OnPosterClickListener
import com.squareup.picasso.Picasso
import androidx.appcompat.app.AppCompatActivity
import com.example.myfakequotesapp.View.Adapters.FilmResultsAdapter
import com.example.myfakequotesapp.ViewModel.FilmResultsViewModel
import android.os.Bundle
import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myfakequotesapp.View.AddQuoteActivity
import com.example.myfakequotesapp.ViewModel.QuoteListViewModel
import com.example.myfakequotesapp.View.Adapters.QuoteListAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.myfakequotesapp.View.QuoteDetailActivity
import com.example.myfakequotesapp.ViewModel.AddQuoteViewModel
import com.example.myfakequotesapp.View.FilmResults
import android.app.Activity
import android.net.Uri
import android.view.View
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Update
import androidx.lifecycle.ViewModel
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