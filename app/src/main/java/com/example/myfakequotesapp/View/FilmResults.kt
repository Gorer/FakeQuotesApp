package com.example.myfakequotesapp.View

import androidx.recyclerview.widget.RecyclerView
import com.example.myfakequotesapp.R
import com.example.myfakequotesapp.View.Adapters.FilmResultsAdapter.OnPosterClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.myfakequotesapp.View.Adapters.FilmResultsAdapter
import com.example.myfakequotesapp.ViewModel.FilmResultsViewModel
import android.os.Bundle
import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import android.view.View
import com.example.myfakequotesapp.databinding.ActivityFilmResultsBinding

class FilmResults : AppCompatActivity() {
    private var binding: ActivityFilmResultsBinding? = null
    private var filmTitle: String? = null
    private var adapter: FilmResultsAdapter? = null
    private var recyclerViewPosters: RecyclerView? = null
    private var filmResultsViewModel: FilmResultsViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // биндим и размещаем интерфейс в активити
        binding = ActivityFilmResultsBinding.inflate(
            layoutInflater
        )
        val view: View = binding!!.root
        setContentView(view)
        val intent = intent
        filmTitle = intent.getStringExtra("filmTitle")
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerViewPosters = findViewById(R.id.recyclerViewPosters)
        recyclerViewPosters?.setLayoutManager(GridLayoutManager(this, 2))
        adapter = FilmResultsAdapter()
        recyclerViewPosters?.setAdapter(adapter)
        filmResultsViewModel = FilmResultsViewModel()
        filmResultsViewModel!!.getExample(filmTitle)
            .observe(this) { example -> adapter!!.setFilms(example?.results) }
        adapter?.setOnPosterClickListener(object : OnPosterClickListener {
            override fun onPosterClick(position: Int) {
                val film = adapter!!.getFilms()!!.get(position)
                val intent = Intent(this@FilmResults, AddQuoteActivity::class.java)
                intent.putExtra("title", film?.title)
                startActivity(intent)
            }
        })
    }
}