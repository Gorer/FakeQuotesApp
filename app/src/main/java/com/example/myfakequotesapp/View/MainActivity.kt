package com.example.myfakequotesapp.View

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfakequotesapp.Model.Quote
import com.example.myfakequotesapp.R
import com.example.myfakequotesapp.Room.Repository
import com.example.myfakequotesapp.View.Adapters.QuoteListAdapter
import com.example.myfakequotesapp.ViewModel.QuoteListViewModel

class MainActivity : AppCompatActivity() {
    private var listViewModel: QuoteListViewModel? = null
    private var button: Button? = null
    private var quoteListAdapter: QuoteListAdapter? = null
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Repository.initDataBase(application)
        button = findViewById(R.id.toAddQuoteButton)
        button?.setOnClickListener(View.OnClickListener {
            startActivityForResult(
                Intent(
                    this@MainActivity,
                    AddQuoteActivity::class.java
                ), 100
            )
        })
        listViewModel = ViewModelProvider(this).get(QuoteListViewModel::class.java)
        initRecyclerView()
        loadQuoteList()
    }

    private fun loadQuoteList() {
        val liveQuote: LiveData<List<Quote?>?>?
        liveQuote = listViewModel?.getListFromViewModel
        //liveQuote!!.observe(this) { quotes -> quoteListAdapter!!.quoteList = quotes }
        liveQuote!!.observe(
            this
        ) { quotes -> quoteListAdapter!!.setQuoteList(quotes) }
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.setLayoutManager(LinearLayoutManager(this))
        val dividerItemDecoration = DividerItemDecoration(
            this,
            DividerItemDecoration.VERTICAL
        ) //декорации
        recyclerView?.addItemDecoration(dividerItemDecoration)
        quoteListAdapter = QuoteListAdapter(applicationContext)
        recyclerView?.setAdapter(quoteListAdapter)
        //listViewModel?.getListFromViewModel
        //    ?.observe(this) { quotes -> quoteListAdapter!!.quoteList = quotes }
        listViewModel!!.getListFromViewModel?.observe(this,
            Observer<List<Quote?>?> { quotes -> quoteListAdapter!!.setQuoteList(quotes) })
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                listViewModel!!.deleteQuote(
                    (recyclerView?.getAdapter() as QuoteListAdapter?)!!.quoteList!![position]
                )
            }
        }).attachToRecyclerView(recyclerView)
        quoteListAdapter!!.setOnItemClickListener (object : QuoteListAdapter.OnItemClickListener {
            override fun onItemClick(quote: Quote?) {
                val intent = Intent(this@MainActivity, QuoteDetailActivity::class.java)
                intent.putExtra("id", quote!!.id)
                intent.putExtra("quoteText", quote!!.quoteText)
                intent.putExtra("nameAuthor", quote!!.nameAuthor)
                intent.putExtra("lastnameAuthor", quote!!.lastnameAuthor)
                intent.putExtra("image", quote!!.image)
                intent.putExtra("title", quote!!.title)
                startActivity(intent)
            }
        })

    /*(QuoteListAdapter.OnItemClickListener
        { quote: Quote ->
            val intent = Intent(this@MainActivity, QuoteDetailActivity::class.java)
            intent.putExtra("id", quote!!.id)
            intent.putExtra("quoteText", quote!!.quoteText)
            intent.putExtra("nameAuthor", quote!!.nameAuthor)
            intent.putExtra("lastnameAuthor", quote!!.lastnameAuthor)
            intent.putExtra("image", quote!!.image)
            intent.putExtra("title", quote!!.title)
            startActivity(intent)
        })*/

    /*(object : QuoteListAdapter.OnItemClickListener {
            fun setOnItemClickListener(listener: QuoteListAdapter.OnItemClickListener?) {
                quote: Quote ->
                val intent = Intent(this@MainActivity, QuoteDetailActivity::class.java)
                intent.putExtra("id", quote!!.id)
                intent.putExtra("quoteText", quote!!.quoteText)
                intent.putExtra("nameAuthor", quote!!.nameAuthor)
                intent.putExtra("lastnameAuthor", quote!!.lastnameAuthor)
                intent.putExtra("image", quote!!.image)
                intent.putExtra("title", quote!!.title)
                startActivity(intent)
            }
        })*/
    /*{ quote: Quote ->
            val intent = Intent(this@MainActivity, QuoteDetailActivity::class.java)
            intent.putExtra("id", quote!!.id)
            intent.putExtra("quoteText", quote!!.quoteText)
            intent.putExtra("nameAuthor", quote!!.nameAuthor)
            intent.putExtra("lastnameAuthor", quote!!.lastnameAuthor)
            intent.putExtra("image", quote!!.image)
            intent.putExtra("title", quote!!.title)
            startActivity(intent)
        }*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100) {
            loadQuoteList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}