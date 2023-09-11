package com.example.cryptodemo.data

import com.google.gson.annotations.SerializedName

data class CoinDto (
    @SerializedName("id")
    val id : String?,
    @SerializedName("rank")
    val rank : String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("name")
    val name : String?,
    @SerializedName("maxSupply")
    val maxSupply: Float?,
    @SerializedName("marketCapUsd")
    val marketCapUsd:Float?,
    @SerializedName("volumeUsd24Hr")
    val volumeUsd24Hr :Float?,
    @SerializedName("priceUsd")
    val priceUsd :Float?,
    @SerializedName("changePercent24Hr")
    val changePercent24Hr : Float?,
    @SerializedName("vwap24Hr")
    val vwap24Hr: Float?,
    @SerializedName("explorer")
    val explorer : String?
)

