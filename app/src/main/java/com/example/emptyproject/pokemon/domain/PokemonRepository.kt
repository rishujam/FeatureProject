package com.example.emptyproject.pokemon.domain

import com.example.emptyproject.pokemon.data.model.detail.ResPokemonDetail
import com.example.emptyproject.pokemon.data.model.list.ResPokemonListing
import com.example.emptyproject.pokemon.domain.model.Pokemon
import com.example.emptyproject.pokemon.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow

/*
 * Created by Sudhanshu Kumar on 20/09/24.
 */

interface PokemonRepository {

    suspend fun getPokemonListing(
        offset: Int,
        limit: Int
    ): Flow<Resource<List<Pokemon>?>>

    suspend fun getPokemonDetail(id: Int): Flow<Resource<PokemonDetail?>>

}