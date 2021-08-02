package com.jm4488.booksearch.search.repository

import com.jm4488.retrofitservice.RestfulService
import com.jm4488.booksearch.network.ServerApi
import com.jm4488.booksearch.network.response.BookItemResponse
import com.jm4488.booksearch.search.model.BookDetailModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class BookRepository {
    val TAG = this.javaClass.name

    fun requestBookList(word: String, page: String, callback: (Boolean, BookItemResponse) -> Unit) {
        val api = RestfulService.getInstance().getApiInstance(ServerApi::class.java)
        val call = api.getBookList(word, page)
        call.enqueue(object: Callback<BookItemResponse?> {
            override fun onResponse(call: Call<BookItemResponse?>, response: Response<BookItemResponse?>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback(true, it) }
                } else {
                    callback(false, BookItemResponse())
                }
            }

            override fun onFailure(call: Call<BookItemResponse?>, t: Throwable) {
                callback(false, BookItemResponse())
            }
        })
    }

    fun requestBookDetail(isbnCode: String, callback: (Boolean, BookDetailModel) -> Unit) {
        val api = RestfulService.getInstance().getApiInstance(ServerApi::class.java)
        val call = api.getBookDetail(isbnCode)
        call.enqueue(object: Callback<BookDetailModel?> {
            override fun onResponse(call: Call<BookDetailModel?>, response: Response<BookDetailModel?>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback(true, it) }
                } else {
                    callback(false, BookDetailModel())
                }
            }

            override fun onFailure(call: Call<BookDetailModel?>, t: Throwable) {
                callback(false, BookDetailModel())
            }

        })
    }

}