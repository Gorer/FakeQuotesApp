package com.example.myfakequotesapp.ViewModel

import com.example.myfakequotesapp.Model.Quote
import androidx.lifecycle.ViewModel
import com.example.myfakequotesapp.Room.Repository

class AddQuoteViewModel : ViewModel() {
    fun AddQuoteThroughVM(
        quoteText: String?, nameAuthor: String?,
        lastnameAuthor: String?, image: String?, title: String?
    ) {
        val quote = Quote()
        quote.quoteText = quoteText
        quote.nameAuthor = nameAuthor
        quote.lastnameAuthor = lastnameAuthor
        quote.image = image
        quote.title = title
        Repository.getDataBase?.addQuote(quote)
    }
}