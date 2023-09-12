package com.example.cryptodemo.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptodemo.data.RetrofitInstance
import com.example.cryptodemo.ui.model.Coin
import com.example.cryptodemo.util.ViewState
import com.example.notesdemo.ext.toLiveData
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    private val _viewStateLiveData = MutableLiveData<ViewState<List<Coin>>>()
    val viewStateLiveData = _viewStateLiveData.toLiveData()

    fun getAllCoins() {

        _viewStateLiveData.value = ViewState.loading()

        viewModelScope.launch {
            try {
                val coinListDto = RetrofitInstance.api.getAllCoins().data
                val coinList = coinListDto.map {
                    Coin(
                        it.id,
                        it.rank,
                        it.symbol,
                        it.name,
                        it.maxSupply,
                        it.marketCapUsd,
                        it.volumeUsd24Hr,
                        it.priceUsd,
                        it.changePercent24Hr,
                        it.vwap24Hr,
                        it.explorer
                    )
                }
                _viewStateLiveData.value = ViewState.success(coinList)
            } catch (e: Exception) {
                _viewStateLiveData.value = ViewState.failed(e)
            }

        }
    }


}