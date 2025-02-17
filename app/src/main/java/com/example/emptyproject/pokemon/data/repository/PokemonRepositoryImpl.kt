package com.example.emptyproject.pokemon.data.repository

import com.example.emptyproject.pokemon.data.remote.PokemonApi
import com.example.emptyproject.pokemon.data.model.detail.ResPokemonDetail
import com.example.emptyproject.pokemon.data.model.list.ResPokemonListing
import com.example.emptyproject.pokemon.domain.PokemonRepository
import com.example.emptyproject.pokemon.domain.Resource
import com.example.emptyproject.pokemon.domain.mapper.MapperPokemonDetail
import com.example.emptyproject.pokemon.domain.mapper.MapperPokemonList
import com.example.emptyproject.pokemon.domain.model.Pokemon
import com.example.emptyproject.pokemon.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/*
 * Created by Sudhanshu Kumar on 20/09/24.
 */

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi,
    private val pokemonDetailMapper: MapperPokemonDetail,
    private val pokemonListMapper: MapperPokemonList
) : PokemonRepository {

    override suspend fun getPokemonListing(
        offset: Int,
        limit: Int
    ): Flow<Resource<List<Pokemon>?>> = flow {
        emit(Resource.Loading())
        try {
            val res = api.getPokemonListing(limit, offset)
            if(res.isSuccessful) {
                emit(Resource.Success(pokemonListMapper.map(res.body())))
            } else {
                emit(Resource.Error("Error: ${res.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Exception: ${e.message}"))
        }
    }

    override suspend fun getPokemonDetail(id: Int): Flow<Resource<PokemonDetail?>> = flow {
        emit(Resource.Loading())
        try {
            val res = api.getPokemonDetail(id)
            if(res.isSuccessful) {
                emit(Resource.Success(pokemonDetailMapper.map(res.body())))
            } else {
                emit(Resource.Error("Error: ${res.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Exception: ${e.message}"))
        }
    }
}