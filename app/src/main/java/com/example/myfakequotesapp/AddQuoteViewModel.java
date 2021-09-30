package com.example.myfakequotesapp;

import androidx.lifecycle.ViewModel;

public class AddQuoteViewModel extends ViewModel {
    public void AddQuoteThroughVM(String quoteText, String nameAuthor, String lastnameAuthor){

        Quote quote = new Quote();
        quote.quoteText = quoteText;
        quote.nameAuthor = nameAuthor;
        quote.lastnameAuthor = lastnameAuthor;
        Repository.getDataBase().quoteDao().insertQuote(quote);
    }
}
