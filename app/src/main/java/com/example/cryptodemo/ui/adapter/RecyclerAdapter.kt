package com.example.cryptodemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptodemo.databinding.ItemStockCardBinding
import com.example.cryptodemo.ext.format
import com.example.cryptodemo.ui.model.Coin

class RecyclerAdapter(val onItemClicked: ((Coin) -> Unit)?) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
    private var coinList: List<Coin> = emptyList()


    class RecyclerViewHolder(
        private val binding: ItemStockCardBinding,
        private val onItemClicked: ((Coin) -> Unit)? = null,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: Coin) {
            with(coin) {
                binding.apply {
                    stockName.text = name
                    stockPrice.text = "${coin.priceUsd?.format()}$"
                    stockPercent.text = "${coin.changePercent24Hr?.format()}%"
                    stockSymbol.text = symbol

                    root.setOnClickListener{
                        onItemClicked?.invoke(coin)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RecyclerViewHolder(
            ItemStockCardBinding.inflate(inflater, parent, false),
            onItemClicked
        )
    }

    override fun getItemCount() = coinList.size

    override fun onBindViewHolder(holder: RecyclerAdapter.RecyclerViewHolder, position: Int) {
        holder.bind(coinList[position])
    }

    fun submitList(list: List<Coin>) {
        coinList =list
        notifyDataSetChanged()
    }
}