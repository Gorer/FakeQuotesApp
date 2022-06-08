package com.example.myfakequotesapp.ViewModel

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
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Update
import androidx.lifecycle.ViewModel
import com.example.myfakequotesapp.Room.Repository

class QuoteListViewModel : ViewModel() {
    val getListFromViewModel: LiveData<List<Quote?>?>?
        get() = Repository.getDataBase?.getAllQuotes()

    fun deleteQuote(quote: Quote?) {
        Repository.getDataBase?.deleteQuote(quote)
    }
}