package com.example.cryptodemo.ui.model

import java.io.Serializable

data class Coin (
    val id : String?,
    val rank : String?,
    val symbol: String?,
    val name : String?,
    val maxSupply: Float?,
    val marketCapUsd:Float?,
    val volumeUsd24Hr :Float?,
    val priceUsd :Float?,
    val changePercent24Hr : Float?,
    val vwap24Hr: Float?,
    val explorer : String?
    ): Serializable
