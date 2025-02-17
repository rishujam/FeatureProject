package com.example.emptyproject.pokemon.ui.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emptyproject.pokemon.domain.PokemonRepository
import com.example.emptyproject.pokemon.domain.Resource
import com.example.emptyproject.pokemon.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Created by Sudhanshu Kumar on 20/09/24.
 */

@HiltViewModel
class PokemonListingViewModel @Inject constructor(
    private val pokemonRepo: PokemonRepository
) : ViewModel() {

    init {
        getListing()
    }

    private val _pokemonList = MutableSharedFlow<Resource<List<Pokemon>?>>(1)
    val pokemonList = _pokemonList.asSharedFlow()

    val xFlow = flow {
        val list = listOf(1, 2, 3, 4)
        for(i in list) {
            emit(i)
            delay(1000L)
        }
    }

    private var offset = 1

    private var isApiInProgress = false

    fun getListing() = viewModelScope.launch(Dispatchers.IO) {
        if(isApiInProgress) {
            return@launch
        }
        isApiInProgress = true
        pokemonRepo.getPokemonListing(offset, OFFSET_LIMIT).collectLatest {
            when(it) {
                is Resource.Success -> {
                    offset++
                    offset += OFFSET_LIMIT
                    isApiInProgress = false
                    _pokemonList.emit(Resource.Success(it.data))
                }
                is Resource.Error -> {
                    isApiInProgress = false
                    _pokemonList.emit(Resource.Error(it.message))
                }
                is Resource.Loading -> {
                    _pokemonList.emit(Resource.Loading())
                }
            }
        }
    }

    companion object {
        private const val OFFSET_LIMIT = 20
    }

}