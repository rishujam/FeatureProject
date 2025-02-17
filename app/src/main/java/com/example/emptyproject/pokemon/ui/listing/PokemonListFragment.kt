package com.example.emptyproject.pokemon.ui.listing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emptyproject.databinding.FragmentPokemonListingBinding
import com.example.emptyproject.pokemon.domain.Resource
import com.example.emptyproject.pokemon.domain.model.Pokemon
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
 * Created by Sudhanshu Kumar on 17/09/24.
 */

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListingBinding? = null
    private val binding get() = _binding

    private val viewModel: PokemonListingViewModel by viewModels()
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonListingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        lifecycleScope.launch {
            viewModel.pokemonList.collect {
                when(it) {
                    is Resource.Success -> {
                        binding?.pbPokemonListing?.visibility = View.GONE
                        if(it.data.isNullOrEmpty() && pokemonAdapter.differ.currentList.size == 0) {
                            binding?.tvMessage?.visibility = View.VISIBLE
                            binding?.tvMessage?.text = "No Pokemon available"
                        } else {
                            if(!it.data.isNullOrEmpty()) {
                                val newList = mutableListOf<Pokemon>()
                                newList.addAll(pokemonAdapter.differ.currentList + it.data)
                                pokemonAdapter.differ.submitList(newList)
                            }
                        }
                    }
                    is Resource.Loading -> {
                        binding?.pbPokemonListing?.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        binding?.pbPokemonListing?.visibility = View.GONE
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupRv() {
        pokemonAdapter = PokemonAdapter()
        binding?.rvPokemon?.apply {
            adapter = pokemonAdapter
            layoutManager = GridLayoutManager(context, 2)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    layoutManager?.apply {
                        val firstVisibleItemPosition = (this as? GridLayoutManager)?.findFirstVisibleItemPosition()
                        firstVisibleItemPosition?.let {
                            val isAtLastItem = firstVisibleItemPosition + childCount >= itemCount
                            if(isAtLastItem) {
                                viewModel.getListing()
                            }
                        }
                    }
                }
            })
        }
        pokemonAdapter.setOnItemClickListener { pokemon, position ->
            val action = PokemonListFragmentDirections.actionListFragToDetailFrag(
                Pokemon(id = 1, "poki", "vs"),
                pokemon.id ?: 0
            )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}