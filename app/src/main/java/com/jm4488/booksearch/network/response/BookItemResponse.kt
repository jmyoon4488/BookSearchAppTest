package com.jm4488.booksearch.network.response

import com.google.gson.annotations.SerializedName
import com.jm4488.booksearch.search.model.BookItemModel

data class BookItemResponse(
    @SerializedName("error")
    var error: String = "",

    @SerializedName("total")
    var total: String = "0",

    @SerializedName("page")
    var page: String = "0",

    @SerializedName("books")
    var books: List<BookItemModel> = emptyList()
)
