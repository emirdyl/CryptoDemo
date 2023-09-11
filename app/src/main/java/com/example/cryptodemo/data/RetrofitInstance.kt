package com.example.cryptodemo.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


object RetrofitInstance {
    private var retrofit: Retrofit? = null
    private const val BASE_URL ="https://api.coincap.io"
    val retrofitInstance: Retrofit
        get() {
            if (retrofit == null) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
            return retrofit!!
        }

    val api by lazy {
        retrofitInstance.create(CryptoApi::class.java)
    }
}

