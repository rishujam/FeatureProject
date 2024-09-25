package com.example.emptyproject.pokemon.ui.listing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emptyproject.pokemon.domain.usecase.GetPokemonListUseCase
import com.example.emptyproject.pokemon.domain.Resource
import com.example.emptyproject.pokemon.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Created by Sudhanshu Kumar on 20/09/24.
 */

@HiltViewModel
class PokemonListingViewModel @Inject constructor(
    private val getPokemonList: GetPokemonListUseCase
) : ViewModel() {

    init {
        Log.d("RishuTest", "calling listing api")
        getListing()
    }

    private val _pokemonList = MutableSharedFlow<Resource<List<Pokemon>>>(1)
    val pokemonList = _pokemonList.asSharedFlow()

    private fun getListing() = viewModelScope.launch(Dispatchers.IO) {
        getPokemonList.invoke(1).collectLatest {
            _pokemonList.emit(it)
        }
    }

}