package com.example.cryptodemo.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptodemo.data.RetrofitInstance
import com.example.cryptodemo.ui.model.Coin
import com.example.notesdemo.ext.toLiveData
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    private val _allCoinLiveData = MutableLiveData<List<Coin>>()
    val allCoinLiveData = _allCoinLiveData.toLiveData()

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData = _errorLiveData.toLiveData()

    fun getAllCoins() {
        viewModelScope.launch {

            try {
                val coinListDto = RetrofitInstance.api.getAllCoins().data
                val coinList= coinListDto.map {
                    Coin(it.id,it.rank,it.symbol,it.name,it.maxSupply,it.marketCapUsd,it.volumeUsd24Hr,it.priceUsd,it.changePercent24Hr,it.vwap24Hr,it.explorer)
                }
                _allCoinLiveData.value=coinList
            } catch (e: Exception) {
                _errorLiveData.value=e.message
            }
        }
    }



}