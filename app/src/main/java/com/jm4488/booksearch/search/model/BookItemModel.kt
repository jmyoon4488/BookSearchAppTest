package com.jm4488.booksearch.search.model

import com.google.gson.annotations.SerializedName

data class BookItemModel(
    @SerializedName("title")
    var title: String = "",

    @SerializedName("subtitle")
    var subtitle: String = "",

    @SerializedName("isbn13")
    var isbn13: String = "",

    @SerializedName("price")
    var price: String = "",

    @SerializedName("image")
    var image: String = "",

    @SerializedName("url")
    var url: String = ""
)
