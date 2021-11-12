package com.example.myfakequotesapp.View.Adapters;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfakequotesapp.Model.Quote;
import com.example.myfakequotesapp.R;
import com.example.myfakequotesapp.pojo.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FilmResultsAdapter extends RecyclerView.Adapter<FilmResultsAdapter.FilmResultsViewHolder> {
    public OnPosterClickListener getOnPosterClickListener() {
        return onPosterClickListener;
    }

    private List<Result> films;
    private OnPosterClickListener onPosterClickListener;
    private FilmResultsAdapter.OnPosterClickListener listener;

    public FilmResultsAdapter() {
        this.films = new ArrayList<>();
    }

    public interface OnPosterClickListener {
        void onPosterClick(int position);
    }

    public void setOnPosterClickListener(OnPosterClickListener onPosterClickListener) {
        this.onPosterClickListener = onPosterClickListener;
    }

    public List<Result> getFilms() {
        return films;
    }

    public void setFilms(List<Result> films) {
        this.films = films;
        Log.d(TAG,"IN CONSTRUCTOR: "+films.get(0).getImage());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmResultsAdapter.FilmResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_results_item, parent, false);
        return new FilmResultsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmResultsViewHolder holder, int position) {

        Result film = films.get(position);
        Picasso.get().load(film.getImage()).into(holder.imageViewSmallPoster);
        Log.d(TAG, film.getImage());
        Log.d(TAG,"OnBindViewHolder: " + film.getImage());
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    class FilmResultsViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewSmallPoster;

        public FilmResultsViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewSmallPoster = itemView.findViewById(R.id.imageViewSmallPoster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onPosterClickListener != null) {
                        onPosterClickListener.onPosterClick(getAdapterPosition());
                    }
                }
            });
        }
    }


}
