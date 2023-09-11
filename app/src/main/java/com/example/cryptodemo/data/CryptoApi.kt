package com.example.cryptodemo.data

import retrofit2.http.GET

interface CryptoApi {
    @GET("v2/assets")
    suspend fun getAllCoins():CoinResponse
}