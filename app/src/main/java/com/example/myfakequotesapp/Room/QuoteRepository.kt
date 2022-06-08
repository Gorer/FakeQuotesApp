package com.example.myfakequotesapp.Room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myfakequotesapp.Model.Quote
import com.example.myfakequotesapp.Model.QuoteDao
import org.jetbrains.annotations.NotNull

class QuoteRepository(application: Application) : RepositoryTasks {
    private val mQuoteDao: QuoteDao?
    var mAllQuotes: LiveData<List<Quote?>?>? = MutableLiveData()
    //var mAllQuotes = MutableLiveData<List<Quote?>?>()
    private var quoteById: Quote? = null
    override fun <T : Quote?> getAllQuotes(): LiveData<List<T?>?>? {
        return mAllQuotes as LiveData<List<T?>?>?
    }

    override fun <T : Quote?> getQuoteByID(quoteId: Int): Quote? {
        QuoteRoomDatabase.Companion.databaseWriteExecutor.execute(Runnable {
            quoteById = mQuoteDao!!.getQuoteByID(quoteId)
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
        QuoteRoomDatabase.Companion.databaseWriteExecutor.execute(Runnable {
            mQuoteDao!!.insertQuote(
                quote as Quote
            )
        })
    }

    override fun <T : Quote?> deleteQuote(quote: T) {
        QuoteRoomDatabase.Companion.databaseWriteExecutor.execute(Runnable {
            mQuoteDao!!.DeleteQuote(
                quote as Quote
            )
        })
    }

    override fun <T : Quote?> updateQuote(quote: T?) {
        QuoteRoomDatabase.Companion.databaseWriteExecutor.execute(Runnable {
            mQuoteDao!!.updateQuote(
                quote
            )
        })
    }

    init {
        val db: QuoteRoomDatabase? = QuoteRoomDatabase.Companion.getInstance(application)
        // немного непонимаю, что мы сюда записываем
        mQuoteDao = db?.quoteDao()
        mAllQuotes = mQuoteDao?.allQuote as MutableLiveData<List<Quote?>?>
    }
}