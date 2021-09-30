package com.example.myfakequotesapp;

public class QuoteRepository {/*implements RepositoryTasks {
    private QuoteDao mQuoteDao;
    private LiveData<List<Quote>> mAllQuotes = new MutableLiveData<>();

    public QuoteRepository(Application application) {
        QuoteRoomDatabase db = QuoteRoomDatabase.getDatabase(application);
        // немного непонимаю, что мы сюда записываем
        mQuoteDao = db.quoteDao();
        mAllQuotes = mQuoteDao.getAllQuotes();
    }

    public LiveData<List<Quote>> getAllQuotes() {
        return mAllQuotes;
    }

    @Override
    public <T extends Quote> void addQuote(T quote) {
        QuoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            // ГДЕ РЕАЛИЗАЦИЯ?
            // В АННОТАЦИИ
            mQuoteDao.addQuote(((Quote) quote));
        });
    }

    @Override
    public <T extends Quote> void deleteQuote(T quote) {
        QuoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            //ГДЕ РЕАЛИЗАЦИЯ?
            mQuoteDao.deleteQuote(((Quote) quote));
        });
    }*/
}
