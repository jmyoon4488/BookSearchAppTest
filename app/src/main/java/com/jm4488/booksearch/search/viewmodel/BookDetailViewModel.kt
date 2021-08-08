package com.jm4488.booksearch.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jm4488.booksearch.search.model.BookDetailModel
import com.jm4488.booksearch.search.repository.BookRepository

class BookDetailViewModel : ViewModel() {
    val TAG = this.javaClass.name

    private val bookRepository = BookRepository()
    var bookDetailLiveData = MutableLiveData<BookDetailModel>()

    fun requestBookDetailInfo(isbnCode: String) {
        bookRepository.requestBookDetail(isbnCode) { isSuccess, bookResponse ->
            if (isSuccess) {
                bookDetailLiveData.postValue(bookResponse)
            }
        }
    }
}