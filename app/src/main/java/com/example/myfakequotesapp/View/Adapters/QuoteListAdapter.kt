package com.example.myfakequotesapp.View.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfakequotesapp.Model.Quote
import com.example.myfakequotesapp.R
import com.example.myfakequotesapp.View.Adapters.QuoteListAdapter.QuoteViewHolder

class QuoteListAdapter(private val context: Context) : RecyclerView.Adapter<QuoteViewHolder>() {
    //var quoteList: List<Quote?>? = null
    internal var quoteList: List<Quote?>? = null
    private var listener: OnItemClickListener? = null
    fun getQuoteList(): List<Quote?>? {
        return quoteList
    }

    fun setQuoteList(quoteList: List<Quote?>?) {
        this.quoteList = quoteList as MutableList<Quote?>?
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quote_list_element, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.quoteText.text = quoteList!![position]!!.quoteText
        holder.nameAuthor.text = quoteList!![position]!!.nameAuthor
        holder.lastnameAuthor.text = quoteList!![position]!!.lastnameAuthor
        holder.title.text = quoteList!![position]!!.title
    }

    override fun getItemCount(): Int {
        return if (quoteList == null) {
            0
        } else {
            quoteList!!.size
        }
    }

    inner class QuoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var quoteText: TextView
        var nameAuthor: TextView
        var lastnameAuthor: TextView
        var title: TextView
        var onItemClickListener: OnItemClickListener? = null

        init {
            quoteText = view.findViewById(R.id.quoteTextElementView)
            nameAuthor = view.findViewById(R.id.nameAuthorTextElementView)
            lastnameAuthor = view.findViewById(R.id.lastnameAuthorTextElementView)
            title = view.findViewById(R.id.textViewTitleElementView)
            view.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener!!.onItemClick(quoteList!![position])
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(quote: Quote?)
    }

    /*fun setOnItemClickListener(listener: (Any) -> Unit) {
        this.listener = listener
    }*/
    open fun setOnItemClickListener(listener: (Quote) -> Unit) {
        this.listener = listener as OnItemClickListener?
    }
}