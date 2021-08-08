package com.jm4488.booksearch.search.viewmodel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jm4488.booksearch.search.model.BookItemModel
import com.jm4488.booksearch.search.repository.BookRepository
import java.util.stream.Collectors.toList

class BookListViewModel : ViewModel() {
    val TAG = this.javaClass.name

    val bookRepository = BookRepository()
    var bookListLiveData = MutableLiveData(listOf<BookItemModel>())
    var bookTotalCount = 0

    private var currentSearchPageNumber = ""
    private var searchKeyword = ""
    private var exclusiveWord = ""

    fun searchBooks(keyword: String) {
        searchKeyword = filterKeyword(keyword)
        bookRepository.requestBookList(searchKeyword, "1") { isSuccess, bookResponse ->
            if (isSuccess) {
                currentSearchPageNumber = bookResponse.page
                bookTotalCount = bookResponse.total.toInt()

                val bookList = bookResponse.books
                var filteredList = bookList.stream().filter {
                    exclusiveWord.isEmpty() || !it.title.contains(exclusiveWord, ignoreCase = true)
                }.collect(toList())

                bookListLiveData.postValue(filteredList)
            }
        }
    }

    fun searchBooksNext() {
        bookRepository.requestBookList(searchKeyword, currentSearchPageNumber.nextNumber()) { isSuccess, bookResponse ->
            if (isSuccess) {
                currentSearchPageNumber = bookResponse.page
                bookTotalCount = bookResponse.total.toInt()

                val bookList = bookResponse.books
                val filteredList = bookList.stream().filter {
                    exclusiveWord.isEmpty() || !it.title.contains(exclusiveWord, ignoreCase = true)
                }.collect(toList())
                bookListLiveData.postValue(filteredList)
            }
        }
    }

    private fun filterKeyword(word: String): String {
        val operatorOr = "|"
        val operatorEx = "-"

        if (word.contains(operatorOr)) {
            return word.replace(operatorOr, " ")
        } else if (word.contains(operatorEx)) {
            val wordsList = word.split("-")
            if (wordsList.size > 1) {
                exclusiveWord = wordsList[1]
            }
            return wordsList[0]
        }
        return word
    }

    private fun String.nextNumber(): String {
        if (this.isDigitsOnly()) {
            return (this.toInt() + 1).toString()
        }
        return "0"
    }
}