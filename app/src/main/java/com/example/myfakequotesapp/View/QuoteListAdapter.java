package com.example.myfakequotesapp.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfakequotesapp.Model.Quote;
import com.example.myfakequotesapp.R;

import java.util.List;

public class QuoteListAdapter extends RecyclerView.Adapter<QuoteListAdapter.QuoteViewHolder>{
    private Context context;
    List<Quote> quoteList;
    private OnItemClickListener listener;

    public QuoteListAdapter(Context context){
        this.context = context;
    }

    public void setQuoteList(List<Quote> quoteList){
        this.quoteList = quoteList;
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
        holder.quoteText.setText(this.quoteList.get(position).quoteText);
        holder.nameAuthor.setText(this.quoteList.get(position).nameAuthor);
        holder.lastnameAuthor.setText(this.quoteList.get(position).lastnameAuthor);

    }

    @Override
    public int getItemCount() {
        if (quoteList == null) {
            return 0;
        }
        else {
            return quoteList.size();
        }
    }
    public class QuoteViewHolder extends RecyclerView.ViewHolder{

        TextView quoteText;
        TextView nameAuthor;
        TextView lastnameAuthor;
        OnItemClickListener onItemClickListener;
        public QuoteViewHolder(View view){
            super(view);
            quoteText = view.findViewById(R.id.quoteTextElementView);
            nameAuthor = view.findViewById(R.id.nameAuthorTextElementView);
            lastnameAuthor = view.findViewById(R.id.lastnameAuthorTextElementView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if(listener!=null && position!=RecyclerView.NO_POSITION){
                        listener.onItemClick(quoteList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Quote quote);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;

    }
}
