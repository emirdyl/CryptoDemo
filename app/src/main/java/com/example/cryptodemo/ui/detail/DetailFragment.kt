package com.example.cryptodemo.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.cryptodemo.R
import com.example.cryptodemo.databinding.FragmentDetailBinding
import com.example.cryptodemo.ext.format
import com.example.cryptodemo.ui.model.Coin

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel by viewModels<DetailViewModel>()

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi(args.coin)
    }

    private fun setupUi(coin: Coin) {
        binding.apply {
            stockName.text = coin.name
            stockSymbol.text = coin.symbol
            currentPrice.text = "${coin.priceUsd?.format()}$"
            coin.changePercent24Hr?.let {
                if (it > 0.0) {
                    stockPercent.text = "${coin.changePercent24Hr.format()}%"
                    stockPercent.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.profit_green
                        )
                    )

                } else if(it == 0f) {
                    stockPercent.text = "${coin.changePercent24Hr.format()}%"
                }else {
                    stockPercent.text ="${coin.changePercent24Hr.format()}%"
                    stockPercent.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.loss_red
                        )
                    )
                }
            }
        }

    }
}