package com.example.myfakequotesapp.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.myfakequotesapp.Model.Quote;
import com.example.myfakequotesapp.Room.Repository;

public class AddQuoteViewModel extends ViewModel {
    public void AddQuoteThroughVM(String quoteText, String nameAuthor, String lastnameAuthor){

        Quote quote = new Quote();
        quote.quoteText = quoteText;
        quote.nameAuthor = nameAuthor;
        quote.lastnameAuthor = lastnameAuthor;
        Repository.getDataBase().addQuote(quote);
    }
}
