package com.jm4488.booksearch.network.response

import com.google.gson.annotations.SerializedName
import com.jm4488.booksearch.search.model.BookDetailModel

data class BookDetailResponse(
    var bookDetail: BookDetailModel = BookDetailModel()
)

