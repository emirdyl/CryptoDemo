package com.example.cryptodemo.ui.home

import android.os.Bundle
import android.util.Log
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
import com.example.cryptodemo.util.ViewState

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
        observeViewModelEvents()
    }

    private fun observeViewModelEvents() {
        viewModel.viewStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Success -> {
                    Log.e("state", "Success")
                    adapter?.submitList(state.data)
                    binding.ltAnimation.visibility = View.GONE
                    binding.internetLayout.visibility = View.GONE
                }

                is ViewState.Loading -> {
                    Log.e("state", "Loading")
                    binding.ltAnimation.visibility = View.VISIBLE
                    binding.internetLayout.visibility = View.GONE
                }

                is ViewState.Failed -> {
                    Log.e("state", "Failed")
                    Toast.makeText(context, state.throwable.message, Toast.LENGTH_SHORT).show()
                    binding.ltAnimation.visibility = View.GONE
                    binding.internetLayout.visibility =View.VISIBLE

                }

            }
            binding.btnRefresh.setOnClickListener {
              viewModel.getAllCoins()
            }
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