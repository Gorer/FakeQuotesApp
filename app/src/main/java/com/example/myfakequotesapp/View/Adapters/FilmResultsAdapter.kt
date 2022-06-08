package com.example.myfakequotesapp.View.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfakequotesapp.R
import com.example.myfakequotesapp.View.Adapters.FilmResultsAdapter.FilmResultsViewHolder
import com.example.myfakequotesapp.pojo.Result
import com.squareup.picasso.Picasso

class FilmResultsAdapter : RecyclerView.Adapter<FilmResultsViewHolder>() {
    internal var films: List<Result?>?
    var onPosterClickListener: OnPosterClickListener? = null
    private val listener: OnPosterClickListener? = null

    interface OnPosterClickListener {
        fun onPosterClick(position: Int)
    }

    @JvmName("setOnPosterClickListener1")
    fun setOnPosterClickListener(onPosterClickListener: OnPosterClickListener?) {
        this.onPosterClickListener = onPosterClickListener
    }

    fun getFilms(): List<Result?>? {
        return films
    }

    fun setFilms(films: List<Result?>?) {
        this.films = films
        //Log.d(ContentValues.TAG, "IN CONSTRUCTOR: " + films!![0].getImage())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmResultsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_results_item, parent, false)
        return FilmResultsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmResultsViewHolder, position: Int) {
        val film = films!![position]
        //Picasso.get().load(film.getImage()).into(holder.imageViewSmallPoster)
        Picasso.get().load(film?.image).into(holder.imageViewSmallPoster)
        //Log.d(ContentValues.TAG, film.getImage())
        //Log.d(ContentValues.TAG, "OnBindViewHolder: " + film.getImage())
    }

    override fun getItemCount(): Int {
        return films!!.size
    }

    inner class FilmResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewSmallPoster: ImageView

        init {
            imageViewSmallPoster = itemView.findViewById(R.id.imageViewSmallPoster)
            itemView.setOnClickListener {
                if (onPosterClickListener != null) {
                    onPosterClickListener!!.onPosterClick(adapterPosition)
                }
            }
        }
    }

    init {
        films = ArrayList()
    }
}