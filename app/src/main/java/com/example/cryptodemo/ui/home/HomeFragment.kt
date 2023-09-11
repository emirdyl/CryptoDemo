package com.example.cryptodemo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptodemo.databinding.FragmentHomeBinding
import com.example.cryptodemo.ui.adapter.RecyclerAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private var adapter: RecyclerAdapter? = null

    private val viewModel by viewModels<HomeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        viewModel.getAllCoins()
        observeViewModelEvents()
    }

    private fun observeViewModelEvents() {

        viewModel.errorLiveData.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
        viewModel.allCoinLiveData.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }
    }

    private fun setRecyclerView() {
        adapter = RecyclerAdapter {
            val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(direction)
        }
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }
}