package com.example.myfakequotesapp.Presentation.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfakequotesapp.Domain.Model.Quote;
import com.example.myfakequotesapp.R;

import java.util.List;

public class QuoteListAdapter extends RecyclerView.Adapter<QuoteListAdapter.QuoteViewHolder>{
    private Context context;
    List<Quote> QuoteList;

    public QuoteListAdapter(Context context){
        this.context = context;
    }

    public void setQuoteList(List<Quote> quoteList){
        this.QuoteList = quoteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.quote_list_element, parent, false);

        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        holder.quoteText.setText(this.QuoteList.get(position).quoteText);
        holder.nameAuthor.setText(this.QuoteList.get(position).nameAuthor);
        holder.lastnameAuthor.setText(this.QuoteList.get(position).lastnameAuthor);

    }

    @Override
    public int getItemCount() {
        if (QuoteList == null) {
            return 0;
        }
        else {
            return QuoteList.size();
        }
    }
    public class QuoteViewHolder extends RecyclerView.ViewHolder{

        TextView quoteText;
        TextView nameAuthor;
        TextView lastnameAuthor;
        public QuoteViewHolder(View view){
            super(view);
            quoteText = view.findViewById(R.id.quoteTextElementView);
            nameAuthor = view.findViewById(R.id.nameAuthorTextElementView);
            lastnameAuthor = view.findViewById(R.id.lastnameAuthorTextElementView);
        }
    }
}
