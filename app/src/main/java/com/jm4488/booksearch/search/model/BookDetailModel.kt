package com.jm4488.booksearch.search.model

import com.google.gson.annotations.SerializedName

data class BookDetailModel(
    @SerializedName("error")
    var error: String = "",

    @SerializedName("title")
    var title: String = "",

    @SerializedName("subtitle")
    var subtitle: String = "",

    @SerializedName("authors")
    var authors: String = "",

    @SerializedName("publisher")
    var publisher: String = "",

    @SerializedName("language")
    var language: String = "",

    @SerializedName("isbn10")
    var isbn10: String = "",

    @SerializedName("isbn13")
    var isbn13: String = "",

    @SerializedName("pages")
    var pages: String = "",

    @SerializedName("year")
    var year: String = "",

    @SerializedName("rating")
    var rating: String = "",

    @SerializedName("desc")
    var desc: String = "",

    @SerializedName("price")
    var price: String = "",

    @SerializedName("image")
    var image: String = "",

    @SerializedName("url")
    var url: String = ""
)

//"error": "0",
//"title": "Learning Android Game Programming",
//"subtitle": "A Hands-On Guide to Building Your First Android Game",
//"authors": "Richard A. Rogers",
//"publisher": "Addison-Wesley",
//"language": "English",
//"isbn10": "0321769627",
//"isbn13": "9780321769626",
//"pages": "480",
//"year": "2012",
//"rating": "3",
//"desc": "The book starts with an up-to-the-minute overview of today's Android mobile games marketplace, reviews each leading genre, and teaches you the foundational concepts of game development. You'll design a simple game, then master every step of game development with AndEngine - the powerful, open source...",
//"price": "$8.40",
//"image": "https://itbook.store/img/books/9780321769626.png",
//"url": "https://itbook.store/books/9780321769626"