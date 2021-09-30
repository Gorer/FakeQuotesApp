package com.example.myfakequotesapp.Presentation.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.myfakequotesapp.Domain.Model.Quote;
import com.example.myfakequotesapp.Presentation.Repository.Room.QuoteRoomDatabase;

public class AddQuoteViewModel extends ViewModel {
    /*public void SaveNewQuote(String quoteText, String nameAuthor, String lastnameAuthor){
        QuoteRoomDatabase database = QuoteRoomDatabase.getInstance(this.getApplicationContext());
        Quote quote = new Quote();
        quote.quoteText = quoteText;
        quote.nameAuthor = nameAuthor;
        quote.lastnameAuthor = lastnameAuthor;
        database.quoteDao().insertQuote(quote);

        finish();
    }*/
}
