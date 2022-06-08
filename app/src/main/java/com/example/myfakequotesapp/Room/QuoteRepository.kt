package com.example.myfakequotesapp.Room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myfakequotesapp.Model.Quote
import com.example.myfakequotesapp.Model.QuoteDaoNew

class QuoteRepository(application: Application) : RepositoryTasks {
    private val mQuoteDaoNew: QuoteDaoNew?
    var mAllQuotes: LiveData<List<Quote?>?>? = MutableLiveData()
    //var mAllQuotes = MutableLiveData<List<Quote?>?>()
    private var quoteById: Quote? = null
    override fun <T : Quote?> getAllQuotes(): LiveData<List<T?>?>? {
        return mAllQuotes as LiveData<List<T?>?>?
    }

    override fun <T : Quote?> getQuoteByID(quoteId: Int): Quote? {
        QuoteRoomDatabaseNew.Companion.databaseWriteExecutor.execute(Runnable {
            quoteById = mQuoteDaoNew!!.getQuoteByID(quoteId)
        })
        return quoteById
    }

    /*override fun <T : Quote?> getAllQuotes(): LiveData<List<Quote?>?>? {
        return mAllQuotes
    }*/

    /*@NotNull
    fun getAllQuotes(): LiveData<List<Quote?>?> {
        return mAllQuotes
    }*/


    override fun <T : Quote?> addQuote(quote: T) {
        QuoteRoomDatabaseNew.Companion.databaseWriteExecutor.execute(Runnable {
            mQuoteDaoNew!!.insertQuote(
                quote as Quote
            )
        })
    }

    override fun <T : Quote?> deleteQuote(quote: T) {
        QuoteRoomDatabaseNew.Companion.databaseWriteExecutor.execute(Runnable {
            mQuoteDaoNew!!.DeleteQuote(
                quote as Quote
            )
        })
    }

    override fun <T : Quote?> updateQuote(quote: T?) {
        QuoteRoomDatabaseNew.Companion.databaseWriteExecutor.execute(Runnable {
            mQuoteDaoNew!!.updateQuote(
                quote
            )
        })
    }

    init {
        val db: QuoteRoomDatabaseNew? = QuoteRoomDatabaseNew.Companion.getInstance(application)
        // немного непонимаю, что мы сюда записываем
        mQuoteDaoNew = db?.quoteDao()
        mAllQuotes = mQuoteDaoNew?.allQuote as LiveData<List<Quote?>?>?
    }
}