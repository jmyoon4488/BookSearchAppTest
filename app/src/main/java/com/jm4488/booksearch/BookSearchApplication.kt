package com.jm4488.booksearch

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jm4488.booksearch.network.NetworkParams
import com.jm4488.retrofitservice.RestfulService
import retrofit2.Retrofit

class BookSearchApplication : Application() {
    init {
        RestfulService.getInstance().initRetrofitBuilder(NetworkParams.BASE_URL)
    }
}