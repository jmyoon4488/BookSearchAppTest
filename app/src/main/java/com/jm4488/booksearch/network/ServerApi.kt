package com.jm4488.booksearch.network

import com.jm4488.booksearch.network.response.BookItemResponse
import com.jm4488.booksearch.search.model.BookDetailModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerApi {
    @GET("1.0/search/{keyWord}/{pageNo}")
    fun getBookList(
        @Path("keyWord") keyword: String,
        @Path("pageNo") pageNumber: String
    ): Call<BookItemResponse>

    @GET("1.0/books/{isbnCode}")
    fun getBookDetail(
        @Path("isbnCode") code: String
    ): Call<BookDetailModel>
}